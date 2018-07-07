package com.bank.helper;

import com.bank.bo.UserAuthenticationBO;
import com.bank.form.LoginForm;

public class UserAuthenticationHelper {
	 // Move the User Authentication Business object to Login Form
	public static void moveUserAuthenticationBoToUserAuthenticationForm(LoginForm loginForm , 
			 UserAuthenticationBO  userAuthenticationBO){
		 loginForm.setLoginID(userAuthenticationBO.getLoginID());
		 loginForm.setPassword(userAuthenticationBO.getPassword());
		 loginForm.setEmailID(userAuthenticationBO.getEmailID());
	 }
		
	 // Move the User Authentication Form to User Authentication Business object 
	 public static void moveUserAuthenticationFormToUserAuthenticationBo(LoginForm loginForm, 
			 UserAuthenticationBO  userAuthenticationBO){	 
		 userAuthenticationBO.setLoginID(loginForm.getLoginID());
		 userAuthenticationBO.setPassword(loginForm.getPassword());
		 userAuthenticationBO.setEmailID(loginForm.getEmailID());
	}
}
