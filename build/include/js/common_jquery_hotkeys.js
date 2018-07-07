$(document).keydown(function(e){                                 
    //alert(code);
    if(e.ctrlKey){
	     var code = e.which || e.keyCode;
	     switch ( code )
	    	{
                  case 97:
               	   addRow('dataTable') 
                    return false;
                    case 65:
                   	 addRow('dataTable')
                    return false;
                    case 68:                    	 
                   	 deleteRow('dataTable')
                    return false;
                    case 100:                    	 
                   	 deleteRow('dataTable')
                  default:
                    break;
	     }
    } 
});