package com.bank.action;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.apache.struts.actions.LookupDispatchAction;

import com.bank.bo.BillDetailBO;
import com.bank.bo.BillHeaderBO;
import com.bank.bo.CustomerBO;
import com.bank.bo.SearchMasterScreenBO;
import com.bank.dao.BillDetailDAO;
import com.bank.dao.BillHeaderDAO;
import com.bank.dao.CustomerDAO;
import com.bank.form.SearchMasterScreenForm;
import com.bank.helper.ParameterHelper;
import com.bank.helper.SearchMasterScreenHelper;
import com.bank.util.BankConstant;
import com.bank.util.DateUtil;
import com.bank.util.ExcelUtility;
import com.bank.util.LogMethods;
import com.bank.util.PDFTableUtility;
import com.bank.util.SortingUtil;
import com.bank.util.auction.AuctionNoticesUtil;

public class SearchMasterScreenAction extends LookupDispatchAction{
	
	final String className = SearchMasterScreenAction.class.getName();
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();	       
	        map.put("button.search", "search");	    
	        map.put("button.clear", "clear");
	        map.put("generatePDF", "generatePDF");
	        map.put("generateAuctionPDF", "generateAuctionPDF");	        
	        map.put("generateXLS", "generateXLS");
	        map.put("next", "next");
	        map.put("previous", "previous");
	        System.out.println("getKeyMethodMap");
	        return map;
	    }	
	 
	 public ActionForward next(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("next");
		 SearchMasterScreenForm searchMasterScreenForm = (SearchMasterScreenForm )form;
		 searchMasterScreenForm.setCurrentRecord(searchMasterScreenForm.getNextRecord());
		search(mapping, searchMasterScreenForm, request, response);
		return mapping.findForward("success");
	 }
	 
	 public ActionForward previous(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("previous");
		 SearchMasterScreenForm searchMasterScreenForm = (SearchMasterScreenForm )form;
		 searchMasterScreenForm.setCurrentRecord(searchMasterScreenForm.getPreviousRecord());
		search(mapping, searchMasterScreenForm, request, response);
		return mapping.findForward("success");
	 }
	 
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public void generatePDF(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("generatePDF");
		ByteArrayOutputStream byteArrayOutputStream=null;
		 SearchMasterScreenForm searchMasterScreenForm;
		search(mapping, form, request, response);
		HttpSession session = request.getSession();	
		String jndiName = (String) session.getAttribute("databaseName");
		ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 if(((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase("C")){
			 searchMasterScreenForm = (SearchMasterScreenForm)session.getAttribute("customerDetails");
			 if(searchMasterScreenForm != null){
				 PDFTableUtility utility = new PDFTableUtility(searchMasterScreenForm);
				 byteArrayOutputStream = utility.getPDFInByteArrayOutputStream("CUSTOMER DETAILS",BankConstant.RESULT,
								//new float[] { 1, 1.3f, 2.0f, 3.2f, 0.9f, 3.3f, 1.3f,1f },
							 new float[] { 1, 1.3f, 2.0f, 4.2f, 2.2f, 3.3f},
								6,utility.columnHeaders,utility.noOfRecords);
					//utility.setResponseFormat(response,byteArrayOutputStream,"application/pdf",
					//parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
				 	utility.setResponseFormat(response,byteArrayOutputStream,BankConstant.APPLICATION_MIME_TYPE_PDF,
							parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
				}
		 }else if(((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase("B")){
			 searchMasterScreenForm = (SearchMasterScreenForm)session.getAttribute("billDetails");		 
			 if(searchMasterScreenForm != null){
				 PDFTableUtility utility = new PDFTableUtility(searchMasterScreenForm);
				 byteArrayOutputStream = utility.getPDFInByteArrayOutputStream("BILL DETAILS",BankConstant.RESULT,
								new float[] { 1, 1.3f, 2.0f, 3, 1, 3.2f, 0.8f, 0.9f, 1.3f,1f },
								utility.columnHeaders.size(),utility.columnHeaders,utility.noOfRecords);
						
					 //utility.setResponseFormat(response, byteArrayOutputStream,"application/pdf",
						//	 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
				 utility.setResponseFormat(response,byteArrayOutputStream,BankConstant.APPLICATION_MIME_TYPE_PDF,
							parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
				}
		 }		 
		//return mapping.findForward("success");
	 }
	 
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public void generateAuctionPDF(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("generateAuctionPDF starts");
		 SearchMasterScreenForm searchMasterScreenForm;
		search(mapping, form, request, response);
		HttpSession session = request.getSession();	
		String jndiName = (String) session.getAttribute("databaseName");
		String user = (String) session.getAttribute("databaseName");
		ActionErrors errors=null;
		searchMasterScreenForm = (SearchMasterScreenForm)session.getAttribute("billDetails");		
	/*	if (searchMasterScreenForm != null)
		{
			errors = searchMasterScreenForm.validateAuction(mapping, request);
			ActionError CustomerNotFound = new ActionError("error.customerNotFound",
					"Customer not found");				
			//errors.add("error.customerNotFound",new ActionError("error.customerNotFound"));
			saveErrors(request, errors);
						
		} 
		if( errors!= null && errors.size() < 1 
				&& ((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase("B")){*/
				 CustomerDAO customerDAO = new  CustomerDAO(jndiName);
				 CustomerBO customerBO = customerDAO.executeCustomerName(searchMasterScreenForm.getSearchInput3());
					response.setCharacterEncoding(BankConstant.CHARACTER_ENCODING);
				 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
				 AuctionNoticesUtil utility = new AuctionNoticesUtil(searchMasterScreenForm);				 
					  utility.getPDFAuctionNotices("BILL DETAILS",BankConstant.RESULT,
								new float[] { 1, 1f, 0, 0, 1, 6f, 1f, 1f, 0f,0f },
								utility.columnHeaders.size(),utility.columnHeaders,utility.noOfRecords,customerBO,parameterHelper);						
					 utility.setResponseFormat(response, utility.getByteArrayOutputStream(),BankConstant.APPLICATION_MIME_TYPE_PDF,
							 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_PDF_SUFFIX);
		 //}		 
		//return mapping.findForward("success");
	 }
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public void generateXLS(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("generateXLS");
		ByteArrayOutputStream baos=null;
		search(mapping, form, request, response);
		HttpSession session = request.getSession();
		 SearchMasterScreenForm searchMasterScreenForm = null;
		 String jndiName = (String) session.getAttribute("databaseName");
		 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 if(((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase("C")){
			 searchMasterScreenForm = (SearchMasterScreenForm)session.getAttribute("customerDetails");
			 if(searchMasterScreenForm != null){
					ExcelUtility utility = new ExcelUtility("CUSTOMER DETAILS", searchMasterScreenForm);
					 baos = utility.getXLSInByteArrayOutputStream("CUSTOMER DETAILS",6);					
					 utility.setResponseFormat(response,baos,BankConstant.APPLICATION_MIME_TYPE_XLS,
							 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_XLS_SUFFIX);
				}
		 }else if(((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase("B")){
			 searchMasterScreenForm = (SearchMasterScreenForm)session.getAttribute("billDetails");
			 if(searchMasterScreenForm != null){
					ExcelUtility utility = new ExcelUtility("BILL DETAILS", searchMasterScreenForm);
					 baos = utility.getXLSInByteArrayOutputStream("BILL DETAILS",8);						
					 utility.setResponseFormat(response,baos,BankConstant.APPLICATION_MIME_TYPE_XLS,
							 parameterHelper.getLoginID()+BankConstant.FILE_TYPE_XLS_SUFFIX);
				}
		 }		 
		
		
		//return mapping.findForward("success");
	 }
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("search method fired");
		HttpSession session = request.getSession();
		String jndiName = (String) session.getAttribute("databaseName");
		
		// To fetch all the parameters
		ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);		
		//Map<String, String> parameterMap = parameterHelper.getParameterMap();
		
		//int recordsPerPage = Integer.parseInt((String) session.getAttribute(BankConstant.RECORDS_PER_PAGE));
		 SearchMasterScreenForm searchMasterScreenForm = (SearchMasterScreenForm )form;
		 
		 //identify it is customer or bill screen and set it accordingly as c or b
		 if(request.getParameter("searchTableDetails") != null && request.getParameter("searchTableDetails").equalsIgnoreCase("C")){			
			 loadCustomerDetails(parameterHelper,searchMasterScreenForm,jndiName,mapping,session,request); 			 
		 }			 
		 //System.out.println(request.getParameter("searchTableDetails"));
		// if(request.getParameter("searchTableDetails") != null && request.getParameter("searchTableDetails").equalsIgnoreCase("B")){	
		 else if(request.getParameter("searchTableDetails") != null && request.getParameter("searchTableDetails").equalsIgnoreCase("B")){	
			//clear all the labels
			 searchMasterScreenForm.clearSearchMasterScreenForms( mapping, request);
			 //clear all the input fields
			 clearSearchParametersForDifferentScreens(mapping, form, request, response, session);
			 searchMasterScreenForm.setSearchTableDetails("B");		
			 session.setAttribute("searchTableDetails", "B");
			 //|| ((String)request.getAttribute("searchTableDetails")).equalsIgnoreCase("B")){
			 //request.setAttribute("SearchTableDetails","B");
			 
			 //load the bill details
			 loadBillDetails(parameterHelper,searchMasterScreenForm,jndiName);
			 session.setAttribute("billDetails", searchMasterScreenForm);
		 }
		
		// }		 
		return mapping.findForward("success");
	}
	 
	 private void loadCustomerDetails(ParameterHelper parameters,SearchMasterScreenForm searchMasterScreenForm,
			 String jndiName, ActionMapping mapping, HttpSession session,HttpServletRequest request)throws SQLException,Exception{
		 
		//clear all the labels
		 searchMasterScreenForm.clearSearchMasterScreenForms( mapping, request);
		 //clear all the input fields
		// clearSearchParametersForDifferentScreens(mapping, form, request, response, session);
		 searchMasterScreenForm.setSearchTableDetails("C");
		 session.setAttribute("searchTableDetails", "C");
			 //|| ((String)request.getAttribute("searchTableDetails")).equalsIgnoreCase("C")){
		 //request.setAttribute("SearchTableDetails","C");

		 //Load the customer details
		 System.out.println("loadCustomerDetails");
		 List<CustomerBO> customerList=null;
		 
		//This is to set the current record if there is no current record exists
		 if( searchMasterScreenForm.getCurrentRecord() < 1 )
		 {
			 searchMasterScreenForm.setCurrentRecord(1);
		 }
		 //int currentRecord=601;
			List<SearchMasterScreenBO> searchMasterScreenBOList=new ArrayList<SearchMasterScreenBO>();			
			
			//initialize all the customer labels
			setCustomerLabels(searchMasterScreenForm);
			
			 CustomerDAO customerDAO = new  CustomerDAO(jndiName);
			 customerList = customerDAO.executeAllCustomers(parameters.getRecordsPerPage(),searchMasterScreenForm.getCurrentRecord(),searchMasterScreenForm,jndiName,
					 (searchMasterScreenForm.getSearchInput1()==null?"":searchMasterScreenForm.getSearchInput1()), 
					 (searchMasterScreenForm.getSearchInput2()==null?"":searchMasterScreenForm.getSearchInput2()),
					 (searchMasterScreenForm.getSearchInput3()==null?"":searchMasterScreenForm.getSearchInput3()),
					 (searchMasterScreenForm.getSearchInput4()==null?"":searchMasterScreenForm.getSearchInput4()), 
					 (searchMasterScreenForm.getSearchInput5()==null?"":searchMasterScreenForm.getSearchInput5()), 
					 (searchMasterScreenForm.getSearchInput6()==null?"":searchMasterScreenForm.getSearchInput6()));
					  
			SearchMasterScreenHelper.setSearchMasterScreenListForCustomerList(
							 searchMasterScreenBOList, customerList);
			 searchMasterScreenForm.setSearchMasterScreenBOList(searchMasterScreenBOList);
			 //This is to reset the current record
			 searchMasterScreenForm.setCurrentRecord(1);
			 //System.out.println("customer size :"+searchMasterScreenBOList.size());
			 session.setAttribute("customerDetails", searchMasterScreenForm);
	 }
	
	
	 private String getListOfCustomerIDs(String jndiName, SearchMasterScreenForm searchMasterScreenForm,
			 String customerListOfIDs)
	 throws SQLException,Exception
	 {
		 //Filter the customers based on Customer Name adn populate customer details
		 List<CustomerBO> customerBOList=null;
		 
		 CustomerDAO customerDAO = new CustomerDAO(jndiName);
		 if (searchMasterScreenForm.getSearchInput3()!=null 
				 && searchMasterScreenForm.getSearchInput3().length() > 0){
			customerBOList = customerDAO.executeAllCustomers(searchMasterScreenForm.getSearchInput3());
			 //System.out.println("Size of Customer after entered name filter "+customerBOList.size());
			 
			StringBuffer customerIds = new StringBuffer();					
			for(CustomerBO customerBO : customerBOList){
				customerIds.append(customerBO.getCustomerID()+",");
			}
			//load all the customer list by passing partiall name
			customerListOfIDs = (customerIds != null && customerIds.length() > 0 
					? customerIds.toString().substring(0, customerIds.length()-1): null );
		 }
		 return customerListOfIDs;
	 }
	
	 private List<BillHeaderBO> getNormalBillSearchWithCustomer(ParameterHelper parameters,
			 SearchMasterScreenForm searchMasterScreenForm, BillHeaderDAO billHeaderDAO, 
			 String customerListOfIDs) throws SQLException, Exception{
		 List<BillHeaderBO> billHeaderList =  billHeaderDAO.executeAllBillHeaders(
				 parameters, searchMasterScreenForm.getCurrentRecord(),searchMasterScreenForm,
				 (searchMasterScreenForm.getSearchInput1()==null?"":searchMasterScreenForm.getSearchInput1()), 
				 (searchMasterScreenForm.getSearchInput2()==null?"":searchMasterScreenForm.getSearchInput2()),
				 (customerListOfIDs),
				 (searchMasterScreenForm.getSearchInput4()==null?"":searchMasterScreenForm.getSearchInput4()),
				 (searchMasterScreenForm.getSearchInput5()==null?"":searchMasterScreenForm.getSearchInput5()),
				 (searchMasterScreenForm.getSearchInput6()==null?"":searchMasterScreenForm.getSearchInput6()),
				 (searchMasterScreenForm.getSearchInput11()==null?"":searchMasterScreenForm.getSearchInput11()));
		 return billHeaderList;
	 }
	 
	 private List<BillHeaderBO> getNormalBillSearch(ParameterHelper parameters,
			 SearchMasterScreenForm searchMasterScreenForm, BillHeaderDAO billHeaderDAO) throws SQLException, Exception{
		 List<BillHeaderBO> billHeaderList = billHeaderDAO.executeAllBillHeaders(
				 parameters,searchMasterScreenForm.getCurrentRecord(),searchMasterScreenForm,
				 (searchMasterScreenForm.getSearchInput1()==null?"":searchMasterScreenForm.getSearchInput1()), 
				 (searchMasterScreenForm.getSearchInput2()==null?"":searchMasterScreenForm.getSearchInput2()),						 
				 (searchMasterScreenForm.getSearchInput4()==null?"":searchMasterScreenForm.getSearchInput4()),
				 (searchMasterScreenForm.getSearchInput5()==null?"":searchMasterScreenForm.getSearchInput5()),
				 (searchMasterScreenForm.getSearchInput6()==null?"":searchMasterScreenForm.getSearchInput6()),
				 (searchMasterScreenForm.getSearchInput11()==null?"":searchMasterScreenForm.getSearchInput11()));
		 return billHeaderList;
	 }
	 
	 private List<BillHeaderBO> getDateBillSearchWithCustomer(ParameterHelper parameters,
			 SearchMasterScreenForm searchMasterScreenForm, BillHeaderDAO billHeaderDAO, 
			 String customerListOfIDs) throws SQLException, Exception{
		 List<BillHeaderBO>  billHeaderList = null;
		 if(searchMasterScreenForm.getSearchInput1().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput1())
					&& searchMasterScreenForm.getSearchInput2().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput2())){
			   billHeaderList = billHeaderDAO.executeBillHeadersBetweenDates(
					 parameters, searchMasterScreenForm.getCurrentRecord(), searchMasterScreenForm
					 ,customerListOfIDs);
		 }else{
				billHeaderList = new ArrayList<BillHeaderBO>();
		 }
		
		 return billHeaderList;
	 }
	 
	 private List<BillHeaderBO> getDateBillSearch(ParameterHelper parameters,
			 SearchMasterScreenForm searchMasterScreenForm, BillHeaderDAO billHeaderDAO) 
			 throws SQLException, Exception{
		 List<BillHeaderBO>  billHeaderList = null;
		 if(searchMasterScreenForm.getSearchInput1().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput1())
					&& searchMasterScreenForm.getSearchInput2().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput2())){
		 billHeaderList = billHeaderDAO.executeBillHeadersBetweenDates(
				 parameters, searchMasterScreenForm.getCurrentRecord(), searchMasterScreenForm
				 ,null);
		 }else{
				billHeaderList = new ArrayList<BillHeaderBO>();
		 }
		 return billHeaderList;
	 }
	 
	//populate the bill header based on the customer filter with normal search
	 private List<BillHeaderBO>  getBillHeaders(SearchMasterScreenForm searchMasterScreenForm, 
			 BillHeaderDAO billHeaderDAO,ParameterHelper parameters,String customerListOfIDs) 
			 throws SQLException, Exception{
		 List<BillHeaderBO>  billHeaderList = null;
		 boolean customerListExists = false;
		 if(customerListOfIDs != null && customerListOfIDs.length() > 0){
			 customerListExists = true;
		 }
		 
		 if(customerListExists && searchMasterScreenForm.getSearchOptions()!= null && 
				 searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("N")){					
				//System.out.println( "Customer ID's " + customerIds.toString().substring(0, customerIds.length()-1));
				 billHeaderList = getNormalBillSearchWithCustomer(parameters, searchMasterScreenForm, 
						 billHeaderDAO, customerListOfIDs);
		 } else if(!customerListExists && billHeaderList == null && searchMasterScreenForm.getSearchOptions()!= null && 
				 searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("N")){
			 //populate the bill header Filter based on sno, date, amount and status				 
			 billHeaderList = getNormalBillSearch(parameters, searchMasterScreenForm, billHeaderDAO);
		 
		//populate the bill header based on the customer filter with date search
		 }else if(customerListExists && searchMasterScreenForm.getSearchOptions()!= null && 
				 ( searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("D") || 
						 searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("R"))){
				//populate the bill header Filter based on sno, date, amount and status				 
				 billHeaderList = getDateBillSearchWithCustomer(parameters, searchMasterScreenForm, 
						 billHeaderDAO, customerListOfIDs);
		}else if(!customerListExists && billHeaderList == null && searchMasterScreenForm.getSearchOptions()!= null && 
				( searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("D") || 
						 searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("R"))){
				//populate the bill header Filter based on sno, date, amount and status				 
				 billHeaderList = getDateBillSearch(parameters, searchMasterScreenForm, billHeaderDAO);
		}else{
			billHeaderList = new ArrayList<BillHeaderBO>();
		}
		 return billHeaderList;
	 }
	 
	 private List<BillDetailBO> getBillDetails(BillDetailDAO billDetailDAO,String billSequenceNumbers) 
	 								throws SQLException, Exception{
		 List<BillDetailBO> billDetailList=null;
		//System.out.println( "Bill sequence" + billSequenceNumbers.toString().substring(0, billSequenceNumbers.length()-1));
		if(billSequenceNumbers != null && billSequenceNumbers.length() > 0){
			 billDetailList = billDetailDAO.executeAllBillDetailsUsingBillSequenceIn(
					 billSequenceNumbers.toString().substring(0, billSequenceNumbers.length()-1));
		}
		else{
			billDetailList = new ArrayList<BillDetailBO>();
		}
		return billDetailList;
	 }
	 
	//populate bill sequence no to get customer details	
	 private String getCustomerUsingBillSequenceNumbers(List<BillHeaderBO> billHeaderList,String customerListOfIDs){			
		StringBuffer customerIds = new StringBuffer();	
		boolean customerListExists = false;
		customerListExists = customerListOfIDs != null && customerListOfIDs.length() > 0 ? true : false;
		if(!customerListExists){
			for(BillHeaderBO billHeaderBO : billHeaderList)										
				customerIds.append(billHeaderBO.getCustomerID()+",");
		}
		if(!customerListExists){
			customerListOfIDs = (customerIds != null && customerIds.length() > 0 
					? customerIds.toString().substring(0, customerIds.length()-1): null) ;
		}
		return customerListOfIDs;
	 }
	
	 // get bill details using bill header list
	 private String getBillSequenceNumbersUsingBillHeader(List<BillHeaderBO> billHeaderList){		
		 String billSequenceNumbers=null;
			StringBuffer billSequenceNumberList = new StringBuffer();
			for(BillHeaderBO billHeaderBO : billHeaderList){
				billSequenceNumberList.append(billHeaderBO.getBillSequence()+",");				
			}
			billSequenceNumbers = (billSequenceNumberList != null && billSequenceNumberList.length() > 0 
					? billSequenceNumberList.toString().substring(0, billSequenceNumberList.length()-1): null);
			return billSequenceNumbers;
			
	 }
	 
	 private List<CustomerBO> getCustomers(CustomerDAO customerDAO,String customerListOfIDs) throws SQLException{
		 List<CustomerBO> customerBOList=null;
		 if(customerListOfIDs != null && customerListOfIDs.length() > 0){
			 customerBOList = customerDAO.executeAllCustomersByCustomerIdIn(customerListOfIDs); 
		 }else{
			customerBOList = new ArrayList<CustomerBO>();
		 }		 
		 return customerBOList;
	 }
	 
	 private void loadBillDetails(ParameterHelper parameters,SearchMasterScreenForm searchMasterScreenForm, String jndiName)throws SQLException,Exception{
		System.out.println("loadBillDetails");
		List<BillHeaderBO> billHeaderList= null;
 		List<BillDetailBO> billDetailList=null;	 
 		List<CustomerBO> customerBOList=null;
		List<SearchMasterScreenBO> searchMasterScreenBOList=new ArrayList<SearchMasterScreenBO>();
		String billSequenceNumbers=null,customerListOfIDs=null;
		BillHeaderDAO billHeaderDAO = new BillHeaderDAO(jndiName);
		BillDetailDAO billDetailDAO = new BillDetailDAO(jndiName);	
		CustomerDAO customerDAO = new CustomerDAO(jndiName);
		
		//This is to set the current record if there is no current record exists
		 if( searchMasterScreenForm.getCurrentRecord() < 1 )
		 {
			 searchMasterScreenForm.setCurrentRecord(1);
		 }
		 
		//initialize all the labels for bill and load the product type details
		setBillLabels(searchMasterScreenForm, jndiName);	
		
		//set the List of customers if bills are filtered based on customer
		customerListOfIDs = getListOfCustomerIDs(jndiName, searchMasterScreenForm,customerListOfIDs);
		
		//populate bill header	
		billHeaderList = getBillHeaders(searchMasterScreenForm, billHeaderDAO, 
				parameters, customerListOfIDs);
			
		 //populate bill details && customer if customer list is not created already
		 
			
		 if(billHeaderList != null && billHeaderList.size()>0){
			
			 //System.out.println("Size of bill header"+billHeaderList.size());	
			 
			 
			 //get the list of bills and its sequence nos based on the search master screen
			 billSequenceNumbers = getBillSequenceNumbersUsingBillHeader(billHeaderList);
			
			// get the list of customer if filter is not based on the customer name search in search master screen
			 customerListOfIDs = getCustomerUsingBillSequenceNumbers(billHeaderList, customerListOfIDs);
				
			//populate customer			
			customerBOList = getCustomers(customerDAO, customerListOfIDs);
			
			//populate bill details
			billDetailList = getBillDetails(billDetailDAO, billSequenceNumbers);				
		 }			 
		
		//setting the searchMaster BO from Bill Header, Bill Detail, Customer
		 SearchMasterScreenHelper.setSearchMasterScreenListForBillList(
					searchMasterScreenBOList, billHeaderList, billDetailList , customerBOList);
		 
		 sortSearchMasterScreenBOListBySerialAndNumber(searchMasterScreenBOList,searchMasterScreenForm);		 
		 
		 //setting the searchMaster form from BO
		 searchMasterScreenForm.setSearchMasterScreenBOList(searchMasterScreenBOList);
		 
		//This is to reset the current record
		 searchMasterScreenForm.setCurrentRecord(1);
		 //System.out.println("Bill size :"+searchMasterScreenBOList.size());
	 }
 
	private void sortSearchMasterScreenBOListBySerialAndNumber(List<SearchMasterScreenBO> searchMasterScreenBOList,
			SearchMasterScreenForm searchMasterScreenForm){
		boolean sortByAscendingSearchMasterScreenBO = searchMasterScreenForm.isSortByAscending();
		boolean sortByDescendingBillRedemptionSerailAndSerialNo = getSortByDescendingBillRedemptionSerailAndSerialNo(searchMasterScreenForm);
				if(sortByDescendingBillRedemptionSerailAndSerialNo){
					//System.out.println("sort by bill redemptions seraio");
					Collections.sort(searchMasterScreenBOList,SortingUtil.sortByBillRedemptionSerialNoInSearchMasterScreenBO);	//Sory By Bill Redemption SerialNo
					 Collections.sort(searchMasterScreenBOList,SortingUtil.sortByBillRedemptionSerialInSearchMasterScreenBO);		//Sory By Redemption Serial
					//Ascending order by redem serial no
					 if(!sortByAscendingSearchMasterScreenBO){				
						 //System.out.println("sort by bill redemptions seraio - reverse");
						 Collections.reverse(searchMasterScreenBOList);		//Reverse the collections, sort By Serial & No
					 }
					 
				}else if(sortByAscendingSearchMasterScreenBO){
					//System.out.println("sort by bill serial no in ascending");
					Collections.sort(searchMasterScreenBOList,SortingUtil.sortByBillSerialNoInSearchMasterScreenBO);	//Sory By SerialNo
					Collections.sort(searchMasterScreenBOList,SortingUtil.sortByBillSerialInSearchMasterScreenBO);		//Sory By Serial
					//Collections.reverse(searchMasterScreenBOList);		//Reverse the collections, sort By Serial & No	
					}
				
	}

		
	//initialize all the customer labels
	 private void setCustomerLabels(SearchMasterScreenForm searchMasterScreenForm){			
			 searchMasterScreenForm.setSearchLabel1("Customer ID");
			 searchMasterScreenForm.setSearchLabel2("Customer Name");
			 searchMasterScreenForm.setSearchLabel3("Address");
			 searchMasterScreenForm.setSearchLabel4("Street");
			 searchMasterScreenForm.setSearchLabel5("Area");
			 searchMasterScreenForm.setSearchLabel6("Mobile No");
		 }
	 
	//initialize all the bill labels
	 private void setBillLabels(SearchMasterScreenForm searchMasterScreenForm, String jndiName){	
		 if(searchMasterScreenForm.getSearchOptions()== null){
			 searchMasterScreenForm.setSearchOptions("N");	//default settings for bill
		 }
		 if(searchMasterScreenForm.getSearchOptions()!= null && searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("N"))
		 {
		 searchMasterScreenForm.setSearchLabel1("Bill S.No");
		 searchMasterScreenForm.setSearchLabel2("Bill Date");		 
		 }else if(searchMasterScreenForm.getSearchOptions()!= null && searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("D"))
		 {
			 searchMasterScreenForm.setSearchLabel1("Date From");
			 searchMasterScreenForm.setSearchLabel2("Date To");
		} 
		 searchMasterScreenForm.setSearchLabel3("Customer[Rel.]");
		 searchMasterScreenForm.setSearchLabel4("Address");
		 searchMasterScreenForm.setSearchLabel5("Amount");
		 searchMasterScreenForm.setSearchLabel6("Prod./Stat");
		 searchMasterScreenForm.setSearchLabel7("Red. Date");
		 searchMasterScreenForm.setSearchLabel8("Red. No");
		 searchMasterScreenForm.setSearchLabel9("Gms.");
		 searchMasterScreenForm.setSearchLabel10("Pr.Val");
		 searchMasterScreenForm.setSearchLabel11("Prod. Type");
		 
		 //searchMasterScreenForm.setSearchInput11("0");
		 //Redemtion Date
	 }
	 
	 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		 SearchMasterScreenForm searchMasterScreenForm = (SearchMasterScreenForm )form;
		System.out.println("Clear method fired");
		//clear all the input fields
		searchMasterScreenForm.clearSearchMasterInputForms(mapping, request);
		
		return mapping.findForward("success");
	}
	 
	 //clear all the input fields
	 private void clearSearchParametersForDifferentScreens(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response,HttpSession session){
		 if(request.getParameter("searchTableDetails") != null && session.getAttribute("searchTableDetails") != null){
			 if( !((String)session.getAttribute("searchTableDetails")).equalsIgnoreCase((String)request.getParameter("searchTableDetails"))){		 
				 clear(mapping, form, request, response);
			 }
		}
	 }
	 
	 /*
	  * 
	  */
	 private boolean getSortByDescendingBillRedemptionSerailAndSerialNo(SearchMasterScreenForm searchMasterScreenForm){
		 final String  methodName = "getSortByDescendingBillRedemptionSerailAndSerialNo";
			LogMethods.printMethodStarts(className, methodName);
			boolean sortByDescendingBillRedemptionSerailAndSerialNo = searchMasterScreenForm.getSearchInput5() != null
					&& searchMasterScreenForm.getSearchInput5().length() >0 && searchMasterScreenForm.getSearchInput5().equalsIgnoreCase(BankConstant.REDEM_CODE) ? true : false;
			if(!sortByDescendingBillRedemptionSerailAndSerialNo){
				sortByDescendingBillRedemptionSerailAndSerialNo = sortByDescendingBillRedemptionSerailAndSerialNo = searchMasterScreenForm.getSearchOptions() != null
						&& searchMasterScreenForm.getSearchOptions().length() >0 && searchMasterScreenForm.getSearchOptions().equalsIgnoreCase(BankConstant.REDEM_CODE) ? true : false;				
			}
			System.out.println("getSortByDescendingBillRedemptionSerailAndSerialNo:"+sortByDescendingBillRedemptionSerailAndSerialNo);
			LogMethods.printMethodStarts(className, methodName);
			return sortByDescendingBillRedemptionSerailAndSerialNo;
		}
	}
