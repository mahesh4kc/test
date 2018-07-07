package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.CustomerBO;
import com.bank.bo.ParametersBO;
import com.bank.form.SearchMasterScreenForm;
import com.bank.helper.CustomerHelper;
import com.bank.util.BankConstant;
import com.bank.util.FilterClausesConstant;

public class CustomerDAO extends BankDAO{
	public static final String CLASS_NAME = "CusomterDatabase";
	
	public static final String CREATE_CUSTOMER = "INSERT INTO CUSTOMER (CUSTOMERID, " +
			"CUSTOMERNAME, ADDRESS, STREET, AREA, DISTRICT, STATE, COUNTRY, PINCODE, PHOTO, " +
			"PHONENO, MOBILENO, MAILID , RELATIONSHIP, RELATIONNAME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET CUSTOMERNAME = ?, " +
	" ADDRESS = ?, STREET =? , AREA = ?, DISTRICT =? , STATE = ?, COUNTRY =? , PINCODE = ?, " +
	"PHOTO =? , PHONENO = ?, MOBILENO =? , MAILID = ?, RELATIONSHIP = ? , RELATIONNAME = ? WHERE CUSTOMERID = ?";
	
	public static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUSTOMERID = ?"; 	
 	
	public static final String SELECT_ALL_CUSTOMER = "SELECT " +
														"CUSTOMERID, CUSTOMERNAME, ADDRESS, STREET, AREA, DISTRICT, STATE, COUNTRY, PINCODE, PHOTO, " +
														"PHONENO, MOBILENO, MAILID,RELATIONSHIP, RELATIONNAME FROM CUSTOMER ";	
		
	public static final String SELECT_CUSTOMER_ID_EQUAL  = SELECT_ALL_CUSTOMER + 
															FilterClausesConstant.WHERE + " CUSTOMERID " + FilterClausesConstant.EQUAL + FilterClausesConstant.QUESTIONAIRE ;
	
	public static final String SELECT_CUSTOMER_NAME_EQUAL = SELECT_ALL_CUSTOMER + 
															FilterClausesConstant.WHERE + " CUSTOMERNAME " + FilterClausesConstant.EQUAL + FilterClausesConstant.QUESTIONAIRE ;
	public static final String SELECT_CUSTOMER_ID_IN = SELECT_ALL_CUSTOMER + 
	FilterClausesConstant.WHERE + " CUSTOMERID " + FilterClausesConstant.IN ;

	
	public static final String SELECT_CUSTOMER_ID_NAME_LIKE  = SELECT_ALL_CUSTOMER + FilterClausesConstant.WHERE + 
																//" CUSTOMERID " + FilterClauses.LIKE + FilterClauses.QUESTIONAIRE + FilterClauses.AND +
																" CUSTOMERNAME " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE ;
	
	public static final String SELECT_CUSTOMER_NAME_LIKE  = SELECT_ALL_CUSTOMER + FilterClausesConstant.WHERE + 
	//" CUSTOMERID " + FilterClauses.LIKE + FilterClauses.QUESTIONAIRE + FilterClauses.AND +
	" CUSTOMERNAME " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE ;

	
	public static final String SELECT_CUSTOMER_ID_NAME_ADDRESS_STREET_AREA_MOBILE_LIKE  = SELECT_ALL_CUSTOMER + FilterClausesConstant.WHERE + 
																" CUSTOMERID " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
																" CUSTOMERNAME " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
																" ADDRESS " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
																" STREET " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
																" AREA " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
																" MOBILENO " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE ;
	
	/*
 	 *public static final String SELECT_CUSTOMER_CODE = "SELECT PRODUCTTYPENO, " +
			"PRODUCTTYPECODE, PRODUCTTYPEDESCRIPTION, RATEOFINTEREST FROM PRODUCTTYPE WHERE " +
	"PRODUCTTYPECODE = ?";
	 */	
	public static final String SELECT_CUSTOMER_MAX_NO = "SELECT MAX(CUSTOMERID) FROM CUSTOMER";
	
