import java.nio.charset.*
import java.util.logging.Logger

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_StoreTerminalDataWebServToDB");

def fail = false
String store_id, group_id, terminal_id, company_id, description, hoststatechangedate,
        ishostonline, terminal_desc, terminal_type, group_name, createts, modifyts, brand, modify_user_id

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        store_id = flowFile.getAttribute('sql.args.1.value').toString()
        brand = flowFile.getAttribute('sql.args.2.value').toString()
        group_id = flowFile.getAttribute('sql.args.3.value').toString()
        terminal_id = flowFile.getAttribute('sql.args.4.value').toString()
        company_id = flowFile.getAttribute('sql.args.5.value').toString()
        description = flowFile.getAttribute('sql.args.6.value').toString()
        hoststatechangedate = flowFile.getAttribute('sql.args.7.value').toString()
        ishostonline = flowFile.getAttribute('sql.args.8.value').toString()
        terminal_desc = flowFile.getAttribute('sql.args.13.value').toString()
        group_name = flowFile.getAttribute('sql.args.14.value').toString()
        terminal_type = flowFile.getAttribute('sql.args.15.value').toString()
        //createts = flowFile.getAttribute('sql.args.9.value').toString()
        modifyts = flowFile.getAttribute('sql.args.10.value').toString()
        modify_user_id = flowFile.getAttribute('sql.args.12.value').toString()


        def query = "UPDATE public.dms_store_terminal_data\n" +
                "SET group_id='"+group_id+"', brand='"+brand+"', company_id='"+company_id+"', description='"+description+"', hoststatechangedate='"+hoststatechangedate+"', ishostonline='"+ishostonline+"'," +
                "modifyts='"+modifyts+"', terminal_desc='"+terminal_desc+"', group_name='"+group_name+"', terminal_type='"+terminal_type+"', modify_user_id='"+modify_user_id+"'\n" +
                "WHERE terminal_id='"+terminal_id+"';"

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
        log.info("Error during processing of validate.groovy "+e)
        session.transfer(inputStream, REL_FAILURE)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}