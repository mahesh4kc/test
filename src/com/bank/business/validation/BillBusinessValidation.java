package com.bank.business.validation;

import java.sql.SQLException;

import org.apache.struts.action.ActionErrors;

import com.bank.bo.BillHeaderBO;
import com.bank.dao.BillHeaderDAO;
import com.bank.dao.CustomerDAO;
import com.bank.dao.ProductTypeDAO;
import com.bank.form.BillForm;
import com.bank.validation.BillValidation;

public class BillBusinessValidation extends BillValidation{
	
	CustomerDAO customerDAO;
	ProductTypeDAO productTypeDAO;
	BillHeaderDAO billHeaderDAO;
	public BillBusinessValidation(String jndiName){
		customerDAO = new CustomerDAO(jndiName);
		productTypeDAO = new ProductTypeDAO(jndiName); 
		billHeaderDAO = new BillHeaderDAO(jndiName);
	}
/*	
	private void addErrorForCustomerDoesNotExists(ActionErrors errors){
		errors.add("error.customerNotFound", new ActionError("error.customerNotFound"));
	}
	*/
	public void validateBillDetailsForSave(ActionErrors errors, BillForm billForm) throws SQLException,Exception{
		if(validateSaveBill(errors, billForm)){
			if(!customerDAO.customerExistsByCustomerId(billForm.getBillHeaderBO().getCustomerID().toString())){
				addErrorForCustomerDoesNotExists(errors);
			}if(!productTypeDAO.productTypeExists(billForm.getBillHeaderBO().getProductTypeNumber().toString())){
				addErrorForProductTypeDoesNotExists(errors);
			}if(billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber())){
				BillHeaderBO billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), 
																							billForm.getBillHeaderBO().getBillNumber());	
				//addErrorForBillSerialNumberAlreadyExists(errors);	
				if(billHeaderBO != null && !billForm.isCanUpdateBill()){				
					addErrorForBillAlreadyRedemedCancelledAuctioned(errors);
				}				
			}		
		}		
	}	
	
	public void validateBillDetailsForSearch(ActionErrors errors, BillForm billForm,String jndiName) throws SQLException,Exception{
		if(billForm.getBillHeaderBO().getBillSerial().length() < 1 || 
				billForm.getBillHeaderBO().getBillNumber().intValue() == 0 ) {
			addErrors(errors, "Bill serial or Bill number ", "errors.required");		
		}else {
			BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			if(!billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), 
					billForm.getBillHeaderBO().getBillNumber().intValue())){
				addErrorForBillSerialNumberDoesNotExists(errors);
			}
		}
		
			
	}
	
	
	public void validateBillRedemptionDetails(ActionErrors errors, BillForm billForm) throws SQLException,Exception{
		if(validateBillRedemption(errors, billForm)){
			if(!billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber())){
				addErrorForBillSerialNumberAlreadyExists(errors);
			}if(billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber())){
				BillHeaderBO billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), 
																							billForm.getBillHeaderBO().getBillNumber());	
				if(billHeaderBO != null && billHeaderBO.getRedemptionStatus().equalsIgnoreCase("C")){
					addErrorForBillAlreadyRedemped(errors);
				}				
			}				
		}		
	}	
}
