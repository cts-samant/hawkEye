import org.apache.commons.io.IOUtils
import java.nio.charset.*
import java.time.LocalDateTime
import java.util.logging.Logger
import java.util.regex.Matcher
import java.util.regex.Pattern

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_SoftwareVersionDataCSVToDB");
String outputData = ""
def fail = false
LocalDateTime date = LocalDateTime.now()
String date_time = date.format("yyyy-MM-dd hh:mm:ss"), version, soft_name=""

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        def recordIn = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
        String[] rows = recordIn.split('\n'), cells
        for(int i =0 ; i < rows.size() ; i++){
            if(i == 0){
                def row = rows[i]
                cells = row.split(',')
                soft_name = cells[6].replaceAll("\r","")
                outputData = "acc_no"+','+"store_id"+','+"host_name"+','+"terminal_type"+','+"group_id"+','+"group_name"+','+"software_name"+','+"version"+"\n";
            }
            else {
                def row = rows[i].replaceAll("#","")
                cells = row.split(',')
                version = cells[6].replaceAll("\r","")

                Pattern p = Pattern.compile("[!@%&*\$+=|<>?{}\\[\\]~]")
                Pattern p1 = Pattern.compile("[a-zA-Z]")

                Matcher m = p.matcher(row)
                Matcher m1 = p1.matcher(cells[4])
                Matcher m2 = p1.matcher(cells[1])

                boolean haveSpecialCharecter = m.find()
                boolean haveAlphabetsGrpId = m1.find()
                boolean haveAlphabetsStrid = m2.find()

                if (haveSpecialCharecter || haveAlphabetsGrpId || haveAlphabetsStrid){

                    def reference = "{\n" +
                            "  \"acc_no\" : \""+cells[0]+"\",\n" +
                            "  \"store_id\" : \""+cells[1]+"\",\n" +
                            "  \"host_name\" : \""+cells[2]+"\",\n" +
                            "  \"terminal_type\" : \""+cells[3]+"\",\n" +
                            "  \"group_id\" : \""+cells[4]+"\",\n" +
                            "  \"group_name\" : \""+cells[5]+"\",\n" +
                            "  \"software_name\" : \""+soft_name+"\",\n" +
                            "  \"version\" : \""+version+"\",\n" +
                            "  \"modifyts\" : \""+date_time+"\",\n" +
                            "  \"createts\" : \""+date_time+"\",\n" +
                            "  \"create_user_id\" : \""+"Nifi"+"\",\n" +
                            "  \"modify_user_id\" : \""+"Nifi"+"\"\n" +
                            "}"

                    def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                            "VALUES ('"+"DMSEXCEP001"+
                            "','"+"Special Char/Alphabet"+
                            "','"+"Special character or Alphabet found"+
                            "','"+"SoftwareVersion"+
                            "','"+"dms_softwareVersionInterface"+
                            "','"+reference+
                            "','"+date_time+
                            "','"+date_time+
                            "','"+"Nifi"+
                            "','"+"Nifi"+"');"
                    try {
                        SQL.mydb.withTransaction {
                            SQL.mydb.execute(query);
                            log.info(query)
                        }
                    } catch (Exception e) {
                        log.info("\n Error Loading data for " + query);
                        log.info("ERROR "+e.toString())
                        log.info("........................................");
                    }

                }
                else if(cells[0]=="" || cells[1]=="" || cells[2]==""){

                    def reference = "{\n" +
                            "  \"acc_no\" : \""+cells[0]+"\",\n" +
                            "  \"store_id\" : \""+cells[1]+"\",\n" +
                            "  \"host_name\" : \""+cells[2]+"\",\n" +
                            "  \"terminal_type\" : \""+cells[3]+"\",\n" +
                            "  \"group_id\" : \""+cells[4]+"\",\n" +
                            "  \"group_name\" : \""+cells[5]+"\",\n" +
                            "  \"software_name\" : \""+soft_name+"\",\n" +
                            "  \"version\" : \""+version+"\",\n" +
                            "  \"modifyts\" : \""+date_time+"\",\n" +
                            "  \"createts\" : \""+date_time+"\",\n" +
                            "  \"create_user_id\" : \""+"Nifi"+"\",\n" +
                            "  \"modify_user_id\" : \""+"Nifi"+"\"\n" +
                            "}"

                    def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                            "VALUES ('"+"DMSEXCEP002"+
                            "','"+"Blank Field(s)"+
                            "','"+"Some field(s) are blank"+
                            "','"+"SoftwareVersion"+
                            "','"+"dms_storeMasterInterface"+
                            "','"+reference+
                            "','"+date_time+
                            "','"+date_time+
                            "','"+"Nifi"+
                            "','"+"Nifi"+"');"
                    try {
                        SQL.mydb.withTransaction {
                            SQL.mydb.execute(query);
                            log.info("")
                        }
                    } catch (Exception e) {
                        log.info("\n Error Loading data for " + query);
                        log.info("ERROR "+e.toString())
                        log.info("........................................");
                    }
                }
                else{
                    def outputRows = cells[0]+','+cells[1]+','+cells[2]+','+cells[3]+','+cells[4]+','+cells[5]+','+soft_name+','+cells[6]+"\n";
                    outputData = outputData + outputRows;
                }
                log.info("Code runs Successfully")

            }

        }

        outputStream.write(outputData.getBytes(StandardCharsets.UTF_8))
        outputData = ''

    }
    catch(e) {
        log.info("Error during processing of softwareVersion.groovy "+e)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}