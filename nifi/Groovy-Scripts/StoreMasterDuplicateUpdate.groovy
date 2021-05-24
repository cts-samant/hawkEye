import java.nio.charset.*
import java.util.logging.Logger

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_StoreMasterDataToDB");

def fail = false
String pfcntr, dma_code, dma_desc, brand, opertnl_stat_code, legal_entity_name, city_name,
        state_code, cnty_name, zip_code, cntry, latitude, longitude, modifyts, createts, modifyuserid

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        pfcntr = flowFile.getAttribute('sql.args.1.value').toString()
        dma_code = flowFile.getAttribute('sql.args.2.value').toString()
        dma_desc = flowFile.getAttribute('sql.args.3.value').toString()
        brand = flowFile.getAttribute('sql.args.4.value').toString()
        opertnl_stat_code = flowFile.getAttribute('sql.args.5.value').toString()
        legal_entity_name = flowFile.getAttribute('sql.args.6.value').toString()
        city_name = flowFile.getAttribute('sql.args.7.value').toString()
        state_code = flowFile.getAttribute('sql.args.8.value').toString()
        cnty_name = flowFile.getAttribute('sql.args.9.value').toString()
        zip_code = flowFile.getAttribute('sql.args.10.value').toString()
        cntry = flowFile.getAttribute('sql.args.11.value').toString()
        latitude = flowFile.getAttribute('sql.args.12.value').toString()
        longitude = flowFile.getAttribute('sql.args.13.value').toString()
        //createts = flowFile.getAttribute('sql.args.14.value').toString()
        modifyts = flowFile.getAttribute('sql.args.15.value').toString()
        modifyuserid = flowFile.getAttribute('sql.args.17.value').toString()

        def query = "UPDATE public.dms_storemaster\n" +
                "SET dma_code='"+dma_code+"', dma_desc='"+dma_desc+"', pfcntr='"+pfcntr+"', opertnl_stat_code='"+opertnl_stat_code+"', latitude='"+latitude+"'," +
                " longitude='"+longitude+"', brand='"+brand+"', legal_entity_name='"+legal_entity_name+"', modifyts='"+modifyts+"', modify_user_id='"+modifyuserid+"',"+
                " city='"+city_name+"', state='"+state_code+"', zipcode='"+zip_code+"', country='"+cntry+"', county='"+cnty_name+"'\n" +
                "WHERE pfcntr='"+pfcntr+"';"

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
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}