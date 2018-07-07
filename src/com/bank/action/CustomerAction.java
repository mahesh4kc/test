package com.bank.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.CustomerBO;
import com.bank.dao.CustomerDAO;
import com.bank.form.CustomerForm;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.BankConstant;

public class CustomerAction  extends SuccessMessage{
//	public class CustomerAction extends Action{
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();	       
	        map.put("button.search", "search");
	        map.put("button.create", "create");
	        map.put("button.update", "update");
	        map.put("button.delete", "delete");
	        map.put("button.clear", "clear");
	        map.put("GoogleSuggestCustomers", "loadAllCustomers");
	        map.put("loadCustomerDetailsForCustomerName", "loadCustomerDetailsForCustomerName");
	        map.put("loadCustomerDetailsForBill", "loadCustomerDetailsForBill");
	        return map;
	    }

	//For Customer screens
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward loadCustomerDetailsForCustomerName(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	 throws SQLException,IOException
	 {
		 System.out.println("loadCustomerDetailsForCustomerName");
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 //System.out.println("CustomerName" +request.getParameter("customerName"));
		 String result = "";
		 StringBuffer customerNames = new StringBuffer();
		 CustomerDAO customerDAO = new CustomerDAO(jndiName);
		 CustomerBO customerBO = customerDAO.executeCustomerName(request.getParameter("customerName"));
		
	       if (customerBO != null) { 
	           response.setContentType("application/text"); 
	           response.setHeader("Cache-Control", "no-cache");	         
	           customerNames.append(customerBO.getName()+ BankConstant.DELIMITER);	   
	           customerNames.append(customerBO.getCustomerID() + BankConstant.DELIMITER);	
	          
	        	   customerNames.append(customerBO.getAddress() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getStreet() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getArea() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getDistrict() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getState() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getCountry()+ BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getPincode() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getMailID() + BankConstant.DELIMITER );	        	  
	        	   customerNames.append(customerBO.getPhoneNo()+ BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getMobileNo() + BankConstant.DELIMITER );
	        	   customerNames.append(customerBO.getRelationName()+ BankConstant.DELIMITER);
	        	   customerNames.append(customerBO.getRelationShip()+ BankConstant.DELIMITER);
	           result = customerNames.substring(0, customerNames.length()- BankConstant.DELIMITER.length());
	           //System.out.println(result );
	           response.getWriter().write(result);  
	        
	       } else { 	    	  
	           result = customerNames.substring(0, customerNames.length()-BankConstant.DELIMITER.length());	           
	    	   response.setContentType("application/text"); 
	           response.setHeader("Cache-Control", "no-cache");	            
	           response.getWriter().write("");  
	           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
	       }
	      
		return null;
	}
	
	 //For Bill screens
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward loadCustomerDetailsForBill(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		 throws SQLException,IOException
		 {
			 System.out.println("loadCustomerDetailsForBill");
			 HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			 CustomerBO customerBO = null;
			// System.out.println("gyjgygy" +request.getParameter("customerID"));
			 String result = "";
			 StringBuffer customerNames = new StringBuffer();
			 CustomerDAO customerDAO = new CustomerDAO(jndiName);
			 if(request.getParameter("customerID") != null){
				  customerBO = customerDAO.executeCustomerID(request.getParameter("customerID"));
			 }else if(request.getParameter("customerName") != null){
				  customerBO = customerDAO.executeCustomerName(request.getParameter("customerName"));
			 }
		
		       if (customerBO != null) { 
		           response.setContentType("application/text"); 
		           response.setHeader("Cache-Control", "no-cache");	         
		           customerNames.append(customerBO.getCustomerID()+ BankConstant.DELIMITER );
		        	   customerNames.append(customerBO.getName()+ BankConstant.DELIMITER );
		        	   customerNames.append(customerBO.getAddress() + " , " );
		        	   customerNames.append(customerBO.getStreet() + " ,  " );
		        	   customerNames.append(customerBO.getArea() + "  , " );
		        	   customerNames.append(customerBO.getDistrict() + "  , " );
		        	   customerNames.append(customerBO.getPincode() + "  , " );
		           result = customerNames.substring(0, customerNames.length());
		          // System.out.println(result );
		           response.getWriter().write(result);  
		        
		       } else { 
		    	   response.setContentType("application/text"); 
		           response.setHeader("Cache-Control", "no-cache");	            
		           response.getWriter().write("");  
		           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
		       }
		       
			return null;
		}
	 
		//To load customers in the form of google suggest
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward loadAllCustomers(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	 throws SQLException,IOException
	 {
		 System.out.println("loadAllCustomers");
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 String result = "";
		 StringBuffer customerNames = new StringBuffer();
		 CustomerDAO customerDAO = new CustomerDAO(jndiName);
		 List<CustomerBO> customerList = customerDAO.executeAllCustomers();
		// String countries  =  "Afghanistan" + "|" +	 "Albania" + "|" +	 "Algeria" + "|"+	 "Andorra";
				 
		/*// log.info("test Logging");
		   System.out.println("gyjgygy" );
           System.out.println("A"+countries.toString());
           System.out.println("gyjgygy" );*/
	       if (customerList.size()>0) { 
	           response.setContentType("application/text"); 
	           response.setHeader("Cache-Control", "no-cache");
	           //System.out.println("Customer size : " + customerList.size());
	           for(CustomerBO customerBO : customerList){
	        	   customerNames.append(customerBO.getName()+ BankConstant.DELIMITER );//+customerBO.getCustomerID() + "|");
	           }
	           result = customerNames.substring(0, customerNames.length()-BankConstant.DELIMITER.length());
	         //  System.out.println(result );
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
	 public ActionForward create(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws SQLException
	 {
		 System.out.println("CREATE");
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 ActionErrors errors=null;
		 CustomerForm customerForm = (CustomerForm)form;		
		CustomerBO customerBO = new CustomerBO();
		if (customerForm != null)
		{
			errors = customerForm.validate(mapping, request);
			/*ActionError CustomerNotFound = new ActionError("error.customerNotFound",
					"Customer not found");	*/			
			//errors.add("error.customerNotFound",new ActionError("error.customerNotFound"));
			saveErrors(request, errors);
						
		} if (errors!= null && errors.size() < 1){
			moveCustomerFormToCustomerBo(customerForm, customerBO);			
			CustomerDAO objCustomerDatabase = new CustomerDAO(jndiName);
			objCustomerDatabase.createCustomer(customerBO);	
			
		}
		
		setSuccessMessage("success.customerCreated", request);
				 
		return mapping.findForward("success");
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException{
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		ActionErrors errors=null;	
		System.out.println("SEARCH");
		CustomerBO customerBO;
		CustomerForm customerForm = (CustomerForm)form;
		clearCustomerNonKeyDetails(customerForm,request);
		//CustomerBO objCustomerBO = new CustomerBO();
		//objCustomerBO.setCustomerCode(objCustomer.getCustomerCode());
	///	objCustomerBO.setCustomerDescription(objCustomer.getCustomerDescription());
		//objCustomerBO.setCustomerRateOfInterest(new Double(objCustomer.getCustomerRateOfInterest()));
		CustomerDAO objCustomerDatabase = new CustomerDAO(jndiName);
		
		//Get customer Business Object
		if (customerForm.getCustomerID().length() == 0 ){
		 errors = new ActionErrors();
			ActionError CustomerNotFound = new ActionError("error.customerDetailsNotFound",
					"Please enter Customer details");				
			//errors.add("error.customerDetailsNotFound",CustomerNotFound);
			//System.out.println("Please enter Customer details");
		}else if (customerForm != null && customerForm.getCustomerID() != null && customerForm.getCustomerID().length() > 0)
		{
			customerBO = objCustomerDatabase.executeCustomerID(customerForm.getCustomerID());
			
			if (customerBO == null){
				
				errors = customerForm.validate(mapping, request);
				/*ActionError CustomerNotFound = new ActionError("error.customerNotFound",
						"Customer not found");	*/			
				//errors.add("error.customerNotFound",new ActionError("error.customerNotFound"));
				saveErrors(request, errors);
				//System.out.println("error.customerNotFound");
			}
				
//				//throw new BankException("Customer not found");
			else
			{
				//load the customer business object to customer form
				moveCustomerBoToCustomerForm(customerForm,customerBO);	
			}
				
			//This is to load the ajax list of customer
			//objCustomer.setCustomerList(
				
			//objCustomerBO = objCustomerDatabase.executeCustomerID(objCustomer.getCustomerID());
		}			
		if(errors == null || errors.size() == 0){
			request.setAttribute("CUSTOMER_EXISTS", 1);
		}
		
		return mapping.findForward("success");

	}
	
	 public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			CustomerForm customerForm = (CustomerForm)form;		
			ActionErrors errors=null;	
			System.out.println("UPDATE");
			HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			if (customerForm != null)
			{
				errors = customerForm.validate(mapping, request);
				/*ActionError CustomerNotFound = new ActionError("error.customerNotFound",
						"Customer not found");	*/			
				//errors.add("error.customerNotFound",new ActionError("error.customerNotFound"));
				saveErrors(request, errors);
							
			}  if (errors!= null && errors.size() < 1){
				CustomerBO customerBO = new CustomerBO();
				moveCustomerFormToCustomerBo(customerForm, customerBO);
				CustomerDAO objCustomerDatabase = new CustomerDAO(jndiName);
				objCustomerDatabase.updateCustomer(customerBO);
				
				//objCustomer.setCustomerNo(objCustomer.getCustomerNo().toString());
			}
			
			setSuccessMessage("success.customerUpdated", request);
			
			return mapping.findForward("success");
		}
	 
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			CustomerForm customerForm = (CustomerForm)form;
			HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			CustomerBO objCustomerBO = new CustomerBO();
			objCustomerBO.setCustomerID(Integer.parseInt(customerForm.getCustomerID()));
			//objCustomerBO.setCustomerCode(objCustomer.getCustomerCode());
			//objCustomerBO.setCustomerDescription(objCustomer.getCustomerDescription());
			//objCustomerBO.setCustomerRateOfInterest(new Double(objCustomer.getCustomerRateOfInterest()));
			CustomerDAO objCustomerDatabase = new CustomerDAO(jndiName);
			objCustomerDatabase.deleteCustomer(objCustomerBO);
			
			//Clear the Customer Details
			clearAllCustomerDetails(customerForm,request);
			
			setSuccessMessage("success.customerDeleted", request);
			 
			//objCustomer.setCustomerNo(objCustomer.getCustomerNo().toString());
			return mapping.findForward("success");
		}

	 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			CustomerForm customerForm = (CustomerForm)form;		
			
			//Clear the Customer Details
			clearAllCustomerDetails(customerForm,request);
			
			//objCustomer.setCustomerNo(objCustomer.getCustomerNo().toString());
			return mapping.findForward("success");
	}
	 
	 // Move the Customer Business object to Customer Form
	 private void moveCustomerBoToCustomerForm(CustomerForm customerForm, 
			 CustomerBO  customerBO){
		 customerForm.setCustomerID(customerBO.getCustomerID().toString());
		 customerForm.setName(customerBO.getName());
		 customerForm.setAddress(customerBO.getAddress());
		 customerForm.setStreet(customerBO.getStreet());
		 customerForm.setArea(customerBO.getArea());
		 customerForm.setDistrict(customerBO.getDistrict());
		 customerForm.setState(customerBO.getState());
		 customerForm.setCountry(customerBO.getCountry());
		 customerForm.setPincode(customerBO.getPincode().toString());
		 customerForm.setPhoto(customerBO.getPhoto());
		 customerForm.setPhoneNo(customerBO.getPhoneNo().toString());
		 customerForm.setMobileNo(new String(customerBO.getMobileNo().toString()));		
		 customerForm.setMailID(customerBO.getMailID());
		 customerForm.setRelationShip(customerBO.getRelationShip());
		 customerForm.setRelationName(customerBO.getRelationName());
	 }
		
	 // Move the Customer Form to Customer Business object 
	 private void moveCustomerFormToCustomerBo(CustomerForm customerForm, 
			 CustomerBO  customerBO){	 
		 if (customerForm.getCustomerID().length() > 0)
			 customerBO.setCustomerID(Integer.parseInt(customerForm.getCustomerID()));
		 customerBO.setName(customerForm.getName());
		 customerBO.setAddress(customerForm.getAddress());
		 customerBO.setStreet(customerForm.getStreet());
		 customerBO.setArea(customerForm.getArea());
		 customerBO.setDistrict(customerForm.getDistrict());
		 customerBO.setState(customerForm.getState());
		 customerBO.setCountry(customerForm.getCountry());
		 if (customerForm.getPincode().length() > 0)
		 customerBO.setPincode(Integer.parseInt(customerForm.getPincode()));		
		 if (customerForm.getPhoneNo().length() > 0)
			 customerBO.setPhoneNo(Long.parseLong(customerForm.getPhoneNo()));
		 if (customerForm.getMobileNo().length() > 0)
			 customerBO.setMobileNo(Long.parseLong(customerForm.getMobileNo()));
		 customerBO.setMailID(customerForm.getMailID());
		 customerBO.setPhoto(customerForm.getPhoto());	
		 customerBO.setRelationShip(customerForm.getRelationShip());
		 customerBO.setRelationName(customerForm.getRelationName());
	 }
	 
		//Clear the clearAllCustomerDetails Details
		 private void clearAllCustomerDetails(CustomerForm customerForm,HttpServletRequest request){
			 clearCustomerKeyDetails(customerForm);
			 clearCustomerNonKeyDetails(customerForm,request);
		 }
		 
		//Clear the Customer Details
		 private void clearCustomerKeyDetails(CustomerForm customerForm){
			 customerForm.setCustomerID("");
			 customerForm.setName("");			
		 }
		 
			//Clear the clearCustomerNonKeyDetails Details
		 private void clearCustomerNonKeyDetails(CustomerForm customerForm,HttpServletRequest request){			
			HttpSession session = request.getSession();
			if(session != null && session.getAttribute("parameterMap")!=null){
				Map<String, String> parameterMap = (Map<String, String>)session.getAttribute("parameterMap");
				customerForm.setArea((String)parameterMap.get(BankConstant.AREA));
				 customerForm.setDistrict((String)parameterMap.get(BankConstant.DISTRICT));			 
				 customerForm.setState((String)parameterMap.get(BankConstant.STATE));
				 customerForm.setCountry((String)parameterMap.get(BankConstant.COUNTRY));
				 customerForm.setPincode((String)parameterMap.get(BankConstant.DEFAULT_PINCODE));	
			}
			 customerForm.setCreateDuplicateCustomer(null);
			 customerForm.setAddress("");
			 customerForm.setStreet("");
			 		 
			 customerForm.setPhoneNo("");
			 customerForm.setMobileNo("");
			 customerForm.setMailID("");
			 customerForm.setRelationShip("");
			 customerForm.setRelationName("");
		 }
}
