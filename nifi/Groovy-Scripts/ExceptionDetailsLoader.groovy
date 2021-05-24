import groovy.json.JsonBuilder
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.logging.Logger
import java.util.concurrent.TimeUnit;


def ff=session.get()
if (ff == null) {
    return
}
Logger log = Logger.getLogger("DunkinMasterDataCSVToDB");
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
LocalDateTime date = LocalDateTime.now();

String[] row
def dataQuery,errQuery ,strInputData, file, limit = 2
List<String> dataList = new ArrayList<String>()
List<String> errorList = new ArrayList<String>()
def results = []

try{
    for(int fileNum=0; fileNum < limit ; fileNum++) {
        file = new File("C:\\Data_Items\\Demo\\dunkins_master" + fileNum + ".csv")
        strInputData = file.text
        file.delete()
        String[] strRows = strInputData.split("\n");
        log.info("\n\n  Total Lines : " + strRows.size() + "\n");
        for (int rowCount = 1; rowCount < strRows.size(); rowCount++) {
            if (strRows[rowCount].contains("&")) {
                row = strRows[rowCount].split(',')
                dataQuery = "INSERT INTO public.\"ErrorTable\"(\"PFCNTR\", \"DMA_CODE\", \"DMA_DESC\", \"OPEN_BRANDS_DESC\", \"OPERTNL_STAT_CODE\", \"LEGAL_ENTITY_NAME\", \"LATITUDE\", \"LONGITUDE\", \"CreateTs\", \"ModifyTs\")\n" +
                        "\tVALUES ('" + row[0] + "','" + row[1] + "','" + row[2] + "','" + row[3] + "','" + row[4] + "','" + row[5] + "','" + row[6] + "','" + row[7] + "','" + date + "','" + date + "');"
                dataList.add(dataQuery)
            } else {
                row = strRows[rowCount].split(',')
                errQuery = "INSERT INTO public.\"DataTable\"(\"PFCNTR\", \"DMA_CODE\", \"DMA_DESC\", \"OPEN_BRANDS_DESC\", \"OPERTNL_STAT_CODE\", \"LEGAL_ENTITY_NAME\", \"LATITUDE\", \"LONGITUDE\", \"CreateTs\", \"ModifyTs\")\n" +
                        "\tVALUES ('" + row[0] + "','" + row[1] + "','" + row[2] + "','" + row[3] + "','" + row[4] + "','" + row[5] + "','" + row[6] + "','" + row[7] + "','" + date + "','" + date + "');"
                errorList.add(errQuery)
            }
        }
        try {
            SQL.mydb.withTransaction {
                SQL.mydb.withBatch(100) { stmtObj ->
                    dataList.each {
                        query-> stmtObj.addBatch(query)
                    }
                }
            }
            SQL.mydb.withTransaction {
                SQL.mydb.withBatch(100) { stmtObj ->
                    errorList.each {
                        query-> stmtObj.addBatch(query)
                    }
                }
            }
            dataList.clear()
            errorList.clear()
            results = "{True}"
        }
        catch(exec){
            log.info(exec.toString())
            results = "{False}"
        }
        if (fileNum+1 < limit){
            TimeUnit.SECONDS.sleep(50);
        }
    }

}
catch(ex){
    log.info(ex.toString())
    results = "{False}"
}

if (results == "{True}"){
    ff.write("UTF-8") { writer ->
        new JsonBuilder(results).writeTo(writer)
    }
    //transfer to success
    REL_SUCCESS << ff
}
else {
    ff.write("UTF-8") { writer ->
        new JsonBuilder(results).writeTo(writer)
    }
    //transfer to failure
    REL_FAILURE << ff
}