//var gScheduleApiUrl = "http://3.130.52.82:8085/";
//var gScheduleApiUrl = "http://3.143.186.114:8085/";
var gScheduleApiUrl="http://52.14.187.131:8085/";
//var gScheduleApiUrl = "https://4799m7zzqd.execute-api.us-east-2.amazonaws.com/Stage/hwapi";
var gCreateScheduleApiUrl = gScheduleApiUrl + "createSchedule";
//var gCreateScheduleApiUrl = gScheduleApiUrl;
var gGetScheduleApiUrl = gScheduleApiUrl + "getAllschedules";
var gUpdateScheduleApiUrl = gScheduleApiUrl + "updateSchedule";
var gSecurityTokenUrl ="/api/datasources/proxy/4";


var refreshInterval = 3000;

var gProgressBarIndex = 0;
var giProgressBarTimer = 0;

//60 secs is 600. 100 secs =1000
function startProgressBar(psTimer) {
  gProgressBarIndex = 0;
  giProgressBarTimer = psTimer;
  displayProgressBar();
}

function displayProgressBar() {

  //console.log (gProgressBarIndex);
  if (gProgressBarIndex < 100) {
    gProgressBarIndex = gProgressBarIndex + 1;
    $(".progress_bar").val(gProgressBarIndex);

    setTimeout("displayProgressBar()", giProgressBarTimer);
  }

}

function convertToDataTableFormat(respDataObj) {

  var colCount = respDataObj.columns.length;
  var rowCount = respDataObj.rows.length;
  var dataRecordRow, jsonName;
  //console.log("\n \n\n ------------ rowCount, colCount Counts " + rowCount +" : " + colCount);

  var dataObj = new Array();

  for (i = 0; i < rowCount; i++) {
    dataRecordRow = respDataObj.rows[i];
    var rowObject = new Object();

    for (j = 0; j < colCount; j++) {
      jsonName = respDataObj.columns[j].text;
      rowObject[jsonName] = dataRecordRow[j];
    }
    dataObj.push(rowObject);
  }
  //console.log("\n dataTable ::: \n" + JSON.stringify(dataObj, undefined, 2) );

  return dataObj;
}