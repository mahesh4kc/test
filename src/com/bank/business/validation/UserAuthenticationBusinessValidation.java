package com.bank.business.validation;

import java.sql.SQLException;

import org.apache.struts.action.ActionErrors;

import com.bank.dao.UserAuthenticationDAO;
import com.bank.form.LoginForm;
import com.bank.util.EmailValidator;
import com.bank.validation.UserAuthenticationValidation;

public class UserAuthenticationBusinessValidation extends UserAuthenticationValidation{
	
	UserAuthenticationDAO userAuthenticationDAO;
	public UserAuthenticationBusinessValidation(UserAuthenticationDAO userAuthenticationDAO1){
		this.userAuthenticationDAO = userAuthenticationDAO1;
	}
		
	public void validateUserAuthenticationDetailsForLogin(ActionErrors errors, LoginForm loginForm,String jndiName) throws SQLException{
		System.out.println("jndiName:"+jndiName);
		if(validateLoginDetails(errors, loginForm)){
			if(!this.userAuthenticationDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(!this.userAuthenticationDAO.userExists(loginForm.getLoginID(), loginForm.getPassword())){
				addErrorForUserDoesNotExists(errors);
			}			
		}		
	}
	
	public void validateUserAuthenticationDetailsForChangePassword(ActionErrors errors, LoginForm loginForm,String jndiName) throws SQLException{
	
	if(validateRequiredFieldsForChangePassword(errors, loginForm)
			&& validateLoginDetails(errors, loginForm)){
			if(!this.userAuthenticationDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(!this.userAuthenticationDAO.userExists(loginForm.getLoginID(), loginForm.getOldPassword())){
				addErrorForPasswordInvalid(errors);
			}			
		}		
	}
	
	public void validateUserRegistrationDetails(ActionErrors errors, LoginForm loginForm,String jndiName) throws SQLException{
		boolean isDatabaseConnectionExists = true, isLoginValid = true, isEmailValid = true, isMobileNumber = true;		
		if(validateRequiredFieldsForRegistration(errors, loginForm)){
			
			if((loginForm.getLoginID().length() > 0 && loginForm.getLoginID().length() < 7 ) || 
					(loginForm.getPassword().length() > 0 && loginForm.getPassword().length() < 7)){
				addErrors(errors, "errors.maxCharactersForUserIdAndPassword");
				isLoginValid = false;
			}
			
			if(loginForm.getEmailID().length() > 0 ){
				EmailValidator emailValidator = new EmailValidator();
				if(!emailValidator.validate(loginForm.getEmailID().trim())){
					addErrorForInvalidEmailAddress(errors);
					isEmailValid = false;
				}				
			}
			
			/*if(loginForm.getDatabaseName().length() > 0 ){
				 if(this.userAuthenticationDAO.databaseExists(loginForm.getDatabaseName())){				
					 addErrorForDatabaseAlreadyExists(errors);
				 }
			}	*/
			
			if(loginForm.getPhoneNumber().length() > 0 && !this.isValidLong(loginForm.getPhoneNumber())){							
				addErrorForPhoneNumberInteger(errors);
				isMobileNumber = false;
			}	
			
			if(!this.userAuthenticationDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
				isDatabaseConnectionExists = false;
			}
			
			if(isDatabaseConnectionExists){
				if(isLoginValid && this.userAuthenticationDAO.userExists(loginForm.getLoginID())){
					addErrorForUserAlreadyExists(errors);
					isLoginValid = false;
				}
				if(isEmailValid	&& this.userAuthenticationDAO.isEmailExists(loginForm.getEmailID().trim())){
					addErrorForEmailAlreadyExists(errors);
					isEmailValid = false;
				}
				if(isMobileNumber && this.userAuthenticationDAO.isMobileNumberExists(
															Long.parseLong(loginForm.getPhoneNumber().trim()))){
					addErrorForMobileNumberAlreadyExists(errors);
					isMobileNumber = false;
				}
			}
		}				
					
	}
	
		public void validateForgotPasswordDetails(ActionErrors errors, LoginForm loginForm,String jndiName) throws SQLException{		
			if(validateRequiredFieldsForForgotPassword(errors, loginForm) && validateEmailAddress(errors, loginForm)){					
				if(!this.userAuthenticationDAO.isConnectionExists(jndiName)){
					addErrorForDatabaseNotFound(errors);
				}else if(!this.userAuthenticationDAO.isUserIdEmailIdExists(loginForm.getLoginID(),loginForm.getEmailID())){
					addErrorForUserIdEmailIdIncorrect(errors);
				}
			}
		}
		
		public boolean validateEmailAddress(ActionErrors errors, LoginForm loginForm){
			boolean isEmailValid = true;
			if(!new EmailValidator().validate(loginForm.getEmailID().trim())){
				addErrorForInvalidEmailAddress(errors);
				isEmailValid = false;
			}
			return isEmailValid;
		}
}
