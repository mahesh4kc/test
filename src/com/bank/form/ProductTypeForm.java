package com.bank.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.ProductTypeBO;



public class ProductTypeForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productTypeNo;
	private String productTypeCode;
	private List<ProductTypeBO> productTypeList;
	
	
	public String getProductTypeNo() {
		return this.productTypeNo;
	}


	public void setProductTypeNo(String productTypeNo) {
		this.productTypeNo = productTypeNo;
	}


	public String getProductTypeCode() {
		return this.productTypeCode;
	}


	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}


	public List<ProductTypeBO> getProductTypeList() {
		return this.productTypeList;
	}


	public void setProductTypeList(List<ProductTypeBO> productTypeList) {
		this.productTypeList = productTypeList;
	}


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    ActionErrors errors = new ActionErrors();
	    //System.out.println("Validate Method");
	  
	    return errors;
	 }
	@SuppressWarnings("unchecked")
	public void reset(ActionMapping mapping,HttpServletRequest request) {
		 //System.out.println("Form is Reset");	
		 this.productTypeList = ListUtils.lazyList(new ArrayList<ProductTypeBO>(),
		        new Factory() {
		            public Object create() {
		                return new ProductTypeBO();
		            }
		        });
		
				setUserLoggedIn(request);
			
	}
	
}
