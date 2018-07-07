package com.bank.business.validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionErrors;

import com.bank.bo.ProductDescriptionBO;
import com.bank.dao.ProductDescriptionDAO;
import com.bank.form.ProductDescriptionForm;
import com.bank.validation.ProductDescriptionValidation;

public class ProductDescriptionBusinessValidation extends ProductDescriptionValidation{
	
	
	ProductDescriptionDAO productDescriptionDAO;
	
	public ProductDescriptionBusinessValidation(String jndiName){
		productDescriptionDAO = new ProductDescriptionDAO(jndiName);
	}
/*	
	private void addErrorForCustomerDoesNotExists(ActionErrors errors){
		errors.add("error.customerNotFound", new ActionError("error.customerNotFound"));
	}
	*/
	public void validateBusinessProductDescriptionForSave(ActionErrors errors, ProductDescriptionForm productDescriptionForm) throws SQLException,Exception{
		System.out.println("validateBusinessProductDescriptionForSave starts");
		//List<ProductDescriptionBO> dbProductDescriptionBOList = null;		
		List<ProductDescriptionBO> screenProductDescriptionBOList = null;
		
		//this list will have records after marked for deletion in the screen
		//this list will also remove the empty product descriptions
		screenProductDescriptionBOList = getProductDescriptionBOListAfterDeletion(productDescriptionForm.getProductDescriptionList());
		productDescriptionForm.setProductDescriptionList(screenProductDescriptionBOList);
		//dbProductDescriptionBOList = productDescriptionDAO.executeAllProducts();
		
		if(validateProductDescriptionForSave(errors, productDescriptionForm)){
			//check is there any duplicate product description exists
			checkProductDescriptionIsDuplicated(errors, screenProductDescriptionBOList);
		}
		System.out.println("validateBusinessProductDescriptionForSave ends");
	}	
	
	public void validateBusinessProductDescriptionForSearch(ActionErrors errors, ProductDescriptionForm productDescriptionForm) throws SQLException,Exception{
		System.out.println("validateBusinessProductDescriptionForSearch starts");
		
		if(validateProductDescriptionForSearch(errors, productDescriptionForm)){
			
		}
		
		System.out.println("validateBusinessProductDescriptionForSearch ends");
	}	
	
	private boolean checkProductDescriptionIsDuplicated(ActionErrors errors,List<ProductDescriptionBO> screenProductDescriptionBOList){		
		boolean duplicateProductDescriptionExists = false;	
		duplicateProductDescriptionExists = duplicateExistsForScreenList(screenProductDescriptionBOList, errors);
			if(!duplicateProductDescriptionExists){
				duplicateExistsForScreenAndDatabaseList(screenProductDescriptionBOList, errors);
			}
		return duplicateProductDescriptionExists;
	}

	/* 
	 * This method is to check whether duplicate product description int the screen list
	 */
	private boolean duplicateExistsForScreenList(List<ProductDescriptionBO> screenProductDescriptionBOList,ActionErrors errors){
		boolean duplicateProductDescriptionExists=false;
		int index = 1;
		for(ProductDescriptionBO productDescriptionBO : screenProductDescriptionBOList){	
			int duplicateCounter=0;
			for(ProductDescriptionBO productDescriptionBOCopy : screenProductDescriptionBOList){				
					if(productDescriptionBO != null && productDescriptionBOCopy != null && 
							productDescriptionBO.getProductDescription()!= null && productDescriptionBOCopy.getProductDescription()!= null
							&& productDescriptionBO.getProductDescription().length() > 0 && productDescriptionBOCopy.getProductDescription().length() > 0){
						if(productDescriptionBO.getProductDescription().equalsIgnoreCase(productDescriptionBOCopy.getProductDescription())){
							duplicateCounter = duplicateCounter + 1;
						}
						if(duplicateCounter > 1){
							System.out.println(productDescriptionBO.getProductDescription());
							System.out.println(productDescriptionBOCopy.getProductDescription());
							//addErrors(errors, "Product Description already exists for [" + index +"]");
							addErrors(errors, index +"", "error.productDescriptionAlreadyExists");
							duplicateProductDescriptionExists = true;
							break;
						}					
					}
			}
			index = index + 1;
		}
		return duplicateProductDescriptionExists;
	}
	
	/* 
	 * This method is to check whether duplicate product description exists even if we have filter criteria 
	 */
	private boolean duplicateExistsForScreenAndDatabaseList(List<ProductDescriptionBO> screenProductDescriptionBOList,ActionErrors errors){
		boolean duplicateProductDescriptionExists=false;
		int index = 1;
		List<ProductDescriptionBO> dbProductDescriptionBOList;
		try{
			dbProductDescriptionBOList = productDescriptionDAO.executeAllProducts();
			for(ProductDescriptionBO productDescriptionBO : screenProductDescriptionBOList){	
				int duplicateCounter=0;
				for(ProductDescriptionBO dbProductDescriptionBO : dbProductDescriptionBOList){				
						if(productDescriptionBO != null && dbProductDescriptionBO != null && 
								productDescriptionBO.getProductNo().intValue() == 0 &&
								productDescriptionBO.getProductDescription()!= null && dbProductDescriptionBO.getProductDescription()!= null
								&& productDescriptionBO.getProductDescription().length() > 0 && dbProductDescriptionBO.getProductDescription().length() > 0){
							if(productDescriptionBO.getProductDescription().equalsIgnoreCase(dbProductDescriptionBO.getProductDescription())){
								duplicateCounter = duplicateCounter + 1;
							}
							if(duplicateCounter > 0){
								System.out.println(productDescriptionBO.getProductDescription());
								System.out.println(dbProductDescriptionBO.getProductDescription());
								//addErrors(errors, "Product Description already exists for [" + index +"]");
								addErrors(errors, productDescriptionBO.getProductDescription() +"", "error.productDescriptionAlreadyExists");
								duplicateProductDescriptionExists = true;
								break;
							}					
						}
				}
				index = index + 1;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}	catch(Exception ex1){
			ex1.printStackTrace();
		}	
		return duplicateProductDescriptionExists;
	}
	
	private List<ProductDescriptionBO> getProductDescriptionBOListAfterDeletion(List<ProductDescriptionBO> screenProductDescriptionBOList){
		System.out.println("getProductDescriptionBOListAfterDeletion starts");		
		List<ProductDescriptionBO> productDescriptionBOListAfterDeletion = new ArrayList<ProductDescriptionBO>();
		for(ProductDescriptionBO productDescriptionBO : screenProductDescriptionBOList){
			if( productDescriptionBO != null && productDescriptionBO.getProductDescription().length() > 0 && 
					(productDescriptionBO.getChecked() == false ||
					(productDescriptionBO.getChecked() == true && productDescriptionBO.getProductNo().intValue() !=0))){
				productDescriptionBOListAfterDeletion.add(productDescriptionBO);
			}			
		}
		System.out.println("getProductDescriptionBOListAfterDeletion ends");
		return productDescriptionBOListAfterDeletion;
	}
	
}
