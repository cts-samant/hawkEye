import java.nio.charset.*
import java.util.logging.Logger

def flowFile = session.get()
if(!flowFile) return

def fail = false
String acc_no, store_id, host_name, terminal_type, group_id, group_name, software_name, version, createts, modifyts, modifyuserid
Logger log = Logger.getLogger("DMS_SoftwareVersionDataCSVToDB");

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        acc_no = flowFile.getAttribute('sql.args.1.value').toString()
        store_id = flowFile.getAttribute('sql.args.2.value').toString()
        host_name = flowFile.getAttribute('sql.args.3.value').toString()
        terminal_type = flowFile.getAttribute('sql.args.4.value').toString()
        group_id = flowFile.getAttribute('sql.args.5.value').toString()
        group_name = flowFile.getAttribute('sql.args.6.value').toString()
        software_name = flowFile.getAttribute('sql.args.7.value').toString()
        version = flowFile.getAttribute('sql.args.8.value').toString()
        //createts = flowFile.getAttribute('sql.args.9.value').toString()
        modifyts = flowFile.getAttribute('sql.args.12.value').toString()
        modifyuserid = flowFile.getAttribute('sql.args.11.value').toString()

        def query = "UPDATE public.dms_host_software_version\n" +
                "SET acc_no='"+acc_no+"', store_id='"+store_id+"', terminal_type='"+terminal_type+"', group_id='"+group_id+"', group_name='"+group_name+"', \"version\"='"+version+"', modifyts='"+modifyts+"', modify_user_id='"+modifyuserid+"'\n" +
                "WHERE host_name='"+host_name+"' AND software_name='"+software_name+"';"
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