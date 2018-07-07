
var styleIDProductDescriptions="#productDescriptions";
  
function loadProductDescriptions(contextName){
	var url =  contextName+"/productDescription.do?method=LOADALLPRODUCTS";	
	$("#productDescriptionsDiv").load(url, function (response) {
		productDescriptions = response.split(ajaxStringDelimiter);				
	});	
}


