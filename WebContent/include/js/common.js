//creation and deletion of dynamic rows in javascript
var productTypeRowCount;
function addRow(tableID) {	
	var table = document.getElementById(tableID);            
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);             
	var colCount = table.rows[rowCount-1].cells.length;	
	for(var i=0; i<colCount; i++) { 
		var newcell = row.insertCell(i);
		//This is to generate all the previous controls of all the tr tags 
		//and the index needs to be passed for the collection
		newcell.innerHTML = getInnerHtmlNewIndex(table.rows[rowCount-1].cells[i].innerHTML,rowCount-1);					
		//alert(newcell.childNodes); 
		switch(newcell.childNodes[0].type) { 
			case "text": 
				newcell.childNodes[0].value = ""; 
				break; 
			case "checkbox": 
				newcell.childNodes[0].checked = false; 
				break; 
			case "select-one": 
				newcell.childNodes[0].selectedIndex = 0; 
				break; 
			case "hidden": 
				newcell.childNodes[0].value = 0; 
				break; 
		} 
	}
	productTypeRowCount=rowCount;	
	
} 
function deleteRow(tableID) { 	            
	try {            
		var table = document.getElementById(tableID);           
		var rowCount = table.rows.length;           
		for(var i=0; i<rowCount; i++) {                
		var row = table.rows[i];                
		var chkbox = row.cells[0].childNodes[0];
		var hd1 = row.cells[1].childNodes[0];		              
		if(null != chkbox && true == chkbox.checked) {		
		//delete only that are created using add row button and will not delete that is already existing in database	
			if(hd1.type == "hidden" && hd1.value != 0){
				alert("Just select and click save to delete that are stored in database."); 				
				break; 
			}                
			if(rowCount <= 2) { 	                     
				alert("Cannot delete all the rows.");                        
				break;                    
			} 	                 
		table.deleteRow(i); 	                   
		rowCount--;                    
		i--; 	               
		}           
	}           
	}catch(e) {               
		alert(e); 	           
	} 
	    
	}
//  This is to get the new index
	function getInnerHtmlNewIndex(strInnerHtml,index)
	{			
		index= index +1;			
		var strextract;				
		var newInnerHtml;	
		//alert(strInnerHtml);	
		newInnerHtml = strInnerHtml.replace(/[[0-9]+]/g, '[' + index + ']');		
		newInnerHtml = newInnerHtml.replace(/id=productDescriptions[0-9]/, 'id=productDescriptions' + (index) + '');		
		//alert(newInnerHtml);
		//alert(productDescriptions);		
		//addJavaScriptToLoadProductDescriptions(index);		
		return newInnerHtml;
	}




function addJavaScriptToLoadProductDescriptions(index){
	//alert(index);
	//$("#productDescriptions[2]").autocomplete(productDescriptions);
	//$("#productDescriptions[2]").autocomplete(productDescriptions);
}
function bodyOnLoad(styleIdName){
	document.getElementById(styleIdName).focus();
}