
//Bill Ajax - not used at this time
/*
function loadBillUsingSerialNo(){
		var serialLetter = document.getElementById("billSerial").value;
		var serialNumber = document.getElementById("billNumber").value;
	if (serialLetter!= null && serialNumber!= null){		
		var billDetails; 
		billDetails = $.ajax({ 
							url: "/bank/bill.do?method=LOADBILLDETAILS&serialLetter="+serialLetter+"&serialNumber="+serialNumber, 
							async: false 
							}).responseText; 
		billDetails = billDetails.split(ajaxStringDelimiter);
		document.getElementById("billSequence").value = billDetails[0];
		document.getElementById("billDate").value = billDetails[1];
		document.getElementById("customerID").value = billDetails[2];
		document.getElementById("careOf").value = billDetails[3];
		loadCustomerDetailForBillUsingCustomerIDValue(billDetails[4]);
		//document.getElementById("customerID").value = billDetails[4];
		document.getElementById("amount").value = billDetails[5];
		document.getElementById("amountInWords").value = billDetails[6];
		document.getElementById("grams").value = billDetails[7];
		document.getElementById("presentValue").value = billDetails[8];
		document.getElementById("monthlyIncome").value = billDetails[9];
		document.getElementById("rateOfInterest").value = billDetails[10];
		if(document.getElementById("redemptionDate") != null){
			document.getElementById("redemptionDate").value = billDetails[11];
			document.getElementById("redemptionInterest").value = billDetails[12];
			document.getElementById("redemptionTotal").value = billDetails[13];
			//document.getElementById("redemptionStatus").value = billDetails[14];;
		}
		
	}
	
}
*/


//Bill distinct serial
var contextName;
function loadDistinctSerial(context){

	var billSerial;
	contextName=context;
	//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do"
	//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do?projNumber=" + document.getElementById("projNumber").value
	var url =  contextName+"/bill.do?method=LOADBILLDISTINCTSERIAL";
	$("#billSerialDiv").load(url, function (response) {
	        billSerial = response.split(ajaxStringDelimiter);
	      //  alert(customerNames[0]);
	       
	        $( "#billSerial" ).autocomplete({
	            source: billSerial
	          });
	        
	        /*$().ready(function() { 
	                $("#billSerial").autocomplete(billSerial);
	});*/
	}); 	
}

//Bill distinct serial no
function loadDistinctSerialNo(){

	var billSerialNo;
	//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do"
	//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do?projNumber=" + document.getElementById("projNumber").value
	var url =  contextName+"/bill.do?method=LOADBILLDISTINCTSERIALNO";
	$("#billSerialNoDiv").load(url, function (response) {
	        billSerialNo = response.split(ajaxStringDelimiter);
	      //  alert(customerNames[0]);
	        $( "#billNumber" ).autocomplete({
	            source: billSerialNo
	          });
	       /* $().ready(function() { 
	                $("#billNumber").autocomplete(billSerialNo);
	});*/
	}); 	
}

//To load customer details

function loadCustomerDetailForBillUsingCustomerID(obj){
	if (obj!= null && obj.value.length > 0){
		var customerID = obj.value;
		var customerDetails;
		var customerDetailsUrl=contextName+"/customer.do?method=LOADCUSTOMERDETAILSFORBILL&customerID="+customerID;
		customerDetails = $.ajax({ 
							url: (customerDetailsUrl), 
							async: false 
							}).responseText; 
		customerDetails = customerDetails.split(ajaxStringDelimiter);
		populateCustomerDetailsForBill(customerDetails);
	}
}


/*
function loadCustomerDetailForBillUsingCustomerIDValue(value){
	if (value.length > 0){
		var customerID = value;
		var customerDetails; 
		customerDetails = $.ajax({ 
							url: "/bank/customer.do?method=LOADCUSTOMERDETAILSFORBILL&customerID="+customerID, 
							async: false 
							}).responseText; 
		customerDetails = customerDetails.split(ajaxStringDelimiter);
		populateCustomerDetailsForBill(customerDetails);
	}
}
*/
function loadCustomerDetailForBillUsingCustomerName(obj){
	if (obj!= null && obj.value.length > 0){
		var customerName = obj.value;
		var customerDetails; 
		var customerDetailsUrl=contextName+"/customer.do?method=LOADCUSTOMERDETAILSFORBILL&customerName="+customerName;
		customerDetails = $.ajax({ 
							url: (customerDetailsUrl), 
							async: false 
							}).responseText; 
		customerDetails = customerDetails.split(ajaxStringDelimiter);
		populateCustomerDetailsForBill(customerDetails);
	}
}

//this is private function
function populateCustomerDetailsForBill(customerDetails){
if(customerDetails[0] !=null && customerDetails[0].length > 0){
	document.getElementById("customerNames").value = customerDetails[1];
	document.getElementById("customerID").value = customerDetails[0];
	document.getElementById("customerAddress").value = customerDetails[2];
}else{
	document.getElementById("customerNames").value = "";
	document.getElementById("customerID").value = "";
	document.getElementById("customerAddress").value = "";
}
	
}