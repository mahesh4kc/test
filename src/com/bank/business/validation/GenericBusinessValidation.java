package com.bank.business.validation;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.bank.validation.GenericValidation;


public class GenericBusinessValidation extends GenericValidation{
	
	protected void addErrorForCustomerDoesNotExists(ActionErrors errors){
		errors.add("error.customerNotFound", new ActionError("error.customerNotFound"));
	}
	
	protected void addErrorForCustomerAlreadyExists(ActionErrors errors){
		errors.add("error.customerAlreadyExists", new ActionError("error.customerAlreadyExists"));
	}
	
	protected void addErrorForCustomerAlreadyExistsCreateDuplicate(ActionErrors errors){
		errors.add("error.customerAlreadyExistsCreateDuplicate", new ActionError("error.customerAlreadyExistsCreateDuplicate"));
	}
	
	
	protected void addErrorForProductTypeDoesNotExists(ActionErrors errors){
		errors.add("error.productTypeNotFound", new ActionError("error.productTypeNotFound"));
	}
	protected void addErrorForBillSerialNumberAlreadyExists(ActionErrors errors){
		errors.add("error.billSerialNumberAlreadyExists", new ActionError("error.billSerialNumberAlreadyExists"));
	}
	protected void addErrorForBillAlreadyRedemped(ActionErrors errors){
		errors.add("error.billAlreadyRedemped", new ActionError("error.billAlreadyRedemped"));
	}
	
	protected void addErrorForBillAlreadyRedemedCancelledAuctioned(ActionErrors errors){
		errors.add("error.billAlreadyRedCanAuc", new ActionError("error.billAlreadyRedCanAuc"));
	}
	
	protected void addErrorForBillSerialNumberDoesNotExists(ActionErrors errors){
		errors.add("error.billSerialNumberNotFound", new ActionError("error.billSerialNumberNotFound"));
	}
	protected void addErrorForUserDoesNotExists(ActionErrors errors){
		errors.add("error.userNotFound", new ActionError("error.userNotFound"));
	}
	
	protected void addErrorForPasswordInvalid(ActionErrors errors){
		errors.add("error.passwordInvalid", new ActionError("error.passwordInvalid"));
	}
	
	
	protected void addErrorForDatabaseNotFound(ActionErrors errors){
		errors.add("error.databaseNotFound", new ActionError("error.databaseNotFound"));
	}
	
	protected void addErrorForUserAlreadyExists(ActionErrors errors){
		errors.add("error.userIdAlreadyExists", new ActionError("error.userIdAlreadyExists"));
	}
	
	/*protected void addErrorForDatabaseAlreadyExists(ActionErrors errors){
		errors.add("error.shopAlreadyExists", new ActionError("error.shopAlreadyExists"));
	}*/
	
	protected void addErrorForPhoneNumberInteger(ActionErrors errors){
		errors.add("errors.integer", new ActionError("errors.integer","Phone number"));
	}
	
	protected void addErrorForInvalidEmailAddress(ActionErrors errors){
		errors.add("errors.email", new ActionError("errors.email","Entered email id"));
	}
	
	protected void addErrorForEmailAlreadyExists(ActionErrors errors){
		errors.add("error.emailIdAlreadyExists", new ActionError("error.emailIdAlreadyExists"));
	}
	protected void addErrorForMobileNumberAlreadyExists(ActionErrors errors){
		errors.add("error.mobileNoAlreadyExists", new ActionError("error.mobileNoAlreadyExists"));
	}
	
	protected void addErrorForUserIdEmailIdIncorrect(ActionErrors errors){
		errors.add("error.userIdEmailIdDoesNotMatch", new ActionError("error.userIdEmailIdDoesNotMatch"));
	}
	
}
