package com.bank.validation;

import org.apache.struts.action.ActionErrors;

import com.bank.business.validation.GenericBusinessValidation;
import com.bank.form.LoginForm;

public class UserAuthenticationValidation extends GenericBusinessValidation{
	
	public boolean validateLoginDetails(ActionErrors errors, LoginForm loginForm){
		boolean loginValidated = false;
		validateRequiredFieldsForLogin(errors, loginForm);
		//validateRequiredFieldsForBillDetails(errors, billForm);
		if(errors != null && errors.size() < 1) {
			loginValidated = true;			
		}
		return loginValidated;
	}
	
	private void validateRequiredFieldsForLogin(ActionErrors errors, LoginForm loginForm){
		if(loginForm.getLoginID().length() < 1 &&  
				loginForm.getPassword().length() < 1 ) {
			addErrors(errors, "Login ID and Password ", "errors.required");		
		}else if(loginForm.getLoginID().length() < 1 ) {
			addErrors(errors, "Login ID ", "errors.required");		
		}else if(loginForm.getPassword().length() < 1 ) {
			addErrors(errors, "Password ", "errors.required");		
		}		
	}
	
	public boolean validateRequiredFieldsForChangePassword(ActionErrors errors, LoginForm loginForm){
		boolean changePasswordValidated= true;
		if(loginForm.getLoginID().length() < 1 ) {
			addErrors(errors, "Login ID ", "errors.required");	
			changePasswordValidated= false;
		}
		if( loginForm.getOldPassword().length() < 1 
				&& loginForm.getOldPassword().length() < 1) {
			addErrors(errors, "Old password ", "errors.required");	
			changePasswordValidated= false;
		}
		if(loginForm.getPassword().length() < 1 ) {
			addErrors(errors, "New Password ", "errors.required");
			changePasswordValidated= false;
		}/*if(loginForm.getUserName().length() < 1) {
			addErrors(errors, "Proprietor", "errors.required");	
			changePasswordValidated= false;
		}*/
		if(loginForm.getDuplicatePassword().length() < 1 ) {
			addErrors(errors, "Re-enter new password ", "errors.required");	
			changePasswordValidated= false;
		}
		if(!loginForm.getDuplicatePassword().equalsIgnoreCase(loginForm.getPassword()) ) {
			addErrors(errors, "error.newPasswordDoesNotMatch");	
			changePasswordValidated= false;
		}
		return changePasswordValidated;
	}
	
	public boolean validateRequiredFieldsForRegistration(ActionErrors errors, LoginForm loginForm){
		boolean changePasswordValidated= true;
		if(loginForm.getLoginID().length() < 1 ) {
			addErrors(errors, "Login ID ", "errors.required");	
			changePasswordValidated= false;
		}
		if(loginForm.getUserName().length() < 1) {
			addErrors(errors, "First Name", "errors.required");	
			changePasswordValidated= false;
		}
		if( loginForm.getPassword().length() < 1) {
			addErrors(errors, "Password ", "errors.required");	
			changePasswordValidated= false;
		}		
		if(loginForm.getDuplicatePassword().length() < 1 ) {
			addErrors(errors, "Re-enter new password ", "errors.required");	
			changePasswordValidated= false;
		}
		if(!loginForm.getDuplicatePassword().equalsIgnoreCase(loginForm.getPassword()) ) {
			addErrors(errors, "error.newPasswordDoesNotMatch");	
			changePasswordValidated= false;
		}
		if( loginForm.getEmailID().length() < 1 ) {
			addErrors(errors, "Email id ", "errors.required");	
			changePasswordValidated= false;
		}
		if( loginForm.getPhoneNumber().length() < 1 ) {
			addErrors(errors, "Phone Number ", "errors.required");	
			changePasswordValidated= false;
		}
		return changePasswordValidated;
	}
	
	public boolean validateRequiredFieldsForForgotPassword(ActionErrors errors, LoginForm loginForm){
		boolean forgotPasswordValidated= true;
		if(loginForm.getLoginID().length() < 1 ) {
			addErrors(errors, "Login ID ", "errors.required");	
			forgotPasswordValidated= false;
		}if(loginForm.getEmailID().length() < 1 ) {
			addErrors(errors, "Email id ", "errors.required");	
			forgotPasswordValidated= false;
		}
		return forgotPasswordValidated;
	}
	
}
