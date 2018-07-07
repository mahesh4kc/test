function closeCustomerChildWindow(primaryKeyValue,search2,search3,search4)
{
	//alert(primaryKeyValue);
	if(window.opener.document.getElementById("customerID") != null){
		window.opener.document.getElementById("customerID").value = primaryKeyValue;
	}
	if(window.opener.document.getElementById("customerNames") != null){
		var customerDetails;
		customerDetails = search2.split("/");
		//alert(customerDetails[0].substring(0, customerDetails[0].length-2));
		//alert(customerDetails[0].length);
		window.opener.document.getElementById("customerNames").value = customerDetails[0].substring(0, customerDetails[0].length-2);
	}
		if(window.opener.document.getElementById("customerAddress") != null){
		window.opener.document.getElementById("customerAddress").value = search3;
	}	
		
	//window.opener.document.getElementById("customerNames").value = window.document.getElementById("search2").innerHTML;
	//window.opener.document.getElementById("customerAddress").value = window.document.getElementById("search3").innerHTML;
	window.close();
	//window.opener.document.forms[0].submit();
	
}

function closeBillChildWindow(primaryKeyValue,search1,search2,search3,search4)
{
var billSerailNumber = search1.split(":");
var billSerial = billSerailNumber[0];
var billNumber = billSerailNumber[1];

	//alert(primaryKeyValue + ":" billSerial + ":" billNumber + ":" + search1+ ":" + search2+ ":" + search3+ ":" + search4);
	//bill.jsalert( billSerial + "-" + billNumber );
	if(window.opener.document.getElementById("billSequence") != null ){
		window.opener.document.getElementById("billSequence").value = primaryKeyValue;
	}
	if(window.opener.document.getElementById("billSerial") != null && billSerial != null){
		window.opener.document.getElementById("billSerial").value = billSerial;
	}
		if(window.opener.document.getElementById("billNumber") != null && billNumber != null){
		window.opener.document.getElementById("billNumber").value = billNumber;
	}	
		
	//window.opener.document.getElementById("customerNames").value = window.document.getElementById("search2").innerHTML;
	//window.opener.document.getElementById("customerAddress").value = window.document.getElementById("search3").innerHTML;
	window.close();
}

function bodyOnLoad(context){
	if(window.opener != null)
		 document.getElementById("bankMenu").style.display = "none";
	loadCustomer(context);
}

function navigaveBillR(billSerialNo,contextName){
	//alert(billSerialNo);
	var href = contextName+"/billRedemption.do?method=SEARCH&billSerialNo=" + billSerialNo;
   location.replace(href); 
 
	
	//window.open(href,'','target="_self"');
}
