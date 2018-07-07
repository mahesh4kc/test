package com.bank.validation;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.bank.business.validation.GenericBusinessValidation;
import com.bank.form.CustomerForm;

public class CustomerValidation extends GenericBusinessValidation{
	private void addErrorForCustomerIDInteger(ActionErrors errors){
		errors.add("errors.integer", new ActionError("errors.integer","Customer ID"));
	}
	
	private void addErrorForCustomerIDRequired(ActionErrors errors){
		errors.add("errors.required", new ActionError("errors.required","Customer ID"));
	}
	
	public void addErrorForCustomerNameRequired(ActionErrors errors){
		errors.add("errors.required", new ActionError("errors.required","Customer Name"));
	}
	
	
	
	private void addErrorForValidEmailID(ActionErrors errors){
		errors.add("errors.email", new ActionError("errors.email","Mail ID"));
	}
	
	private void addErrorForPincodeInteger(ActionErrors errors){
		errors.add("errors.integer", new ActionError("errors.integer","Pincode"));
	}	
	
	private void addErrorForMobileNumberInteger(ActionErrors errors){
		errors.add("errors.integer", new ActionError("errors.integer","Mobile number"));
	}
	
	public boolean validateSearchCustomer(ActionErrors errors, CustomerForm customerForm){
		boolean customerValidated = false;
		if(customerForm.getCustomerID().length() < 1) {
			addErrorForCustomerIDRequired(errors);			
		}else if(customerForm.getCustomerID().length() > 0 && !this.isValidInteger(customerForm.getCustomerID())){
			addErrorForCustomerIDInteger(errors);			
		}else
		{
			customerValidated = true;
		}
		return customerValidated;
	}
	
	public boolean validateCreateCustomer(ActionErrors errors, CustomerForm customerForm){
		boolean errorExists = false;
		validRequiredFieldsForCustomer(errors, customerForm);
		validIntegerLongFieldsForCustomer(errors, customerForm);
		
		if(errors != null && errors.size() > 0){
			errorExists =true ;
		}
		
		return errorExists;
	}
	
	private void validRequiredFieldsForCustomer(ActionErrors errors, CustomerForm customerForm){		
		if(customerForm.getName() == null || customerForm.getName().length() < 1 ){
			addErrorForCustomerNameRequired(errors);
		}
	
	}
	private void validIntegerLongFieldsForCustomer(ActionErrors errors, CustomerForm customerForm){
		
		if(customerForm.getPincode().length() > 0 && !this.isValidInteger(customerForm.getPincode())){
			addErrorForPincodeInteger(errors);
			
		}
		
		if(customerForm.getPhoneNo().length() > 0 && !this.isValidLong(customerForm.getPhoneNo())){
			addErrorForPhoneNumberInteger(errors);			
		}
		
		if(customerForm.getMobileNo().length() > 0 && !this.isValidLong(customerForm.getMobileNo())){
			addErrorForMobileNumberInteger(errors);
		}
		
	
		if(customerForm.getMailID().length() > 0 && (!customerForm.getMailID().contains("@")
				|| (!customerForm.getMailID().toUpperCase().contains(".COM")) && 
				!customerForm.getMailID().toUpperCase().contains(".CO.IN"))){
			addErrorForValidEmailID(errors);
		}
	}
}
