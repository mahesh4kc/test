function isNewPasswordSame(){
	var password = document.getElementById('password').value;
	var duplicatePassword = document.getElementById('duplicatePassword').value;
	if(password != duplicatePassword){
		alert("New Passwords does not match");
		return false;
	}
}

function clickApply(){
	isNewPasswordSame();
}