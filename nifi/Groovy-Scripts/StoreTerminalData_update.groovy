import groovy.json.JsonSlurper
import org.apache.commons.io.IOUtils
import java.nio.charset.*
import java.time.LocalDateTime
import java.util.logging.Logger

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_StoreTerminalDataWebServToDB");
String outputData = "", str, store_id, terminal_type, company_id = "", brand = ""
def fail = false
LocalDateTime date = LocalDateTime.now()
String date_time = date.format("yyyy-MM-dd hh:mm:ss.SSS")
json_sup = new JsonSlurper()

String com_id = flowFile.getAttribute('API').toString()
String[] arr = com_id.split('#')
company_id = arr[1].substring(14,24)
brand = arr[2].replaceAll(" ","")
log.info(company_id)

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        def recordIn = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
        json_response = json_sup.parseText(recordIn)

        outputData = "store_id"+','+"brand"+','+"group_id"+','+"terminal_id"+','+"company_id"+','+"description"+','+"hoststatechangedate"+','+"ishostonline"+','+
                "createts"+','+"modifyts"+','+"create_user_id"+','+"modify_user_id"+','+"terminal_desc"+','+"group_name"+','+"terminal_type"+'\n'
        for (int i = 0; i < json_response.hosts.size(); i++) {

            str = json_response.hosts.description[i]
            if (str.contains("WSKDS")) {
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '_') {
                        store_id = str.substring(j + 1, j + 7)
                    }
                }
                terminal_type = "KDS"
            } else if (str.contains("POS") && str.length() > 11) {
                store_id = str.substring(2, 8)
                terminal_type = "POS"
            } else if (str.contains("KIOSK") || str.contains("Kiosk")  && !str.contains("LAB")){
                store_id = str.substring(2, 8)
                terminal_type = "KIOSK"
            } else if (str.contains("KIOSK") || str.contains("Kiosk") && str.contains("LAB")){
                store_id = str.substring(3, 9)
                terminal_type = "KIOSK"
            } else if (str.contains("BRIDGE") && !str.contains("LAB")){
                store_id = str.substring(2, 8)
                terminal_type = "BRIDGE"
            } else if (str.contains("BRIDGE") && str.contains("LAB")){
                store_id = str.substring(3, 9)
                terminal_type = "BRIDGE"
            } else if (str.contains("TAB") && str.length() > 11){
                store_id = str.substring(2, 8)
                terminal_type = "TAB"
            } else {
                store_id = str
                terminal_type = "UAT"
            }

            def group_nm = ""
            if (json_response.groups['id'].contains(json_response.hosts.groupid[i])){
                int index=json_response.groups['id'].indexOf(json_response.hosts.groupid[i]);
                group_nm=json_response.groups[index].name;
            }

            String hoststatechangedate = (json_response.hosts.hostStateChangeDate[i].toString().replaceAll("T", " ")).replaceAll("Z", ".000")

            def outputRows = store_id+
                    ','+brand+
                    ','+json_response.hosts.groupid[i].toString()+
                    ','+json_response.hosts.id[i].toString()+
                    ','+company_id+
                    ','+''+
                    ','+hoststatechangedate+
                    ','+json_response.hosts.isHostOnline[i].toString()+
                    ','+date_time+
                    ','+date_time+
                    ','+"Nifi"+
                    ','+"Nifi"+
                    ','+json_response.hosts.description[i]+
                    ','+group_nm+
                    ','+terminal_type+'\n'

            outputData = outputData + outputRows

        }

        outputStream.write(outputData.toString().getBytes(StandardCharsets.UTF_8))
        outputData = ''

    }
    catch(e) {
        log.info("Error during processing of terminalData.groovy "+e)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}