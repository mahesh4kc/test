var contextName;
var productDescriptions;
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

function billOnBodyLoad(context,billSource){
	contextName = context;
	billAndBillRedemptionFocus(billSource);		
	loadDistinctSerial(contextName);
	loadDistinctSerialNo();
	loadCustomer(contextName);
	//passing default row counts
	loadProductDescriptions(contextName);   
}

//For bill focus should be on customer name
//For bill redemption it has to be on bill number and after search it has to be on redemption date
function billAndBillRedemptionFocus(billSource){
	billSource == "BM" ? bodyOnLoad('customerNames') : 
		((document.getElementById("amount").value != null && document.getElementById("amount").value > 0 ) ? 
					bodyOnLoad('redemptionDate') : bodyOnLoad('billNumber'));
	
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
   
   //Load Product Descriptions 0th element

   $(function() {	   
       function split( val ) {
         return val.split( /,\s*/ );
       }
       function extractLast( term ) {
         return split( term ).pop();
       }
    
       $( "#productDescriptions0" )
         // don't navigate away from the field on tab when selecting an item
         .bind( "keydown", function( event ) {
           if ( event.keyCode === $.ui.keyCode.TAB &&
               $( this ).data( "ui-autocomplete" ).menu.active ) {
             event.preventDefault();
           }
         })
         .autocomplete({
           minLength: 0,
           source: function( request, response ) {
             // delegate back to autocomplete, but extract the last term
             response( $.ui.autocomplete.filter(
           		  productDescriptions, extractLast( request.term ) ) );
           },
           focus: function() {
             // prevent value inserted on focus
             return false;
           },
           select: function( event, ui ) {
             var terms = split( this.value );
             // remove the current input
             terms.pop();
             // add the selected item
             terms.push( ui.item.value );
             // add placeholder to get the comma-and-space at the end
             terms.push( "" );
             this.value = terms.join( ", " );
             return false;
           }
         });
     });

   
   