package com.bank.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.BillDetailBO;
import com.bank.bo.BillHeaderBO;
import com.bank.bo.CustomerBO;
import com.bank.dao.BillDetailDAO;
import com.bank.dao.BillHeaderDAO;
import com.bank.dao.CustomerDAO;
import com.bank.form.BillForm;
import com.bank.helper.BillDetailHelper;
import com.bank.helper.BillHeaderHelper;
import com.bank.helper.ParameterHelper;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.BankConstant;
import com.bank.util.DateUtil;
import com.bank.util.auction.AuctionNoticesUtil;
import com.bank.util.pdf.BillPdf;

public class BillAction extends SuccessMessage{
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("button.clear", "clear");
	        map.put("button.clearnew", "clear");
	        map.put("button.search", "search");
	        map.put("button.save", "save");
	        map.put("button.savenew", "savenew");
	        map.put("button.delete", "delete");	        
	        map.put("button.redem", "redem");
	        map.put("button.print", "printbill");
	        map.put("button.nextbill", "nextbill");
	        map.put("loadBillDetails", "loadBillDetails");
	        map.put("loadBillDistinctSerial", "loadBillDistinctSerial");
	        map.put("loadBillDistinctSerialNo", "loadBillDistinctSerialNo");	        
	        return map;
	    }


		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward savenew(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
				 HttpServletResponse response) throws SQLException,Exception{
			System.out.println("save method fired");
			 ActionErrors errors=null;
			 BillForm billForm = (BillForm )form;
			 Integer billSequence=0;
			 BillHeaderBO billHeaderBO = null;
			 HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			 BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);
			 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
			 

			 if (billForm != null)
			{
				errors = billForm.validate(mapping, request);
				saveErrors(request, errors);
							
			} 
			 if (errors!= null && errors.size() < 1 && 
					 !billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber())){
			 billSequence = billHeaderDAO.createBillHeader(billForm.getBillHeaderBO());	
			 }else{
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
						 			billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber());
				 billSequence = billHeaderBO.getBillSequence();
				// System.out.println("getCustomerID"+billForm.getBillHeaderBO().getCustomerID());
				 billHeaderDAO.updateBillHeader(billForm.getBillHeaderBO());
			 }
			
			 billDetailDAO.createUpdateDelete(billForm.getBillDetailList(), billSequence);

			 //Print the Bill
			//BillPdf billPdf = new BillPdf(billForm, parameterHelper);
			 //ByteArrayOutputStream outputStream = billPdf.getBillInPDF();
			 //session.setAttribute("billpdfstream",outputStream);
			 /* billPdf.setResponseFormat(response, outputStream ,BankConstant.APPLICATION_MIME_TYPE_PDF,
						 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
			 //response.getOutputStream().flush();
			 //response.getOutputStream().close();
			*/
			 
			 billForm.getBillHeaderBO().setBillSequence(billSequence);
			
			 //clear all the fields and generate the next serial number
			 clearAfterSave(parameterHelper.getCurrentBillSerial(),billForm, billHeaderDAO,jndiName,request);
			 
			 setSuccessMessage("success.billSave", request);
			 		 
			return mapping.findForward("success");
		}


	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("save method fired");
		 ActionErrors errors=null;
		 BillForm billForm = (BillForm )form;
		 Integer billSequence=0;
		 BillHeaderBO billHeaderBO = null;
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
		 BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 

		 if (billForm != null)
		{
			errors = billForm.validate(mapping, request);
			saveErrors(request, errors);
						
		} 
		 if (errors!= null && errors.size() < 1 && 
				 !billHeaderDAO.isBillExistsByBillSerialNo(billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber())){
		 billSequence = billHeaderDAO.createBillHeader(billForm.getBillHeaderBO());	
		 }else{
			 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
					 			billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber());
			 billSequence = billHeaderBO.getBillSequence();
			// System.out.println("getCustomerID"+billForm.getBillHeaderBO().getCustomerID());
			 billHeaderDAO.updateBillHeader(billForm.getBillHeaderBO());
		 }
		
		 billDetailDAO.createUpdateDelete(billForm.getBillDetailList(), billSequence);

		 //Print the Bill
		BillPdf billPdf = new BillPdf(billForm, parameterHelper);
		 ByteArrayOutputStream outputStream = billPdf.getBillInPDF();
		 session.setAttribute("billpdfstream",outputStream);
		 /* billPdf.setResponseFormat(response, outputStream ,BankConstant.APPLICATION_MIME_TYPE_PDF,
					 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
		 //response.getOutputStream().flush();
		 //response.getOutputStream().close();
		*/
		 
		 //billForm.getBillHeaderBO().setBillSequence(billSequence);
		
		 //clear all the fields and generate the next serial number
		 //clearAfterSave(parameterHelper.getCurrentBillSerial(),billForm, billHeaderDAO,jndiName,request);
		 
		 setSuccessMessage("success.billSave", request);
		 		 
		return mapping.findForward("success");
	}

		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public void printbill(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		 System.out.println("print method fired");
		 BillForm billForm = (BillForm )form;
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request); 

		 //Print the Bill
		 BillPdf billPdf = new BillPdf();
		 ByteArrayOutputStream outputStream = (ByteArrayOutputStream)session.getAttribute("billpdfstream");
		 billPdf.setResponseFormat(response, outputStream ,BankConstant.APPLICATION_MIME_TYPE_PDF,
					 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
		 //response.sendRedirect("nextbill");
		 //return perform(mapping, billForm, request, response);// new ActionForward("/bill.do?method=NEXTBILL", true);
		 //return mapping.findForward("nextbill").setRedirect(true);; 
	}
	 
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward nextbill(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
				 HttpServletResponse response) throws SQLException,Exception{
			System.out.println("save method fired");
			 BillForm billForm = (BillForm )form;
			 Integer billSequence=0;
			 HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request); 
			 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			 billForm.getBillHeaderBO().setBillSequence(billSequence);
			
			 //clear all the fields and generate the next serial number
			 clearAfterSave(parameterHelper.getCurrentBillSerial(),billForm, billHeaderDAO,jndiName,request);
			 
			 setSuccessMessage("success.billSave", request);
			 		 
			return mapping.findForward("success");
		}

	 private void clearAfterSave(String billSerial, BillForm billForm, BillHeaderDAO billHeaderDAO,String jndiName,HttpServletRequest request){
		 System.out.println(" clearAfterSave starts");
		 		 
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 //String redemSerial = parameterHelper.getCurrentBillRedemSerial();
		 BillHeaderHelper billHeaderHelper= new BillHeaderHelper(parameterHelper);
		 
		 billHeaderHelper.clearBillHeadBillForm(billForm,jndiName);
		 
		 BillDetailHelper.clearBillDetailsBillForm(billForm);
			
			 // Logic to display the next serial no based on the entered serial no
			 try{
				 Integer nextBillSerialNo =  billHeaderDAO.getNextBillNumberForSerial(billSerial);
					//billHeaderBO.setBillNumber(nextBillSerialNo);
				billForm.getBillHeaderBO().setBillSerial(billSerial);				
				billForm.getBillHeaderBO().setBillNumber(nextBillSerialNo);
				
				/*if(redemSerial != null){
					Integer nextBillRedemSerialNo =  billHeaderDAO.getNextRedemBillNumberForSerial(redemSerial);
					billForm.getBillHeaderBO().setBillRedemSerial(redemSerial);
					billForm.getBillHeaderBO().setBillRedemNumber(nextBillRedemSerialNo);	
				}*/
				
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
			 System.out.println(" clearAfterSave ends");
	 }
	
	 
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward redem(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("save method fired");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		/*if(request.getAttribute("invalidSession") == null){
			 System.out.println("invalidSession1");
			return mapping.findForward("invalidSession");
		}else{*/
			ActionErrors errors=null;
			 BillForm billForm = (BillForm )form;
			 Integer billSequence=0;
			 BillHeaderBO billHeaderBO = null;
			 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			 BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);
			 if (billForm != null)
			{
				errors = billForm.validate(mapping, request);
				saveErrors(request, errors);						
			} 
			 if (errors!= null && errors.size() < 1){
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
						 			billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber());
				 billSequence = billHeaderBO.getBillSequence();
				// System.out.println("getCustomerID"+billForm.getBillHeaderBO().getCustomerID());
				 billHeaderDAO.updateBillHeader(billForm.getBillHeaderBO());
			 }
			// billDetailDAO.createUpdateDelete(billForm.getBillDetailList(), billSequence);
			// billForm.getBillHeaderBO().setBillSequence(billSequence);
			 
			 search(mapping, form, request, response);
			 
			 setSuccessMessage("success.billRedemed", request);
			 
			 clear(mapping, billForm, request, response);
			
		//}
		return mapping.findForward("success");
	}
	 
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("delete method fired");
		
		search(mapping, form, request, response);
		
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		
			ActionErrors errors=null;
			 BillForm billForm = (BillForm )form;
			 Integer billSequence=0;
			 BillHeaderBO billHeaderBO = null;
			 List<BillDetailBO> billDetailList = new ArrayList<BillDetailBO>();
			 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			 BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);
			 if (billForm != null)
			{
				errors = billForm.validate(mapping, request);
				saveErrors(request, errors);						
			} 
			 if (errors!= null && errors.size() < 1){
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
						 			billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber());
				 billSequence = billHeaderBO.getBillSequence();
				 billHeaderDAO.deleteBillHeader(billHeaderBO);
				 billDetailDAO.deleteBillDetails(billSequence);
				// System.out.println("getCustomerID"+billForm.getBillHeaderBO().getCustomerID());
				 
			 }
			// billDetailDAO.createUpdateDelete(billForm.getBillDetailList(), billSequence);
			// billForm.getBillHeaderBO().setBillSequence(billSequence);
			 
			 
			 
		//}
		return mapping.findForward("success");
	}
	 
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("search method fired");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
			 BillForm billForm = (BillForm )form;		
			 BillHeaderBO billHeaderBO = null;
			 List<BillDetailBO> billDetailList = null;	
			 CustomerBO customerBO = null;
			 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
			 BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);	
			 CustomerDAO customerDAO = new CustomerDAO(jndiName);
			 
			 //Fetch existing details if bill already exists
			/* if (billForm.getBillHeaderBO().getBillSequence().intValue() > 0){			 
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSequence(billForm.getBillHeaderBO().getBillSequence());
				 billDetailList = billDetailDAO.executeBillDetailsByBillSequence(billForm.getBillHeaderBO().getBillSequence());			
			 }else*/ if(billForm.getBillHeaderBO().getBillSerial().length()>0 && 
					 billForm.getBillHeaderBO().getBillNumber().intValue() > 0){
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
						 billForm.getBillHeaderBO().getBillSerial(), billForm.getBillHeaderBO().getBillNumber());
				 //System.out.println("billHeaderBO"+billHeaderBO.getBillNumber());
				 //System.out.println("billHeaderBO"+billHeaderBO.getBillSequence());
				 if(billHeaderBO.getBillSequence().intValue() > 0){
					 billDetailList = billDetailDAO.executeBillDetailsByBillSequence(billHeaderBO.getBillSequence()); 
				 }			
			 }
			 BillHeaderHelper billHeaderHelper= new BillHeaderHelper(new ParameterHelper(jndiName,request));
			 billHeaderHelper.clearBillHeadBillForm(billForm,jndiName);
			 BillDetailHelper.clearBillDetailsBillForm(billForm);
			 //Display empty header and detail if already exists or display the existing bill details
			 if (billHeaderBO != null){
				 billHeaderHelper.moveBillHeaderToBillForm(billHeaderBO, billForm);
				 BillDetailHelper.moveBillDetailsToBillForm(billDetailList,billForm);
				 customerBO = customerDAO.executeCustomerID(billForm.getBillHeaderBO().getCustomerID().toString());
				 if (customerBO != null){
					 billForm.setCustomerName(customerBO.getName());
					 billForm.setCustomerAddress(customerBO.getAddress() + " , " + 
							 					customerBO.getStreet() + "," +
							 					customerBO.getArea() + " , " + 
							 					customerBO.getDistrict()+ " , " +
							 					customerBO.getPincode() );
				 }
			 }
			 
			parameterRedemeptionDetails(billForm,jndiName,billHeaderDAO,request);
			
			return mapping.findForward("success");
		
		
	}
	 
	 private void parameterRedemeptionDetails(BillForm billForm,String jndiName, BillHeaderDAO billHeaderDAO, HttpServletRequest request){
		//this is that default date is overwriiten
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 String redemSerial = parameterHelper.getCurrentBillRedemSerial();
		 if(!billForm.getBillHeaderBO().getStatusDescription().equalsIgnoreCase(BankConstant.BILL_REDEM_STATUS)){
			 billForm.getBillHeaderBO().setRedemptionDate(
					 parameterHelper.getRedemCurrentDate() != null 
						&& parameterHelper.getRedemCurrentDate().length()>0 
						? parameterHelper.getRedemCurrentDate():DateUtil.getTodaysDate()
						); 
		 }	
		 if(!billForm.getBillHeaderBO().getRedemptionStatus().equalsIgnoreCase("R")
				 && redemSerial != null && redemSerial.length() > 0){
			//this is to set the bill redemption serial
			 billForm.getBillHeaderBO().setBillRedemSerial(redemSerial);
			//this is to set the bill redemption serial no 
			 try{
				 Integer nextBillRedemSerialNo =  billHeaderDAO.getNextRedemBillNumberForSerial(
						 billForm.getBillHeaderBO().getBillRedemSerial());
				billForm.getBillHeaderBO().setBillRedemNumber(nextBillRedemSerialNo);	 							
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
		 }
		 
	 }
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 System.out.println("clear method fired");		
		 BillForm billForm = (BillForm )form;			
		 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 clearAfterSave(parameterHelper.getCurrentBillSerial(),billForm, billHeaderDAO,jndiName,request);
		
		return mapping.findForward("success");
	}
	 
	 //For Bill screens
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward loadBillDetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		 throws SQLException,IOException
		 {
			 HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			 try{ 			
				 System.out.println("loadBillDetails");
				 //System.out.println("serialLetter" +request.getParameter("serialLetter"));
				 //System.out.println("serialNumber" +request.getParameter("serialNumber"));
				 BillHeaderBO billHeaderBO = null;
				 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
				 StringBuffer billDetails = new StringBuffer();
				 String result = "";
				 
				 billHeaderBO = billHeaderDAO.executeBillHeaderByBillSerialNo(
						 		request.getParameter("serialLetter"), 
						 		Integer.parseInt(request.getParameter("serialNumber")));
				 if (billHeaderBO != null) { 
			         //  response.setContentType("application/text"); 
			           //response.setHeader("Cache-Control", "no-cache");
					 //System.out.println(billHeaderBO.toString());
			           billDetails.append(billHeaderBO.getBillSequence()+ BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getBillDate()+ BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getCustomerID()+  BankConstant.DELIMITER );
			           billDetails.append(billHeaderBO.getCareOf() +  BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getProductTypeNumber() + BankConstant.DELIMITER );
			           billDetails.append(billHeaderBO.getAmount() +  BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getAmountInWords() + BankConstant.DELIMITER );
			           billDetails.append(billHeaderBO.getGrams() + BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getPresentValue()  + BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getMonthlyIncome() + BankConstant.DELIMITER );
			           billDetails.append(billHeaderBO.getRateOfInterest()+ BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getRedemptionDate() + BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getRedemptionInterest() + BankConstant.DELIMITER  );
			           billDetails.append(billHeaderBO.getRedemptionTotal()+ BankConstant.DELIMITER );
			           billDetails.append(billHeaderBO.getRedemptionStatus()+ BankConstant.DELIMITER  );
			           result = billDetails.substring(0, billDetails.length()-BankConstant.DELIMITER.length());
			           //System.out.println(result );
			           response.getWriter().write(result);  
			           
			       } else { 
			    	   response.setContentType("application/text"); 
			           response.setHeader("Cache-Control", "no-cache");	            
			           response.getWriter().write("");  
			           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
			       }
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
			 
			return null;
		}
		 
		 //For Bill screens
			//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 public ActionForward loadBillDistinctSerial(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
			 throws SQLException,IOException
			 {
				 List<String> billSerialList = null;
				 HttpSession session = request.getSession();
				 String jndiName = (String) session.getAttribute("databaseName");
				 try{ 			
					 System.out.println("loadBillDistinctSerial");
					// System.out.println("serialLetter" +request.getParameter("serialLetter"));
					// System.out.println("serialNumber" +request.getParameter("serialNumber"));
					/// BillHeaderBO billHeaderBO = null;
					 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
					 StringBuffer billSerial = new StringBuffer();
					 String result = "";
					 
					 billSerialList = billHeaderDAO.executeDistinctBillSerial();
							 		
					 for(String billSerialNo : billSerialList){
						  billSerial.append(billSerialNo + BankConstant.DELIMITER  );
					 }
					 if (billSerialList != null && billSerialList.size() > 0) { 						 				     
				           result = billSerial.substring(0, billSerial.length()-BankConstant.DELIMITER.length());
				           //System.out.println(result );
				           response.getWriter().write(result);  
				        
				       } else { 
				    	   response.setContentType("application/text"); 
				           response.setHeader("Cache-Control", "no-cache");	            
				           response.getWriter().write("");  
				           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
				       }
										
				 }catch(Exception ex){
					 ex.printStackTrace();
				 }
				return null;
			}
			 
			 //For Bill screens
				//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 public ActionForward loadBillDistinctSerialNo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
				 throws SQLException,IOException
				 {
					 List<String> billSerialList = null;
					 HttpSession session = request.getSession();
					 String jndiName = (String) session.getAttribute("databaseName");
					 try{ 			
						 System.out.println("loadBillDistinctSerialNo");
						// System.out.println("serialLetter" +request.getParameter("serialLetter"));
						// System.out.println("serialNumber" +request.getParameter("serialNumber"));
						/// BillHeaderBO billHeaderBO = null;
						 BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
						 StringBuffer billSerial = new StringBuffer();
						 String result = "";
						 
						 billSerialList = billHeaderDAO.executeDistinctBillSerialNo();
								 		
						 for(String billSerialNo : billSerialList){
							  billSerial.append(billSerialNo + BankConstant.DELIMITER  );
						 }
						 if (billSerialList != null && billSerialList.size() > 0) { 						 				     
					           result = billSerial.substring(0, billSerial.length()-BankConstant.DELIMITER.length());
					           //System.out.println(result );
					           response.getWriter().write(result);  
					        
					       } else { 
					    	   response.setContentType("application/text"); 
					           response.setHeader("Cache-Control", "no-cache");	            
					           response.getWriter().write("");  
					           response.setStatus(HttpServletResponse.SC_NO_CONTENT);	 
					       }
						
					 }catch(Exception ex){
						 ex.printStackTrace();
					 }
					return null;
				}
	}
