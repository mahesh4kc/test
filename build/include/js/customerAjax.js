var contextName;
function loadCustomer(context){
var customerNames;
contextName = context;
//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do"
//var url = "https://localhost:12002/Procon2Web/projectAjaxSearchPre.do?projNumber=" + document.getElementById("projNumber").value
var url =  contextName+"/customer.do?method=LOADALLCUSTOMERS";
$("#customers").load(url, function (response) {
        customerNames = response.split(ajaxStringDelimiter);
      //  alert(customerNames[0]);
        $().ready(function() { 
                $("#customerNames").autocomplete(customerNames);
});
});  
}

function loadCustomerDetailForCustomerName(obj){
	if (obj!= null && obj.value.length > 0){
		var customerName = obj.value;
		var customerURL=contextName+"/customer.do?method=LOADCUSTOMERDETAILSFORCUSTOMERNAME&customerName="+customerName;
		var customerDetails; 
		customerDetails = $.ajax({ 
							url: (customerURL), 
							async: false 
							}).responseText; 
		customerDetails = customerDetails.split(ajaxStringDelimiter);
		//alert(customerDetails.length);
		if(customerDetails[1] !=null){
			document.getElementById("customerNames").value = customerDetails[0];
			document.getElementById("customerID").value = customerDetails[1];
			document.getElementById("address").value = customerDetails[2];
			document.getElementById("street").value = customerDetails[3];
			document.getElementById("area").value = customerDetails[4];
			document.getElementById("district").value = customerDetails[5];
			document.getElementById("state").value = customerDetails[6];
			document.getElementById("country").value = customerDetails[7];
			document.getElementById("pincode").value = customerDetails[8];
			document.getElementById("mailID").value = customerDetails[9];
			document.getElementById("phoneNo").value = customerDetails[10];
			document.getElementById("mobileNo").value = customerDetails[11];
			document.getElementById("relationName").value = customerDetails[12];
			if(customerDetails[13]=="W")
				document.forms[0].relationShip[0].checked = true;
			if(customerDetails[13]=="S")
				document.forms[0].relationShip[1].checked = true;
			if(customerDetails[13]=="D")
				document.forms[0].relationShip[2].checked = true;
			if(customerDetails[13]=="H")
				document.forms[0].relationShip[3].checked = true;
			if(customerDetails[13]=="F")
			document.forms[0].relationShip[4].checked = true;				
		}
			
	}
}