	private String jndiName;
	
	
	public String getJndiName() {
		return this.jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/*
	 * 
	 */
	
	public CustomerDAO(String jndiName) {
		super();
		//objConnection = getConnection(jndiName);
		this.jndiName = jndiName;
	}
	
	public void createCustomer(CustomerBO objCustomerBO ) throws SQLException{
		final String  METHOD_NAME = "createCustomer";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		
		int noOfInsertedRecords =0;
		int customerSequence =0;
		try{		
		//objConnection = getConnection();		 	 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(CREATE_CUSTOMER);
		 customerSequence = getMaxCustomerNo();//System.out.println(" Next Sequence for Customer is : " + customerSequence);
		 objPreparedStatement.setInt(1, new Integer(customerSequence));
		 objPreparedStatement.setString(2, objCustomerBO.getName() != null ? objCustomerBO.getName().toUpperCase():"");
		 objPreparedStatement.setString(3, objCustomerBO.getAddress() != null ? objCustomerBO.getAddress().toUpperCase():"");
		 objPreparedStatement.setString(4, objCustomerBO.getStreet() != null ? objCustomerBO.getStreet().toUpperCase():"");
		 objPreparedStatement.setString(5, objCustomerBO.getArea() != null ? objCustomerBO.getArea().toUpperCase():"");
		 objPreparedStatement.setString(6, objCustomerBO.getDistrict() != null ? objCustomerBO.getDistrict().toUpperCase():"");
		 objPreparedStatement.setString(7, objCustomerBO.getState() != null ? objCustomerBO.getState().toUpperCase():"");
		 objPreparedStatement.setString(8, objCustomerBO.getCountry() != null ? objCustomerBO.getCountry().toUpperCase():"");
		 objPreparedStatement.setInt(9, objCustomerBO.getPincode() != null ? objCustomerBO.getPincode():0);
		 objPreparedStatement.setString(10, objCustomerBO.getPhoto() != null ? objCustomerBO.getPhoto().toUpperCase():"");
		 objPreparedStatement.setLong(11, objCustomerBO.getPhoneNo() != null ? objCustomerBO.getPhoneNo():0);
		 objPreparedStatement.setLong(12, objCustomerBO.getMobileNo() != null ? objCustomerBO.getMobileNo():0);
		 objPreparedStatement.setString(13, objCustomerBO.getMailID() != null ? objCustomerBO.getMailID().toUpperCase():"");
		 objPreparedStatement.setString(14, objCustomerBO.getRelationShip() != null ? objCustomerBO.getRelationShip().toUpperCase():"");
		 objPreparedStatement.setString(15, objCustomerBO.getRelationName() != null ? objCustomerBO.getRelationName().toUpperCase():"");
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		// System.out.println(noOfInsertedRecords + " Record inserted");
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objConnection = null;
		}
	
			
	}
	public void updateCustomer(CustomerBO objCustomerBO ){
		final String  METHOD_NAME = "updateCustomer";
		int noOfInsertedRecords =0;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_CUSTOMER);		
		 objPreparedStatement.setString(1, objCustomerBO.getName() != null ? objCustomerBO.getName().toUpperCase():"");
		 objPreparedStatement.setString(2, objCustomerBO.getAddress() != null ? objCustomerBO.getAddress().toUpperCase():"");
		 objPreparedStatement.setString(3, objCustomerBO.getStreet() != null ? objCustomerBO.getStreet().toUpperCase():"");
		 objPreparedStatement.setString(4, objCustomerBO.getArea() != null ? objCustomerBO.getArea().toUpperCase():"");
		 objPreparedStatement.setString(5, objCustomerBO.getDistrict() != null ? objCustomerBO.getDistrict().toUpperCase():"");
		 objPreparedStatement.setString(6, objCustomerBO.getState() != null ? objCustomerBO.getState().toUpperCase():"");
		 objPreparedStatement.setString(7, objCustomerBO.getCountry() != null ? objCustomerBO.getCountry().toUpperCase():"");
		 objPreparedStatement.setInt(8, objCustomerBO.getPincode() != null ? objCustomerBO.getPincode():0);
		 objPreparedStatement.setString(9, objCustomerBO.getPhoto() != null ? objCustomerBO.getPhoto().toUpperCase():"");
		 objPreparedStatement.setLong(10, objCustomerBO.getPhoneNo() != null ? objCustomerBO.getPhoneNo():0);
		 objPreparedStatement.setLong(11, objCustomerBO.getMobileNo() != null ? objCustomerBO.getMobileNo():0);
		 objPreparedStatement.setString(12, objCustomerBO.getMailID() != null ? objCustomerBO.getMailID().toUpperCase():"");
		 objPreparedStatement.setString(13, objCustomerBO.getRelationShip() != null ? objCustomerBO.getRelationShip().toUpperCase():"");
		 objPreparedStatement.setString(14, objCustomerBO.getRelationName() != null ? objCustomerBO.getRelationName().toUpperCase():"");
		 //System.out.println("Customer ID : "+objCustomerBO.getCustomerID());
		 objPreparedStatement.setInt(15, objCustomerBO.getCustomerID());
		// noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 //System.out.println(noOfInsertedRecords + " Record updated");
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objPreparedStatement = null;
			objConnection = null;
		}
			
	}
	
	public int deleteCustomer(CustomerBO objCustomerBO ){
		final String  METHOD_NAME = "deleteCustomer";
		int noOfInsertedRecords =0;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(DELETE_CUSTOMER);		 
		 objPreparedStatement.setInt(1, objCustomerBO.getCustomerID());
		// objPreparedStatement.setString(2, objCustomerBO.getCustomerDescription().toUpperCase());
		 //objPreparedStatement.setDouble(3, objCustomerBO.getCustomerRateOfInterest());
		 //objPreparedStatement.setInt(4,  objCustomerBO.getCustomerNo());
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 //System.out.println(noOfInsertedRecords + " Record updated");
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		 return noOfInsertedRecords;
			
	}
	
	public CustomerBO executeCustomerID(String customerID ) throws SQLException{
		final String  METHOD_NAME = "executeCustomerNo";
		CustomerBO customerBO=null;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{
			
		// objConnection = getConnection();		 
		 //System.out.println("Connection is Alive in executeCustomerNo: " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_ID_EQUAL);	
		
		 //System.out.println(" Customer id = " + customerID);
		 //System.out.println(SELECT_CUSTOMER_ID_EQUAL + customerID );
		 objPreparedStatement.setInt(1, Integer.parseInt(customerID));
		 
		
		 ResultSet objCustomerResultSet = objPreparedStatement.executeQuery();
		while (objCustomerResultSet.next()){
			customerBO = CustomerHelper.getCustomerBOFromResultSet(objCustomerResultSet);				
		}
		
		//executeCustomer();		
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		 return customerBO;			
	}
	
	public CustomerBO executeCustomerName(String customerName ) throws SQLException{
		final String  METHOD_NAME = "executeCustomerName";
		CustomerBO customerBO=null;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{
			
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive in executeCustomerName: " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_NAME_EQUAL);	
		
		 //System.out.println(" Customer Name = " + customerName);
		 //System.out.println(SELECT_CUSTOMER_NAME_EQUAL + customerName.trim() );
		 objPreparedStatement.setString(1, customerName);
		 
		
		 ResultSet objCustomerResultSet = objPreparedStatement.executeQuery();
		while (objCustomerResultSet.next()){
			customerBO = CustomerHelper.getCustomerBOFromResultSet(objCustomerResultSet);				
		}
		
		//executeCustomer();		
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		 return customerBO;			
	}
	
	public List<CustomerBO> executeAllCustomers() throws SQLException{
		//final String  METHOD_NAME = "executeAllCustomers";	
		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{	
			//objConnection= getConnection();	
			 //System.out.println("Connection is Alive in executeCustomer: " + !objConnection.isClosed());
			//objPreparedStatement.clearParameters();
			 objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_CUSTOMER);			
			 resultSet = objPreparedStatement.executeQuery();		 	 
		
			customerList = CustomerHelper.getCustomerListFromResultSet(resultSet, customerList);
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		
		/*}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
			 
		}*/	
		return customerList;
	}
	
	public List<CustomerBO> executeAllCustomers(int recordsPerPage, String customerID,String customerName) throws SQLException{
		//final String  METHOD_NAME = "executeAllCustomers";	
		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		ResultSet customerResultSet=null;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
			//objConnection= getConnection();	
			// System.out.println("Connection is Alive in executeCustomer: " + !objConnection.isClosed());
			 //objPreparedStatement.clearParameters();
			 objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_ID_NAME_LIKE);
			 //System.out.println(SELECT_CUSTOMER_ID_NAME_LIKE + customerID  + ","+ customerName);
			// objPreparedStatement.setString(1, customerID+"%");
			// objPreparedStatement.setInt(1, "3");
			 objPreparedStatement.setString(1, customerName+"%");
			 customerResultSet = objPreparedStatement.executeQuery();		 	 
		
			customerList = CustomerHelper.getCustomerListFromResultSet(customerResultSet, customerList);
		
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		/*}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
			 
		}*/	
		return customerList;
	}
	
	public List<CustomerBO> executeAllCustomers(String customerNameLike) throws SQLException{
		//final String  METHOD_NAME = "executeAllCustomers";	
		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
			//objConnection= getConnection();	
			// System.out.println("Connection is Alive in executeCustomer: " + !objConnection.isClosed());
			 //objPreparedStatement.clearParameters();
			 objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_NAME_LIKE);
			 //System.out.println(SELECT_CUSTOMER_NAME_LIKE +  customerNameLike);
			// objPreparedStatement.setString(1, customerID+"%");
			// objPreparedStatement.setInt(1, "3");
			 objPreparedStatement.setString(1, customerNameLike+"%");
			 resultSet = objPreparedStatement.executeQuery();		 	 
		
			customerList = CustomerHelper.getCustomerListFromResultSet(resultSet, customerList);
		
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		/*}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
			 
		}*/	
		return customerList;
	}
	
	public List<CustomerBO> executeAllCustomersByCustomerIdIn(String customerIds) throws SQLException{
		//final String  METHOD_NAME = "executeAllCustomersByCustomerIdIn";	
		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
			//objConnection= getConnection();	
			// System.out.println("Connection is Alive in executeCustomer: " + !objConnection.isClosed());
			 //objPreparedStatement.clearParameters();
			 objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_ID_IN
					 +FilterClausesConstant.LEFT_PARANTHESIS + customerIds + FilterClausesConstant.RIGHT_PARANTHESIS);
			 //System.out.println(SELECT_CUSTOMER_ID_IN +  customerIds);
			// objPreparedStatement.setString(1, customerID+"%");
			// objPreparedStatement.setInt(1, "3");
			 //objPreparedStatement.setString(1, customerIds);
			 resultSet = objPreparedStatement.executeQuery();		 	 
		
			customerList = CustomerHelper.getCustomerListFromResultSet(resultSet, customerList);
		
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		/*}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
			 
		}*/	
		return customerList;
	}
	
	public List<CustomerBO> executeAllCustomers(int recordsPerPage, int currentRecord,SearchMasterScreenForm searchMasterScreenForm , 
			String jndiName, String customerID,String customerName,String address, 
			String street,String area,String mobileNumber) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeAllCustomers";	
		List<CustomerBO> customerList=new ArrayList<CustomerBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{
			ParametersBO parametersBO=null;
			ParametersDAO parametersDAO = new ParametersDAO(jndiName);
			
			parametersBO = parametersDAO.executeParameterIDEqual(BankConstant.RECORDS_PER_PAGE);
			//objConnection= getConnection();	
			// System.out.println("BankConstant.RECORDS_PER_PAGE " + parametersBO.getParamValue());
			 //objPreparedStatement.clearParameters();
			 objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_ID_NAME_ADDRESS_STREET_AREA_MOBILE_LIKE );
					// + " LIMIT " + parametersBO.getParamValue());
			/* System.out.println(SELECT_CUSTOMER_ID_NAME_ADDRESS_STREET_AREA_MOBILE_LIKE 
					 + customerID  + ","+ customerName + ","+ address  + ","
					 + street  + ","+ area  + ","+ mobileNumber
					 + " LIMIT " + parametersBO.getParamValue());*/
			// objPreparedStatement.setString(1, customerID+"%");
			// objPreparedStatement.setInt(1, "3");
			 objPreparedStatement.setString(1, customerID+"%");
			 objPreparedStatement.setString(2, customerName+"%");
			 objPreparedStatement.setString(3, address+"%");
			 objPreparedStatement.setString(4, street+"%");
			 objPreparedStatement.setString(5, area+"%");
			 objPreparedStatement.setString(6, mobileNumber+"%");
			 resultSet = objPreparedStatement.executeQuery();		 	 
		
			 CustomerHelper customerHelper = new CustomerHelper();
			customerList = customerHelper.getSearchCustomerListFromResultSet(recordsPerPage, 
					currentRecord,searchMasterScreenForm,resultSet, customerList);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		
		/*}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
			 
		}*/	
		return customerList;
	}
	/*
	 * 
	 
	public CustomerBO executeCustomerCode(String customerCode ){
		final String  METHOD_NAME = "executeCustomerCode";
		CustomerBO objCustomerBO = new CustomerBO();
		try{		
		 objConnection = new MySQLConnection().getConnection();		 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_CODE);	
		 System.out.println(" Value for Product Type is : " + customerCode);
		 objPreparedStatement.setString(1, customerCode);		
		 ResultSet objCustomerResultSet = objPreparedStatement.executeQuery();
		 if (objCustomerResultSet == null)
			 System.out.println("No records found" +objCustomerResultSet.getInt(1) );
		while (objCustomerResultSet.next()){
			objCustomerBO.setCustomerNo(objCustomerResultSet.getInt(1));
			objCustomerBO.setCustomerCode(objCustomerResultSet.getString(2));
			objCustomerBO.setCustomerDescription(objCustomerResultSet.getString(3));
			objCustomerBO.setCustomerRateOfInterest(objCustomerResultSet.getDouble(4));
		}
				
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		}
		 return objCustomerBO;			
	}
	*/
	private Integer getMaxCustomerNo(){
		final String  METHOD_NAME = "getMaxCustomerNo";
		PreparedStatement objPreparedStatementQuery;
		int maxCustomer =0;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_CUSTOMER_MAX_NO);				
		  resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			maxCustomer = resultSet.getInt(1);			
		}				
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		// System.out.println("Next Sequence for Customer : " + maxCustomer);
		 return (maxCustomer+1);			
	}

	public boolean customerExistsByCustomerId(String customerID) throws SQLException{
		boolean customerExists = false;
		
		try{		
		CustomerBO customerBO;		 
		customerBO = executeCustomerID(customerID);
		if (customerBO != null){
			customerExists = true;
		}
	}finally{
		
	}
		return customerExists;
	}
	
	public boolean customerExistsByCustomerName(String customerName) throws SQLException{
		boolean customerExists = false;
		try{
		
		CustomerBO customerBO;		 
		customerBO = executeCustomerName(customerName);
		if (customerBO != null){
			customerExists = true;
		}
		}finally{
			
		}
		return customerExists;
	}
}
