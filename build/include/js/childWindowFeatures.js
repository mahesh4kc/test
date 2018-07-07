function hideMenuForPopupChildWindow(){
	if(window.opener != null){
		 document.getElementById("bankMenu").style.display = "none";
		 document.getElementById("close").style.display = "block";
	}
}
function closeChildWindow(){
	self.close();
}