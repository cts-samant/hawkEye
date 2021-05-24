import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import groovy.json.*
import groovy.json.JsonOutput

def lsURL = "${grafanaURL}"+"0" ;

retrieveDataFromService( lsURL, false); 

public String retrieveDataFromService(psURL, bWriteToFile){ 
 
 	log.info("\n\n\n ********* psURL="+ psURL);
	def http = new HTTPBuilder( psURL ) ;

	http.request(GET,TEXT) { req ->
		headers.'authorization' = '${AuthKey}'

		response.success = { resp, reader ->  
			def jsonContent =resp.entity.content.text ;
			//log.info("\n Response \n " +  JsonOutput.prettyPrint(jsonContent)  );
			def jsonSlurper = new JsonSlurper();
			def respObj = jsonSlurper.parseText(jsonContent);
			
			if(bWriteToFile){
				//log.info("\n -----writing to file \n" + respObj.dashboard  );
				File file = new File( "dashboards/"+respObj.dashboard.title +".json");
				file.write(new JsonBuilder( respObj.dashboard ).toPrettyString() ) ;
			}	
			else{
					respObj.each { recordData -> 
					if(recordData.type =='dash-folder'){
						log.info('folder :' +  recordData.id);
						retrieveDataFromService("${grafanaURL}"+recordData.id,  false);
					}
					else{
							log.info('file :' + recordData.uid + '\n');
							retrieveDataFromService("${dashboardURL}" +recordData.uid, true);
						}
					} 
				}

		} 
	} 
}


