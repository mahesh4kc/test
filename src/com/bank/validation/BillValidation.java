package com.bank.validation;

import org.apache.struts.action.ActionErrors;

import com.bank.bo.BillDetailBO;
import com.bank.business.validation.GenericBusinessValidation;
import com.bank.form.BillForm;
import com.bank.util.DateUtil;

public class BillValidation extends GenericBusinessValidation{	
	
	public boolean validateSaveBill(ActionErrors errors, BillForm billForm){
		boolean billValidated = false;
		validateRequiredFieldsForBill(errors, billForm);
		validateDateFieldsForBill(errors, billForm);
		//validateRequiredFieldsForBillDetails(errors, billForm);
		if(errors != null && errors.size() < 1) {
			billValidated = true;			
		}
		return billValidated;
	}
	private void validateRequiredFieldsForBillDetails(ActionErrors errors, BillForm billForm){
		int counter = 1;
		for(BillDetailBO billDetailBO : billForm.getBillDetailList()){
			if( billDetailBO == null 
					|| ( billDetailBO.getProductQuantity() != null && billDetailBO.getProductQuantity().intValue() == 0) 
					|| 	billDetailBO.getProductDescription().length() < 1){
				addErrors(errors, "Quantity & Product Description[ " + counter + "]", "errors.required");	
				counter = counter + 1;
			}
		}
	}
	
	private void validateDateFieldsForBill(ActionErrors errors, BillForm billForm){
		if(!DateUtil.isValidDate(billForm.getBillHeaderBO().getBillDate())) {			
			addErrors(errors, "Bill Date ", "errors.date");		
		}
	}
	
	private void validateDateFieldsForBillRedemption(ActionErrors errors, BillForm billForm){
		if(!DateUtil.isValidDate(billForm.getBillHeaderBO().getRedemptionDate())) {			
			addErrors(errors, "Redemption Date ", "errors.date");		
		}
	}
	
	private void validateRequiredFieldsForBill(ActionErrors errors, BillForm billForm){
		if(billForm.getBillHeaderBO().getBillSerial().length() < 1 || 
				billForm.getBillHeaderBO().getBillNumber().intValue() == 0 ) {
			addErrors(errors, "Bill serial or Bill number ", "errors.required");		
		}if(billForm.getBillHeaderBO().getBillDate().length() < 1) {
			addErrors(errors, "Bill Date", "errors.required");		
		}if(billForm.getBillHeaderBO().getCustomerID().intValue() == 0 ) {
			addErrors(errors, "Customer ID", "errors.required");		
		}if(billForm.getBillHeaderBO().getProductTypeNumber() == 0) {
			addErrors(errors, "Article", "errors.required");		
		}if(billForm.getBillHeaderBO().getAmount().intValue() == 0 ) {
			addErrors(errors, "Amount", "errors.required");		
		}if(billForm.getBillHeaderBO().getAmountInWords().length() < 1 ) {
			addErrors(errors, "Amount in words", "errors.required");		
		}/*if(billForm.getBillHeaderBO().getGrams().doubleValue() == 0.0 ) {
			addErrors(errors, "Total grams", "errors.required");		
		}if(billForm.getBillHeaderBO().getPresentValue().intValue() == 0 ) {
			addErrors(errors, "Present value", "errors.required");		
		}*/if(billForm.getBillHeaderBO().getMonthlyIncome().intValue() == 0 ) {
			addErrors(errors, "Monthly income", "errors.required");		
		}if(billForm.getBillHeaderBO().getRateOfInterest().doubleValue() == 0.0 ) {
			addErrors(errors, "Rate of interest", "errors.required");		
		}
	}
	
	public boolean validateBillRedemption(ActionErrors errors, BillForm billForm){
		boolean billRedemptionValidated = false;
		if(billForm.getBillHeaderBO().getRedemptionStatus().length() > 0){
			validateRequiredFieldsForRedemption(errors, billForm);
			validateDateFieldsForBillRedemption(errors, billForm);
			validateRedemptionStatusForRedemption(errors, billForm);
		}
		
		if(errors != null && errors.size() < 1) {
			billRedemptionValidated = true;			
		}
		return billRedemptionValidated;
	}
	
	private void validateRequiredFieldsForRedemption(ActionErrors errors, BillForm billForm){
		if(	billForm.getBillHeaderBO().getRedemptionDate().length() < 1  ) {
			addErrors(errors, "Redemption Date ", "errors.required");		
		}/*if(billForm.getBillHeaderBO().getRedemptionInterest().intValue() == 0 ) {
			addErrors(errors, "Redemption interest ", "errors.required");		
		}if(billForm.getBillHeaderBO().getRedemptionTotal().intValue() == 0 ) {
			addErrors(errors, "Redemption Total", "errors.required");		
		}*/if(billForm.getBillHeaderBO().getRedemptionStatus().length() < 1  ) {
			addErrors(errors, "Redemption Status ", "errors.required");		
		}
	}
	
	private void validateRedemptionStatusForRedemption(ActionErrors errors, BillForm billForm){
		if(billForm.getBillHeaderBO().getRedemptionStatus().equalsIgnoreCase("O")  ) {
			addErrors(errors, "Redemption Status ", "error.billStillOpen");		
		}
	}
}
