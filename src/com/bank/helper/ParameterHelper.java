package com.bank.helper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bank.bo.ParametersBO;
import com.bank.bo.UserAuthenticationBO;
import com.bank.dao.ParametersDAO;
import com.bank.util.BankConstant;


public class ParameterHelper {

	private Map<String, String> parameterMap = null;
	
	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public ParameterHelper(String jndiName,HttpServletRequest request){
		try{	
			setParameterMap(getParameterMapFromSession(request));
			//System.out.println(this.parameterMap.size());
			if (getParameterMap() == null){
				setParameterMapToSession(request, jndiName);
			}			
		}catch(Exception ex){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getParameterMapFromSession(HttpServletRequest request){
		HttpSession session = request.getSession();			
		return (Map<String, String>)session.getAttribute("parameterMap");		
	}
	
	public Map<String, String> setParameterMapToSession(HttpServletRequest request,String jndiName) throws SQLException, Exception{
		HttpSession session = request.getSession();
		generateParameterMap(jndiName, getLoginID(request));
		session.setAttribute("parameterMap", getParameterMap());	
		Map<String, String> parameterMap = getParameterMapFromSession(request);
		//MySQLConnection.databaseName = parameterMap.get(BankConstant.DATABASE_NAME);
		//session.setAttribute("databaseName",parameterMap.get(BankConstant.DATABASE_NAME) );
		session.setAttribute(BankConstant.RECORDS_PER_PAGE,parameterMap.get(BankConstant.RECORDS_PER_PAGE) );
		return parameterMap;
	}
	
	private void generateParameterMap(String jndiName,String loginID) throws SQLException, Exception{
		List<ParametersBO> parameterList=null;
		Map<String, String> parameterMap = new HashMap<String, String>();
		ParametersDAO parametersDAO = new ParametersDAO(jndiName);				
		parameterList = parametersDAO.executeAllParameters();			
		parameterMap.put(BankConstant.LOGIN_ID, loginID);								//set login id
		for(ParametersBO parametersBO : parameterList){
			parameterMap.put(parametersBO.getParamID(), parametersBO.getParamValue());
		}		
		setParameterMap(parameterMap);
	}
	
	public  String getLoginID(){
		return this.parameterMap.get(BankConstant.LOGIN_ID);
	}
	
	public  String getCurrentBillSerial(){
		return this.parameterMap.get(BankConstant.CURRENT_SERIAL);
	}
	
	public  String getCurrentBillRedemSerial(){
		return this.parameterMap.get(BankConstant.BILL_REDEM_CURRENT_SERIAL);
	}		
	
	public  String getSearchSerials(){
		return this.parameterMap.get(BankConstant.SEARCH_SERIALS);
	}	
	
	public  String getCurrentDate(){
		return this.parameterMap.get(BankConstant.CURRENT_DATE);
	}

	public  String getRedemCurrentDate(){
		return this.parameterMap.get(BankConstant.REDEM_CURRENT_DATE);
	}
	
	public  Integer getMonthlyIncome(){
		return Integer.parseInt((String)this.parameterMap.get(BankConstant.MONTHLY_INCOME));
	}
	
	public  Double getRateOfInterest(){
		return Double.parseDouble((String)this.parameterMap.get(BankConstant.RATE_OF_INTEREST));
	}

	public  int getRecordsPerPage(){
		return Integer.parseInt((String)this.parameterMap.get(BankConstant.RECORDS_PER_PAGE));
	}
	
	public Map<String, String> getParameterMap() {
		return this.parameterMap;
	}
	
	public  String getShopName(){
		return this.parameterMap.get(BankConstant.SHOP_NAME);
	}
	
	public  String getShopNumber(){
		return this.parameterMap.get(BankConstant.SHOP_NO);
	}
	
	public  String getShopStreet(){
		return this.parameterMap.get(BankConstant.SHOP_STREET);
	}
	
	public  String getShopArea(){
		return this.parameterMap.get(BankConstant.SHOP_AREA);
	}
	
	public  String getShopType(){
		return this.parameterMap.get(BankConstant.SHOP_TYPE);
	}
	
	public  String getShopCity(){
		return this.parameterMap.get(BankConstant.SHOP_CITY);
	}
	
	public  String getShopPinCode(){
		return this.parameterMap.get(BankConstant.SHOP_PINCODE);
	}
	
	public  String getShopState(){
		return this.parameterMap.get(BankConstant.SHOP_STATE);
	}
	
	public  String getDatabaseIPAddress(){
		//System.out.println(this.parameterMap.get(BankConstant.DATABASE_IPADDRESS));
		return this.parameterMap.get(BankConstant.DATABASE_IPADDRESS);
	}
	
	public  String getDatabasePortNumber(){
		//System.out.println(this.parameterMap.get(BankConstant.MYSQL_PORT));
		return this.parameterMap.get(BankConstant.MYSQL_PORT);
	}

	public  String getDatabaseUserID(){
		//System.out.println(this.parameterMap.get(BankConstant.MYSQL_DB_USER_ID));
		return this.parameterMap.get(BankConstant.MYSQL_DB_USER_ID);
	}
	

	public  String getDatabasePassword(){
		//System.out.println(this.parameterMap.get(BankConstant.MYSQL_DB_PASSWORD));
		return this.parameterMap.get(BankConstant.MYSQL_DB_PASSWORD);
	}
	

	public  String getMySqlServerPath(){
		//System.out.println(this.parameterMap.get(BankConstant.MYSQL_DB_PATH));
		return this.parameterMap.get(BankConstant.MYSQL_DB_PATH);
		
	}
	
	
	
	public  String getShopDetails(){
		
		if(this.getShopName() != null){
			return this.getShopName() + this.getShopNumber() + this.getShopStreet() 
			+ this.getShopArea() + this.getShopCity() + this.getShopPinCode() 
			+ this.getShopState();	
		}else{
			return "";
		}
		
	}	 

	public String getBillDelete(){
		return this.parameterMap.get(BankConstant.BILL_DELETE);
	}
	
	public String getBillUpdate(){
		return this.parameterMap.get(BankConstant.BILL_UPDATE);
	}
	
	public String getAuctionDescription(){
		return this.parameterMap.get(BankConstant.AUCTION_DETAILS).replaceAll(BankConstant.LINEBREAK,BankConstant.ESCAPE_LINEBREAK);
	}
	
	public String getLoginID(HttpServletRequest request) {
		String loginID="";
		HttpSession session = request.getSession();
		UserAuthenticationBO userAuthenticationBO = null;
		userAuthenticationBO = (UserAuthenticationBO)session.getAttribute("loggedInUser");	
		if(userAuthenticationBO != null){
			loginID = userAuthenticationBO.getUserName();
		}
		return loginID;
	}
	
}
