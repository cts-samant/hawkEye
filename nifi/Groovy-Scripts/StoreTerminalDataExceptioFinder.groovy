import org.apache.commons.io.IOUtils
import java.nio.charset.*
import java.util.logging.Logger
import java.util.regex.Matcher
import java.util.regex.Pattern

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_StoreTerminalDataWebServToDB");
String outputData = ""
def fail = false

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        def recordIn = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
        String[] rows = recordIn.split('\n'), cells
        for(int i =0 ; i < rows.size() ; i++){
            if(i == 0){
                outputData = "store_id"+','+"brand"+','+"group_id"+','+"terminal_id"+','+"company_id"+','+"description"+','+"hoststatechangedate"+','+"ishostonline"+','+
                        "createts"+','+"modifyts"+','+"create_user_id"+','+"modify_user_id"+','+"terminal_desc"+','+"group_name"+','+"terminal_type"+'\n'
            }
            else {
                def row = rows[i]
                cells = row.split(',')

                Pattern p = Pattern.compile("[!@#%&*\$+=|<>?{}\\[\\]~]")
                Pattern p1 = Pattern.compile("[a-zA-Z]")

                Matcher m = p.matcher(row)
                Matcher m1 = p1.matcher(cells[6].toString())

                boolean hasSpecialCharecter = m.find()
                boolean hasAlphabet = m1.find()


                if (hasSpecialCharecter || hasAlphabet || cells[2].length() > 8 || cells[3].length() > 12){

                    def reference = "{\n" +
                            "  \"store_id\" : \""+cells[0]+"\",\n" +
                            "  \"brand\" : \""+cells[1]+"\",\n" +
                            "  \"group_id\" : \""+cells[2]+"\",\n" +
                            "  \"terminal_id\" : \""+cells[3]+"\",\n" +
                            "  \"company_id\" : \""+cells[4]+"\",\n" +
                            "  \"description\" : \""+cells[5]+"\",\n" +
                            "  \"hoststatechangedate\" : \""+cells[6]+"\",\n" +
                            "  \"ishostonline\" : \""+cells[7]+"\",\n" +
                            "  \"terminal_desc\" : \""+cells[12]+"\",\n" +
                            "  \"group_name\" : \""+cells[13]+"\",\n" +
                            "  \"terminal_type\" : \""+cells[14]+"\",\n" +
                            "  \"createts\" : \""+cells[8]+"\",\n" +
                            "  \"modifyts\" : \""+cells[9]+"\",\n" +
                            "  \"create_user_id\" : \""+cells[10]+"\",\n" +
                            "  \"modify_user_id\" : \""+cells[11]+"\"\n" +
                            "}"

                    def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                            "VALUES ('"+"DMSEXCEP001"+
                            "','"+"Special Char/Alphabet"+
                            "','"+"Special character or Alphabet found"+
                            "','"+"TerminalStatus"+
                            "','"+"dms_storeTerminalDataInterface"+
                            "','"+reference+
                            "','"+cells[8]+
                            "','"+cells[9]+
                            "','"+"Nifi"+
                            "','"+"Nifi"+"');"

                    try {
                        SQL.mydb.withTransaction {
                            SQL.mydb.execute(query);
                            // results = "{True}"
                            log.info("")
                        }
                    } catch (Exception e) {
                        log.info("\n Error Loading data for "+ query.toString() + " Error is "+e.toString());
                        log.info("........................................");
                    }

                }
                else if (cells[0]=="" || cells[2]=="" || cells[3]=="" || cells[12]=="" || cells[13]==""){
                    def reference = "{\n" +
                            "  \"store_id\" : \""+cells[0]+"\",\n" +
                            "  \"brand\" : \""+cells[1]+"\",\n" +
                            "  \"group_id\" : \""+cells[2]+"\",\n" +
                            "  \"terminal_id\" : \""+cells[3]+"\",\n" +
                            "  \"company_id\" : \""+cells[4]+"\",\n" +
                            "  \"description\" : \""+cells[5]+"\",\n" +
                            "  \"hoststatechangedate\" : \""+cells[6]+"\",\n" +
                            "  \"ishostonline\" : \""+cells[7]+"\",\n" +
                            "  \"terminal_desc\" : \""+cells[12]+"\",\n" +
                            "  \"group_name\" : \""+cells[13]+"\",\n" +
                            "  \"terminal_type\" : \""+cells[14]+"\",\n" +
                            "  \"createts\" : \""+cells[8]+"\",\n" +
                            "  \"modifyts\" : \""+cells[9]+"\",\n" +
                            "  \"create_user_id\" : \""+cells[10]+"\",\n" +
                            "  \"modify_user_id\" : \""+cells[11]+"\"\n" +
                            "}"

                    def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                            "VALUES ('"+"DMSEXCEP002"+
                            "','"+"Missing Field(s)"+
                            "','"+"Some fields are missing"+
                            "','"+"TerminalStatus"+
                            "','"+"dms_storeTerminalDataInterface"+
                            "','"+reference+
                            "','"+cells[8]+
                            "','"+cells[9]+
                            "','"+"Nifi"+
                            "','"+"Nifi"+"');"

                    try {
                        SQL.mydb.withTransaction {
                            SQL.mydb.execute(query);
                            log.info("")
                        }
                    } catch (Exception e) {
                        log.info("\n Error Loading data for "+ query.toString() + " Error is "+e.toString());
                        log.info("........................................");
                    }
                }
                else{
                    def outputRows = cells[0]+','+cells[1]+','+cells[2]+','+cells[3]+','+cells[4]+','+cells[5]+ ','+cells[6]+','+
                            cells[7]+','+cells[8]+','+cells[9]+','+cells[10]+','+cells[11]+','+cells[12]+','+cells[13]+','+cells[14]+'\n'

                    outputData = outputData + outputRows;
                }

            }

        }

        outputStream.write(outputData.getBytes(StandardCharsets.UTF_8))
        outputData = ''
        log.info("Code runs Successfully")

    }
    catch(e) {
        log.info("Error during processing of validate.groovy "+e)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}