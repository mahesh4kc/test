package com.bank.validation;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.bank.business.validation.GenericBusinessValidation;
import com.bank.form.ProductDescriptionForm;

public class ProductDescriptionValidation extends GenericBusinessValidation{	
	
	protected void addErrorForProductDescriptionRequired(ActionErrors errors){
		errors.add("errors.required", new ActionError("errors.required","Product Description"));
	}
	
	protected boolean validateProductDescriptionForSearch(ActionErrors errors,  ProductDescriptionForm productDescriptionForm){
		System.out.println("validateProductDescriptionForSearch starts");
		boolean productDescriptionValidated= false;
		if(productDescriptionForm.getProductDescription() != null && 
				productDescriptionForm.getProductDescription().length() < 1){
			addErrorForProductDescriptionRequired(errors);
			productDescriptionValidated = true;
		 }
		System.out.println("validateProductDescriptionForSearch starts");
		return productDescriptionValidated;
	}

	
	protected boolean validateProductDescriptionForSave(ActionErrors errors,  ProductDescriptionForm productDescriptionForm){
		System.out.println("validateProductDescriptionForSave starts");
		boolean ProductDescriptionValidated = false;
		validateRequiredFieldsForProductDescription(errors, productDescriptionForm);
		if(errors != null && errors.size() < 1) {
			ProductDescriptionValidated = true;			
		}
		System.out.println("validateProductDescriptionForSave ends");
		return ProductDescriptionValidated;
	}
	
	private void validateRequiredFieldsForProductDescription(ActionErrors errors,  ProductDescriptionForm productDescriptionForm){
		System.out.println("validateRequiredFieldsForProductDescription starts");
		/*int index = 1;
		for(ProductDescriptionBO productDescriptionBO : productDescriptionForm.getProductDescriptionList()){
			if( productDescriptionBO == null 
					|| ( productDescriptionBO != null && productDescriptionBO.getProductDescription().length() < 1)){
				addErrors(errors, "Product Description" + "errors.required" +" [ " + index + "]");	
				index = index + 1;
			}
		}*/
		System.out.println("validateRequiredFieldsForProductDescription ends");
	}	
}
