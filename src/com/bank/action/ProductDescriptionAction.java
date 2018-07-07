package com.bank.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.ProductDescriptionBO;
import com.bank.dao.ProductDescriptionDAO;
import com.bank.form.ProductDescriptionForm;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.BankConstant;

public class ProductDescriptionAction extends SuccessMessage{
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("button.search", "search");
	        map.put("button.save", "save");
	        map.put("button.clear", "clear");
	        map.put("loadAllProductDescriptions", "loadAllProductDescriptions");
	        //System.out.println("getKeyMethodMap fired");
	        return map;
	    }

		//To load customers in the form of google suggest
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward loadAllProductDescriptions(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		 throws SQLException,IOException,Exception
		 {
			 System.out.println("loadAllProductDescriptions");
			 String result = "";
			 StringBuffer productDescription = new StringBuffer();
			 HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			 ProductDescriptionDAO productDescriptionDAO = new ProductDescriptionDAO(jndiName);
			 List<ProductDescriptionBO> productDescriptionList = productDescriptionDAO.executeAllProducts();
			// String countries  =  "Afghanistan" + "|" +	 "Albania" + "|" +	 "Algeria" + "|"+	 "Andorra";
					 
			/*// log.info("test Logging");
			   System.out.println("gyjgygy" );
	           System.out.println("A"+countries.toString());
	           System.out.println("gyjgygy" );*/
		       if (productDescriptionList.size()>0) { 
		           response.setContentType("application/text"); 
		           response.setHeader("Cache-Control", "no-cache");	         
		           for(ProductDescriptionBO productDescriptionBO : productDescriptionList){
		        	   productDescription.append(productDescriptionBO.getProductDescription()+ BankConstant.DELIMITER );//+customerBO.getCustomerID() + "|");
		           }
		           result = productDescription.substring(0, productDescription.length()-BankConstant.DELIMITER.length());
		           //System.out.println(result );
		           response.getWriter().write(result);  		           
		       } else { 
		    	   response.setContentType("application/text"); 
		           response.setHeader("Cache-Control", "no-cache");	            
		           response.getWriter().write("");  
		           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
		       }
			return null;
		}
		 
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			 ProductDescriptionForm productDescriptionForm = (ProductDescriptionForm )form;
			System.out.println("Clear method fired");
			//productDescriptionForm.setProductCode("");
			productDescriptionForm.setProductDescription("");
			productDescriptionForm.setProductDescriptionList(null);
			//search(mapping, form, request, response);
			
			return mapping.findForward("success");
		}
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException{
		System.out.println("Save method fired starts");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 ProductDescriptionForm productDescriptionForm = (ProductDescriptionForm )form;
		 ProductDescriptionDAO productDescriptionDAO = new ProductDescriptionDAO(jndiName);
		 //Refresh the updated list after add/delete
		// refreshUpdatedList(productTypeForm.getProductTypeList());
		 productDescriptionDAO.createUpdateDelete(productDescriptionForm.getProductDescriptionList());	
		 search(mapping, form, request, response);
		 /*for(ProductTypeBO productTypeBO: productTypeForm.getProductTypeList()){
			 objPreparedStatement.setString(1, productTypeBO.getProductTypeCode().toUpperCase());
			 objPreparedStatement.setString(2, productTypeBO.getProductTypeDescription().toUpperCase());
			 objPreparedStatement.setDouble(3, productTypeBO.getProductTypeRateOfInterest());
			 
			 System.out.println(productTypeBO.getProductTypeNo());
			 System.out.println(productTypeBO.getChecked());
		 }*/
		
		 setSuccessMessage("success.productDescriptionsSave", request);
		 
		 System.out.println("Save method fired ends");
		return mapping.findForward("success");
	}
	
	
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		System.out.println("Search method fired");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		ProductDescriptionForm productDescriptionForm;
		 try{	 
			 ProductDescriptionDAO productDescriptionDAO = new ProductDescriptionDAO(jndiName);
			 productDescriptionForm = (ProductDescriptionForm )form;			
			 
			 //execute if only description is entered
			 if (productDescriptionForm.getProductDescription() !=null && productDescriptionForm.getProductDescription().length() > 0){
				 productDescriptionForm.setProductDescriptionList(
						 productDescriptionDAO.executeProductDescriptionLike(
								 productDescriptionForm.getProductDescription()));
			 }else{
				 productDescriptionForm.setProductDescriptionList(productDescriptionDAO.executeAllProducts());
			 }
			 
			 ProductDescriptionBO productDescriptionBO = new ProductDescriptionBO();
			 List<ProductDescriptionBO> productDescriptionList = productDescriptionForm.getProductDescriptionList();
			 productDescriptionList.add(productDescriptionBO);
			 productDescriptionForm.setProductDescriptionList( productDescriptionList);
			 //System.out.println("size"+productDescriptionList);
			
			
			 //saveErrors(request, errors);
			 }catch(Exception ex){
			 }  
		return mapping.findForward("success");
	}

}
