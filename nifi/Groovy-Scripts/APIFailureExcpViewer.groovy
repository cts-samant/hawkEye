import java.nio.charset.*
import java.time.LocalDateTime
import java.util.logging.Logger

def flowFile = session.get()
if(!flowFile) return

def fail = false
String auth
LocalDateTime date = LocalDateTime.now()
String date_time = date.format("yyyy-MM-dd hh:mm:ss.SSS")
Logger log = Logger.getLogger("DMS_StoreTerminalDataWebServToDB");

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        def api = flowFile.getAttribute('API').toString()

        def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                "VALUES ('DMSEXCEP003', 'Authorization Failed', 'Authorization failed during API call', 'TerminalStatus', 'dms_storeTerminalDataInterface', '"+api+"', '"+date_time+"', '"+date_time+"', 'Nifi', 'Nifi')"
        log.info(query)

        try {
            SQL.mydb.withTransaction {
                SQL.mydb.execute(query);
            }
        } catch (Exception e) {
            log.info(e.toString())
        }

        outputStream.write("Success".getBytes(StandardCharsets.UTF_8))

    }
    catch(e) {
        log.info("Error during processing of StoreTerminalData.groovy "+e)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}