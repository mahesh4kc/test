package com.bank.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.ProductDescriptionBO;
import com.bank.business.validation.ProductDescriptionBusinessValidation;



public class ProductDescriptionForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productDescription;
	private List<ProductDescriptionBO> productDescriptionList;
	
	

	public String getProductDescription() {
		return this.productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<ProductDescriptionBO> getProductDescriptionList() {
		return this.productDescriptionList;
	}
	public void setProductDescriptionList(List<ProductDescriptionBO> productDescriptionList) {
		this.productDescriptionList = productDescriptionList;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    //System.out.println("Validate Method of ProductDescriptionForm starts");
	    String action = request.getParameter("method");
	    HttpSession session = request.getSession();
	    String jndiName = (String) session.getAttribute("databaseName");
	    //object instantiation
	    ActionErrors errors = new ActionErrors();
	    ProductDescriptionBusinessValidation  productDescriptionBusinessValidation = new ProductDescriptionBusinessValidation(jndiName);
	   
	  try{
		   if(action !=null && action.equals("SEARCH")){	
			  // productDescriptionBusinessValidation.validateBusinessProductDescriptionForSearch(errors, this);
		   }else if(action !=null && action.equals("SAVE")){
		    	productDescriptionBusinessValidation.validateBusinessProductDescriptionForSave(errors, this);	
		   }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }catch(Exception exception1){
		  exception1.printStackTrace();
	  }
	  if(errors != null && errors.size() > 0){
		  System.out.println("# of errors"+errors.size());
	  }
	  //System.out.println("Validate Method of ProductDescriptionForm ends");
	    return errors;
	 }
	
	@SuppressWarnings("unchecked")
	public void reset(ActionMapping mapping,HttpServletRequest request) {
		 //System.out.println("Form is Reset");	
		 this.productDescriptionList = ListUtils.lazyList(new ArrayList<ProductDescriptionBO>(),
		        new Factory() {
		            public Object create() {
		                return new ProductDescriptionBO();
		            }
		        });
		
				setUserLoggedIn(request);
			
	}
	
}
