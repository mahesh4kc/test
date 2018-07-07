package com.bank.business.validation;

import java.sql.SQLException;

import org.apache.struts.action.ActionErrors;

import com.bank.dao.CustomerDAO;
import com.bank.form.CustomerForm;
import com.bank.validation.CustomerValidation;

public class CustomerBusinessValidation extends CustomerValidation{
	
	CustomerDAO customerDAO;
	public CustomerBusinessValidation(String jndiName){
		customerDAO = new CustomerDAO(jndiName);
	}
		
	public void validateCustomerDetailsForSearch(ActionErrors errors, CustomerForm customerForm,String jndiName) throws SQLException{
		if(validateSearchCustomer(errors, customerForm)){
			if(!customerDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(!customerDAO.customerExistsByCustomerId(customerForm.getCustomerID())){
				addErrorForCustomerDoesNotExists(errors);
			}			
		}		
	}
	
	public void validateCustomerDetailsForCreate(ActionErrors errors, CustomerForm customerForm,String jndiName) throws SQLException{
		if(!validateCreateCustomer(errors, customerForm)){
			System.out.println("customerForm.getCreateDuplicateCustomer()"+customerForm.getCreateDuplicateCustomer());
			if(!customerDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(customerDAO.customerExistsByCustomerName(customerForm.getName())){
				if (customerForm.getCreateDuplicateCustomer() == null ||
						(customerForm.getCreateDuplicateCustomer()!= null 
						&& !customerForm.getCreateDuplicateCustomer().equalsIgnoreCase("on"))){
					addErrorForCustomerAlreadyExistsCreateDuplicate(errors);
			}
				
			}
		}
		
	}	

	public void validateCustomerDetailsForUpdate(ActionErrors errors, CustomerForm customerForm,String jndiName) throws SQLException{
		if(validateSearchCustomer(errors, customerForm)){
			if(!customerDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(!customerDAO.customerExistsByCustomerId(customerForm.getCustomerID())){
				addErrorForCustomerDoesNotExists(errors);
			}else if(customerDAO.customerExistsByCustomerId(customerForm.getCustomerID())){
				validateCreateCustomer(errors, customerForm);
			}			
		}		
	}
	
	public void validateCustomerNameForSearch(ActionErrors errors, String customerName,String jndiName) throws SQLException{
		if(validRequiredFieldsForCustomer(errors, customerName)){
			if(!customerDAO.isConnectionExists(jndiName)){
				addErrorForDatabaseNotFound(errors);
			}else if(!customerDAO.customerExistsByCustomerName(customerName)){
				addErrorForCustomerDoesNotExists(errors);
			}			
		}		
	}
	
	private boolean validRequiredFieldsForCustomer(ActionErrors errors, String customerName){	
		boolean customerNameExists = true;
		if(customerName == null || customerName.length() < 1 ){
			addErrorForCustomerNameRequired(errors);
			customerNameExists = false;
		}	
		return  customerNameExists;
	}
	
}
