$(document).ready( function () {
 
   
		
 } );
 
 function sampleAjaxCall() {
	 var objChkBoxTable  = $('#mcomListTable').DataTable( {
			ajax: 'http://localhost:8080/getMcomList', 
			columns: [ 
				{ "data": "div" },
				{ "data": "sellingChannel" },
				{ "data": "fulfillmentMethod" },
				{ "data": "shipReqReductionDays" },
				{ "data": "transitReductionDays" },
				{ "data": "lastUpdatedTS" },
				{ "data": "lastUpdatedUserId" } 
			],			
			"bLengthChange": false,  
			"paging":   false,
			"ordering": false,
			"info":     false,
			"rowCallback": function( row, data, index ) {
			  $('td', row).css('color', '#00000F');
			}  
 	} ); 
		 
	//on selection of a row in the mcomListTable table...
	$('#mcomListTable tbody').on( 'click', 'tr', function () {
		if ( $(this).hasClass('selected') ) {
			$(this).removeClass('selected');
		}
		else {
			objChkBoxTable.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');

			//var tmpData=$(this).find('td:first').html();
			  var dataRow= objChkBoxTable.row( this ).data();
			//show the PopUp panel with details...
			//console.log(" clicked for record id " + dataRow.dayType );
			 $('#orderDate').val( dataRow.orderDate );	
			 $('#orderTime').val( dataRow.orderTime );
			 $('#dayType').val( dataRow.dayType );
			 $('#cutoffTime').val( dataRow.cutoffTime );
			 
			 $('#shipReductionDays').val( dataRow.shipReqReductionDays );
			 $('#transitReductionDays').val( dataRow.transitReductionDays );	
			 
		}
	} );  
	 
	 
 }