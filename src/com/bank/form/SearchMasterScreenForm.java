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

import com.bank.bo.BillDetailBO;
import com.bank.bo.BillHeaderBO;
import com.bank.bo.ProductTypeBO;
import com.bank.bo.SearchMasterScreenBO;
import com.bank.business.validation.CustomerBusinessValidation;
import com.bank.dao.ProductTypeDAO;



public class SearchMasterScreenForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchTableDetails;		//Bill or Customer detail
	private String searchPrimaryKey;
	private String searchOptions;			//different Bill search options 
	
	//customer id & bill serial no
	private String searchLabel1;
	private String searchInput1;
	
	//customer name & bill date
	private String searchLabel2;
	private String searchInput2;
	
	//address & customer name
	private String searchLabel3;
	private String searchInput3;
	
	//street & amount
	private String searchLabel4;
	private String searchInput4;
	
	// area & product description & redem status
	private String searchLabel5;
	private String searchInput5;
	
	//mobile number & bill redemption date
	private String searchLabel6;
	private String searchInput6;
	
	//mobile number & bill redemption date
	private String searchLabel7;
	private String searchInput7;
	
	//mobile number & bill redemption no
	private String searchLabel8;
	private String searchInput8;
	
	//Gram
	private String searchLabel9;
	
	//Present Value
	private String searchLabel10;
	
	//Product Type
	private String searchLabel11;
	private String searchInput11;
	
	private List<ProductTypeBO> productTypeBOList;
	
	private List<SearchMasterScreenBO> searchMasterScreenBOList;
	
	private int currentRecord=0;
    
    private int nextRecord=0;
   
    private int previousRecord=0;
   
    private boolean nextRecordExists = false;
   
    private boolean previousRecordExists = false;
    
    private boolean sortByAscending = false;
    
	public boolean isSortByAscending() {
		return sortByAscending;
	}
	public void setSortByAscending(boolean sortByAscending) {
		this.sortByAscending = sortByAscending;
	}
	public int getCurrentRecord() {
		return this.currentRecord;
	}
	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}
	public int getNextRecord() {
		return this.nextRecord;
	}
	public void setNextRecord(int nextRecord) {
		this.nextRecord = nextRecord;
	}
	public int getPreviousRecord() {
		return this.previousRecord;
	}
	public void setPreviousRecord(int previousRecord) {
		this.previousRecord = previousRecord;
	}
	public boolean isNextRecordExists() {
		return this.nextRecordExists;
	}
	public void setNextRecordExists(boolean nextRecordExists) {
		this.nextRecordExists = nextRecordExists;
	}
	public boolean isPreviousRecordExists() {
		return this.previousRecordExists;
	}
	public void setPreviousRecordExists(boolean previousRecordExists) {
		this.previousRecordExists = previousRecordExists;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getSearchPrimaryKey() {
		return this.searchPrimaryKey;
	}
	public void setSearchPrimaryKey(String searchPrimaryKey) {
		this.searchPrimaryKey = searchPrimaryKey;
	}
	public String getSearchTableDetails() {
		return this.searchTableDetails;
	}
	public void setSearchTableDetails(String searchTableDetails) {
		this.searchTableDetails = searchTableDetails;
	}
	public List<SearchMasterScreenBO> getSearchMasterScreenBOList() {
		return this.searchMasterScreenBOList;
	}
	public void setSearchMasterScreenBOList(
			List<SearchMasterScreenBO> searchMasterScreenBOList) {
		this.searchMasterScreenBOList = searchMasterScreenBOList;
	}
	public String getSearchLabel1() {
		return this.searchLabel1;
	}
	public void setSearchLabel1(String searchLabel1) {
		this.searchLabel1 = searchLabel1;
	}
	public String getSearchLabel2() {
		return this.searchLabel2;
	}
	public void setSearchLabel2(String searchLabel2) {
		this.searchLabel2 = searchLabel2;
	}
	public String getSearchLabel3() {
		return this.searchLabel3;
	}
	public void setSearchLabel3(String searchLabel3) {
		this.searchLabel3 = searchLabel3;
	}
	public String getSearchLabel4() {
		return this.searchLabel4;
	}
	public void setSearchLabel4(String searchLabel4) {
		this.searchLabel4 = searchLabel4;
	}
	public String getSearchInput1() {
		return this.searchInput1;
	}
	public void setSearchInput1(String searchInput1) {
		this.searchInput1 = searchInput1;
	}
	public String getSearchInput2() {
		return this.searchInput2;
	}
	public void setSearchInput2(String searchInput2) {
		this.searchInput2 = searchInput2;
	}
	public String getSearchInput3() {
		return this.searchInput3;
	}
	public void setSearchInput3(String searchInput3) {
		this.searchInput3 = searchInput3;
	}
	public String getSearchInput4() {
		return this.searchInput4;
	}
	public void setSearchInput4(String searchInput4) {
		this.searchInput4 = searchInput4;
	}
/*	public void reset(ActionMapping mapping,HttpServletRequest request) {		
		 this.searchMasterScreenBOList = ListUtils.lazyList(new ArrayList<SearchMasterScreenBO>(),
			        new Factory() {
			            public Object create() {
			                return new SearchMasterScreenBO();
			            }
			        });
	}*/
	public String getSearchInput5() {
		return this.searchInput5;
	}
	public void setSearchInput5(String searchInput5) {
		this.searchInput5 = searchInput5;
	}
	public String getSearchLabel5() {
		return this.searchLabel5;
	}
	public void setSearchLabel5(String searchLabel5) {
		this.searchLabel5 = searchLabel5;
	}
	
	//clear all the labels in the search screen
	public void clearSearchMasterScreenForms(ActionMapping mapping,HttpServletRequest request){
		this.searchLabel1="";
		this.searchLabel2="";
		this.searchLabel3="";
		this.searchLabel4="";
		this.searchLabel5="";
		this.searchLabel6="";
		this.searchMasterScreenBOList = new ArrayList<SearchMasterScreenBO>();
		reset(mapping, request);
	}
	
	//clear all the input fields in the search screen
	public void clearSearchMasterInputForms(ActionMapping mapping,HttpServletRequest request){	
		//System.out.println("clearSearchMasterInputForms");
		this.searchInput1="";
		this.searchInput2="";
		this.searchInput3="";
		this.searchInput4="";
		this.searchInput5="";
		this.searchInput6="";
		this.searchInput11="";
		this.searchOptions = "N";	//Between bill dates D or normal search N
		this.setSortByAscending(false);
		reset(mapping, request);
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request){
		try{
			HttpSession session = request.getSession();
			String jndiName = (String) session.getAttribute("databaseName");			
			ProductTypeDAO productTypeDAO = new ProductTypeDAO(jndiName);
			if(productTypeDAO.isConnectionExists(jndiName)){
				this.productTypeBOList  = productTypeDAO.executeAllProductType();
			}else {
				this.productTypeBOList  = new ArrayList<ProductTypeBO>();
			}			
		     session.setAttribute( "productTypeBOList", productTypeBOList);
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
		setUserLoggedIn(request);
	}
	public String getSearchInput6() {
		return this.searchInput6;
	}
	public void setSearchInput6(String searchInput6) {
		this.searchInput6 = searchInput6;
	}
	public String getSearchLabel6() {
		return this.searchLabel6;
	}
	public void setSearchLabel6(String searchLabel6) {
		this.searchLabel6 = searchLabel6;
	}
	public String getSearchLabel7() {
		return this.searchLabel7;
	}
	public void setSearchLabel7(String searchLabel7) {
		this.searchLabel7 = searchLabel7;
	}
	public String getSearchInput7() {
		return this.searchInput7;
	}
	public void setSearchInput7(String searchInput7) {
		this.searchInput7 = searchInput7;
	}
	public String getSearchLabel8() {
		return this.searchLabel8;
	}
	public void setSearchLabel8(String searchLabel8) {
		this.searchLabel8 = searchLabel8;
	}
	public String getSearchInput8() {
		return this.searchInput8;
	}
	public void setSearchInput8(String searchInput8) {
		this.searchInput8 = searchInput8;
	}
	public String getSearchOptions() {
		return searchOptions;
	}
	public void setSearchOptions(String searchOptions) {
		this.searchOptions = searchOptions;
	}
	public String getSearchLabel9() {
		return this.searchLabel9;
	}
	public void setSearchLabel9(String searchLabel9) {
		this.searchLabel9 = searchLabel9;
	}
	public String getSearchLabel10() {
		return this.searchLabel10;
	}
	public void setSearchLabel10(String searchLabel10) {
		this.searchLabel10 = searchLabel10;
	}
	public String getSearchLabel11() {
		return searchLabel11;
	}
	public void setSearchLabel11(String searchLabel11) {
		this.searchLabel11 = searchLabel11;
	}
	public String getSearchInput11() {
		return searchInput11;
	}
	public void setSearchInput11(String searchInput11) {
		this.searchInput11 = searchInput11;
	}
	public List<ProductTypeBO> getProductTypeBOList() {
		return productTypeBOList;
	}
	public void setProductTypeBOList(List<ProductTypeBO> productTypeBOList) {
		this.productTypeBOList = productTypeBOList;
	}
	
	public ActionErrors validateAuction(ActionMapping mapping, HttpServletRequest request)  {
		//String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    String jndiName = (String)session.getAttribute("databaseName");
	    CustomerBusinessValidation customerBusinessValidation = new CustomerBusinessValidation(jndiName);
	  try{
		 customerBusinessValidation.validateCustomerNameForSearch(errors, this.searchInput3, jndiName);
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }
	   /*else if(action.equals("CREATE")){
	    	CustomerValidation.validateCreateCustomer(errors, this);
	    }*/
	   /* if ((this.name == null)  || (this.customerID == null))
		      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));
	    else if ((this.name.length() == 0) || (this.customerID.length() == 0))
	      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));*/
	    return errors;
	  }
}
