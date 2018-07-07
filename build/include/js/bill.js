var contextName;
function openCustomerChildWindow(){
//var href = "/bank/jsp/searchMasterScreens.jsp?searchTableDetails=C&method=SEARCH";
var href = contextName+"/searchMasterScreen.do?method=SEARCH&searchTableDetails=C";
var obj = window.open(href);
//obj.document.getElementById("searchTableDetails").value = "C";
}
function openBillChildWindow(){
//var href = "/bank/jsp/searchMasterScreens.jsp?searchTableDetails=B&method=SEARCH";
var href = contextName+"/searchMasterScreen.do?method=SEARCH&searchTableDetails=B";
var obj = window.open(href);
//obj.document.getElementById("searchTableDetails").value = "B";
}

function calculatePresentValue(obj){
	var amountValue = document.getElementById("amount").value;
	//alert(amountValue);
	document.getElementById("presentValue").value = (parseInt(amountValue) + 100);
}
function validateDateField(obj){

var valid_date = validateDate(obj.value,'J','F');
alert(valid_date);
}

function billOnBodyLoad(context){
	contextName = context;
	bodyOnLoad('customerNames');
	loadDistinctSerial(contextName);
	loadDistinctSerialNo();
	loadCustomer(contextName);
	//passing default row counts
	loadProductDescriptions(contextName);        
}

 function ValGrams(){  
         var regEx = /^\d{1,3}\.\d{0,3}$/;  
        //var regEx = /^\d{2}\.\d{2}$/;  
        bValid = document.forms[0].grams.value.match(regEx);  
        if(!bValid){  
          alert('Grams incorrect, format supports (###,###)');  
          return false;           
        }  
        else return true;  
  }  
  
   function ValPresentValue(){  
         var regEx = /^\d{1}\.{0,1}\d{0,2}$/;  
        //var regEx = /^\d{2}\.\d{2}$/;  
        bValid = document.forms[0].rateOfInterest.value.match(regEx);  
        if(!bValid){  
          alert('Present value incorrect, format supports (#,##)');  
          return false;           
        }  
        else return true;  
  }  