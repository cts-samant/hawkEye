import groovy.json.JsonSlurper
import org.apache.commons.io.IOUtils
import java.nio.charset.*
import java.time.LocalDateTime
import java.util.logging.Logger
import java.util.regex.Matcher
import java.util.regex.Pattern

def flowFile = session.get()
if(!flowFile) return
Logger log = Logger.getLogger("DMS_StoreMasterDataToDB");
def outputData = ""
def fail = false
def pfcntr, dma_code, dma_desc, brand, opertnl_stat_code, legal_entity_name,
    city, state, county, zipcode, country, latitude, longitude, modifyts, createts, createUserId, modifyUserId

LocalDateTime date = LocalDateTime.now()
String date_time = date.format("yyyy-MM-dd hh:mm:ss.SS")


json_sup = new JsonSlurper()

flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        def recordIn = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
        json_response = json_sup.parseText(recordIn)

        Pattern p = Pattern.compile("[!@#%*\$+=|<>?~]")
        Pattern p1 = Pattern.compile("[a-zA-Z]")

        pfcntr = json_response.pfcntr
        dma_code = json_response.dma_code
        dma_desc = json_response.dma_desc.toString().replaceAll("[\"|']", "")
        brand = json_response.brand.toString().replace("null","DBI")
        opertnl_stat_code = json_response.opertnl_stat_code
        legal_entity_name = json_response.legal_entity_name.toString().replaceAll("[\"|']", "")
        city = json_response.city
        state = json_response.state
        county = json_response.county
        zipcode = json_response.zipcode
        country = json_response.country
        latitude = json_response.latitude
        longitude = json_response.longitude
        modifyts = json_response.modifyts
        createts = json_response.createts
        createUserId = json_response.create_user_id
        modifyUserId = json_response.modify_user_id

        Matcher m = p.matcher(recordIn)
        Matcher m1 = p1.matcher(pfcntr)
        Matcher m2 = p1.matcher(dma_code)
        Matcher m3 = p1.matcher(zipcode)
        //Matcher m4 = p1.matcher(latitude)
        //Matcher m5 = p1.matcher(longitude)

        boolean containsSpecialCharecter = m.find()
        boolean containsAlphabet1 = m1.find()
        boolean containsAlphabet2 = m2.find()
        boolean containsAlphabet3 = m3.find()
        //boolean containsAlphabet4 = m4.find()
        //boolean containsAlphabet5 = m5.find()
        //log.info(latitude+" <> "+longitude)


        if (containsSpecialCharecter || containsAlphabet1 || containsAlphabet2 || containsAlphabet3) {

            String reference = "{\n" +
                    "  \"pfcntr\" : \""+pfcntr+"\",\n" +
                    "  \"dma_code\" : \""+dma_code+"\",\n" +
                    "  \"dma_desc\" : \""+dma_desc+"\",\n" +
                    "  \"brand\" : \""+brand+"\",\n" +
                    "  \"opertnl_stat_code\" : \""+opertnl_stat_code+"\",\n" +
                    "  \"legal_entity_name\" : \""+legal_entity_name+"\",\n" +
                    "  \"city\" : \""+city+"\",\n" +
                    "  \"state\" : \""+state+"\",\n" +
                    "  \"county\" : \""+county+"\",\n" +
                    "  \"zipcode\" : \""+zipcode+"\",\n" +
                    "  \"country\" : \""+country+"\",\n" +
                    "  \"latitude\" : \""+latitude+"\",\n" +
                    "  \"longitude\" : \""+longitude+"\",\n" +
                    "  \"modifyts\" : \""+modifyts+"\",\n" +
                    "  \"createts\" : \""+createts+"\",\n" +
                    "  \"create_user_id\" : \""+createUserId+"\",\n" +
                    "  \"modify_user_id\" : \""+modifyUserId+"\"\n" +
                    "}"

            def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                    "VALUES ('"+"DMSEXCEP001"+
                    "','"+"Special Char/Alphabet"+
                    "','"+"Special character or Alphabet found"+
                    "','"+"StoreMaster"+
                    "','"+"dms_storeMasterInterface"+
                    "','"+reference+
                    "','"+date_time+
                    "','"+date_time+
                    "','"+"Nifi"+
                    "','"+"Nifi"+"');"
            log.info(query)
            try {
                SQL.mydb.withTransaction {
                    SQL.mydb.execute(query)
                }
            } catch (Exception e) {
                log.info("\n Error Loading data for "+ query);
                log.info("........................................");
            }
            outputData = "[]"
            outputStream.write(outputData.getBytes(StandardCharsets.UTF_8))
        }
        else if(latitude == null || longitude == null || pfcntr == null || dma_code == null)  {

            String reference = "{\n" +
                    "  \"pfcntr\" : \""+pfcntr+"\",\n" +
                    "  \"dma_code\" : \""+dma_code+"\",\n" +
                    "  \"dma_desc\" : \""+dma_desc+"\",\n" +
                    "  \"brand\" : \""+brand+"\",\n" +
                    "  \"opertnl_stat_code\" : \""+opertnl_stat_code+"\",\n" +
                    "  \"legal_entity_name\" : \""+legal_entity_name+"\",\n" +
                    "  \"city\" : \""+city+"\",\n" +
                    "  \"state\" : \""+state+"\",\n" +
                    "  \"county\" : \""+county+"\",\n" +
                    "  \"zipcode\" : \""+zipcode+"\",\n" +
                    "  \"country\" : \""+country+"\",\n" +
                    "  \"latitude\" : \""+latitude+"\",\n" +
                    "  \"longitude\" : \""+longitude+"\",\n" +
                    "  \"modifyts\" : \""+modifyts+"\",\n" +
                    "  \"createts\" : \""+createts+"\",\n" +
                    "  \"create_user_id\" : \""+createUserId+"\",\n" +
                    "  \"modify_user_id\" : \""+modifyUserId+"\"\n" +
                    "}"

            def query = "INSERT INTO public.dms_common_error_table (error_code, error_type, error_description, error_context, interface, reference, createts, modifyts, create_user_id, modify_user_id)\n" +
                    "VALUES ('"+"DMSEXCEP002"+
                    "','"+"Missing Field(s)"+
                    "','"+"Some fields are missing"+
                    "','"+"StoreMaster"+
                    "','"+"dms_storeMasterInterface"+
                    "','"+reference+
                    "','"+date_time+
                    "','"+date_time+
                    "','"+"Nifi"+
                    "','"+"Nifi"+"');"
            log.info(query)
            try {
                SQL.mydb.withTransaction {
                    SQL.mydb.execute(query)
                }
            } catch (Exception e) {
                log.info("\n Error Loading data for "+ query);
                log.info("........................................");
            }
            outputData = "[]"
            outputStream.write(outputData.getBytes(StandardCharsets.UTF_8))
        }
        else {

            outputData = "{\n" +
                    "  \"pfcntr\" : \""+pfcntr+"\",\n" +
                    "  \"dma_code\" : \""+dma_code+"\",\n" +
                    "  \"dma_desc\" : \""+dma_desc+"\",\n" +
                    "  \"brand\" : \""+brand+"\",\n" +
                    "  \"opertnl_stat_code\" : \""+opertnl_stat_code+"\",\n" +
                    "  \"legal_entity_name\" : \""+legal_entity_name+"\",\n" +
                    "  \"city\" : \""+city+"\",\n" +
                    "  \"state\" : \""+state+"\",\n" +
                    "  \"county\" : \""+county+"\",\n" +
                    "  \"zipcode\" : \""+zipcode+"\",\n" +
                    "  \"country\" : \""+country+"\",\n" +
                    "  \"latitude\" : \""+latitude+"\",\n" +
                    "  \"longitude\" : \""+longitude+"\",\n" +
                    "  \"modifyts\" : \""+modifyts+"\",\n" +
                    "  \"createts\" : \""+createts+"\",\n" +
                    "  \"create_user_id\" : \""+createUserId+"\",\n" +
                    "  \"modify_user_id\" : \""+modifyUserId+"\"\n" +
                    "}"

            outputStream.write(outputData.getBytes(StandardCharsets.UTF_8))
        }

    }
    catch(e) {
        log.info("Error during processing of storeMaster.groovy "+e)
        fail=true
    }
} as StreamCallback)
if(fail){
    session.transfer(flowFile, REL_FAILURE)
    fail = false
} else {
    session.transfer(flowFile, REL_SUCCESS)
}