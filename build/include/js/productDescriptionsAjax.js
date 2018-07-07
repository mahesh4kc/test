var productDescriptions;
var styleIDProductDescriptions="#productDescriptions";
function loadProductDescriptions(contextName){	
var rowCount=1;
if(productTypeRowCount!=null){rowCount = productTypeRowCount};
//unbind
	for(i=0;i<rowCount;i++){
		unbindProductDescriptions(i);
	}
//bind
	var url =  contextName+"/productDescription.do?method=LOADALLPRODUCTS";
	$("#productDescriptionsDiv").load(url, function (response) {
		productDescriptions = response.split(ajaxStringDelimiter);
			for(i=0;i<rowCount;i++){			
				bindProductDescriptions(i);	
			}   
	});  
}
//unbind
function unbindProductDescriptions(rowCount){
	unbindStyleIDProductDescriptions = styleIDProductDescriptions+parseInt(rowCount);
	$(unbindStyleIDProductDescriptions).unbind().autocomplete(productDescriptions);
}
//bind
function bindProductDescriptions(rowCount){
	bindStyleIDProductDescriptions = styleIDProductDescriptions+parseInt(rowCount);
	$(bindStyleIDProductDescriptions).autocomplete(productDescriptions,{
		multiple: true,
		mustMatch: false,
		autoFill: true
	}); 
}