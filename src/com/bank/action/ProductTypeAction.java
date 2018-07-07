package com.bank.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.ProductTypeBO;
import com.bank.dao.ProductTypeDAO;
import com.bank.form.ProductTypeForm;
import com.bank.successmessage.SuccessMessage;

public class ProductTypeAction extends SuccessMessage{
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("button.search", "search");
	        map.put("button.save", "save");
	        map.put("button.clear", "clear");
	        System.out.println("getKeyMethodMap fired");
	        return map;
	    }


		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			 ProductTypeForm productTypeForm = (ProductTypeForm )form;
			System.out.println("Clear method fired");
			productTypeForm.setProductTypeNo("");
			productTypeForm.setProductTypeCode("");
			productTypeForm.setProductTypeList(null);
			//search(mapping, form, request, response);
			
			return mapping.findForward("success");
		}
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException{
		System.out.println("Save method fired");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 ProductTypeForm productTypeForm = (ProductTypeForm )form;
		 ProductTypeDAO productTypeDAO = new ProductTypeDAO(jndiName);
		 //Refresh the updated list after add/delete
		// refreshUpdatedList(productTypeForm.getProductTypeList());
		 productTypeDAO.createUpdateDelete(productTypeForm.getProductTypeList());	
		 search(mapping, form, request, response);
		 /*for(ProductTypeBO productTypeBO: productTypeForm.getProductTypeList()){
			 objPreparedStatement.setString(1, productTypeBO.getProductTypeCode().toUpperCase());
			 objPreparedStatement.setString(2, productTypeBO.getProductTypeDescription().toUpperCase());
			 objPreparedStatement.setDouble(3, productTypeBO.getProductTypeRateOfInterest());
			 
			 System.out.println(productTypeBO.getProductTypeNo());
			 System.out.println(productTypeBO.getChecked());
		 }*/
		
		 setSuccessMessage("success.productTypeSave", request);
		 
		return mapping.findForward("success");
	}
	 private void refreshUpdatedList(List<ProductTypeBO> productTypeBOList){
		 List<ProductTypeBO> newProductTypeBOList=null; 
		 newProductTypeBOList = new ArrayList<ProductTypeBO>();
		 for(ProductTypeBO productTypeBO : productTypeBOList){
		//	 if(productTypeBO.getChecked())
		 }
	 }
	
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		System.out.println("Search method fired");
	//	ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 try{	 
			 ProductTypeDAO productTypeDAO = new ProductTypeDAO(jndiName);
			 ProductTypeForm productTypeForm = (ProductTypeForm )form;
			 if(productTypeForm.getProductTypeNo().length() < 1 && 
					 productTypeForm.getProductTypeCode().length() < 1){
				 productTypeForm.setProductTypeList(productTypeDAO.executeAllProductType());	 
			 }else if (productTypeForm.getProductTypeNo().length() > 0 && 
					 productTypeForm.getProductTypeCode().length() > 0){
				 productTypeForm.setProductTypeList(productTypeDAO.executeByProductTypeNoAndCode(productTypeForm.getProductTypeNo(),
						 productTypeForm.getProductTypeCode()));
			 }else if (productTypeForm.getProductTypeNo().length() > 0){
				 productTypeForm.setProductTypeList(productTypeDAO.executeProductTypeNo(productTypeForm.getProductTypeNo()));
			 }else if (productTypeForm.getProductTypeCode().length() > 0){
				 productTypeForm.setProductTypeList(productTypeDAO.executeProductTypeCode(productTypeForm.getProductTypeCode()));
			 }
			 ProductTypeBO productTypeBO = new ProductTypeBO();
			 List productTypeBOList = productTypeForm.getProductTypeList();
			 productTypeBOList.add(productTypeBO);
			 productTypeForm.setProductTypeList( productTypeBOList);
			 
			 /* if(productTypeForm.getProductTypeList().size() < 1){
				 ProductTypeBO productTypeBO = new ProductTypeBO();
				 List productTypeBOList = new ArrayList<ProductTypeBO>();
				 productTypeBOList.add(productTypeBO);
				 productTypeForm.setProductTypeList(productTypeBOList);				 
			 }*/
			/* if(productTypeForm.getProductTypeList().size() < 1){
				 errors.add("product type not found", new ActionError("No Record found"));
				
			 }*/
			// saveErrors(request, errors);
			 }catch(Exception ex){}  
		return mapping.findForward("success");
	}
/*
 * 
 
	 private void moveProductFormToProductBO(ProductTypeForm productTypeForm,ProductTypeBO productTypeBO){
			productTypeBO.setProductTypeCode(productTypeForm.getProductTypeCode());
			productTypeBO.setProductTypeDescription(productTypeForm.getProductTypeDescription());
			productTypeBO.setProductTypeRateOfInterest(new Double(productTypeForm.getProductTypeRateOfInterest()));
			//productTypeForm.setProductTypeNo(productTypeBO.getProductTypeNo().toString());
	 }*/
	
	 /*
	  * 
	 
	 	 private void moveProductBOToProductForm(ProductTypeForm1 productTypeForm,ProductTypeBO productTypeBO){
	 			productTypeBO.setProductTypeCode(productTypeForm.getProductTypeCode());
	 			productTypeBO.setProductTypeDescription(productTypeForm.getProductTypeDescription());
	 			productTypeBO.setProductTypeRateOfInterest(new Double(productTypeForm.getProductTypeRateOfInterest()));
	 			productTypeForm.setProductTypeNo(productTypeBO.getProductTypeNo().toString());
	 	 } */
}
