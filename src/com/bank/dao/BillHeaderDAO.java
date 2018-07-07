package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.BillHeaderBO;
import com.bank.form.SearchMasterScreenForm;
import com.bank.helper.BillHeaderHelper;
import com.bank.helper.ParameterHelper;
import com.bank.util.BankConstant;
import com.bank.util.DateUtil;
import com.bank.util.FilterClausesConstant;
import com.bank.util.LogMethods;

public class BillHeaderDAO extends BankDAO{
	public static final String className = "BillHeaderDAO";
		
	public static final String CREATE_BILL_HEADER = "INSERT INTO BILL_HEADER " +
	" (BILL_SEQUENCE, BILL_SERIAL, BILL_NO, BILL_DATE, CUSTOMERID, CAREOF,  PRODUCT_TYPE_NO,  RATE_OF_INTEREST,  AMOUNT,  AMOUNT_IN_WORDS, " +
	"  PRESENT_VALUE,  GRAMS,  MONTHLY_INCOME,REDEMPTION_DATE,REDEMPTION_INTEREST,REDEMPTION_TOTAL,REDEMPTION_STATUS,COMMENTS) " +
	"  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
	
	public static final String UPDATE_BILL_HEADER = "UPDATE BILL_HEADER SET BILL_SERIAL = ?, " +
	" BILL_NO = ?, BILL_DATE =?, CUSTOMERID =? , CAREOF =?, PRODUCT_TYPE_NO = ?, RATE_OF_INTEREST =? " +
	", AMOUNT =?, AMOUNT_IN_WORDS = ?, PRESENT_VALUE =?, GRAMS =?, MONTHLY_INCOME = ? " +
	", REDEMPTION_DATE =?, REDEMPTION_INTEREST = ?, REDEMPTION_TOTAL =?, REDEMPTION_STATUS =? " +
	", BILL_REDEM_SERIAL = ? , BILL_REDEM_NO = ?, COMMENTS = ? WHERE BILL_SEQUENCE = ?";
	
	public static final String DELETE_BILL_HEADER = "DELETE FROM BILL_HEADER WHERE BILL_SEQUENCE = ?";
	
	public static final String SELECT_ALL_BILL_HEADER = "SELECT " +
    " BILL_SEQUENCE, BILL_SERIAL, BILL_NO, BILL_DATE, CUSTOMERID, CAREOF, PRODUCT_TYPE_NO, RATE_OF_INTEREST, " +
    " AMOUNT, AMOUNT_IN_WORDS, PRESENT_VALUE, GRAMS, MONTHLY_INCOME,REDEMPTION_DATE,REDEMPTION_INTEREST,REDEMPTION_TOTAL,REDEMPTION_STATUS, " +
    " BILL_REDEM_SERIAL,BILL_REDEM_NO,COMMENTS FROM BILL_HEADER ";
	public static final String SELECT_BILL_HEADER_BY_BILL_SEQUENCE =  SELECT_ALL_BILL_HEADER +
																		FilterClausesConstant.WHERE + " BILL_SEQUENCE " + FilterClausesConstant.EQUAL + " ? ";
	
	
	
	public static final String SELECT_BILL_HEADER_BY_BILL_SERIAL_NO =  SELECT_ALL_BILL_HEADER +
	FilterClausesConstant.WHERE + " BILL_SERIAL " + FilterClausesConstant.EQUAL + " ? " +
	FilterClausesConstant.AND + " BILL_NO " + FilterClausesConstant.EQUAL + " ? ";
	
/*	public static final String SELECT_BILL_HEADER_BY_BILL_SERIAL_NO =  "SELECT " +
    " BILL_SEQUENCE, BILL_SERIAL, BILL_NO, BILL_DATE, CUSTOMERID, CAREOF, PRODUCT_TYPE_NO, RATE_OF_INTEREST, " +
    " AMOUNT, AMOUNT_IN_WORDS, PRESENT_VALUE, GRAMS, MONTHLY_INCOME " +
    " FROM BILL_HEADER " +
			" WHERE BILL_SERIAL = ? AND BILL_NO = ? ";*/
	public static final String SELECT_BILL_HEADER_MAX_SEQUENCE = "SELECT MAX(BILL_SEQUENCE) FROM BILL_HEADER ";
	
	public static final String SELECT_BILL_HEADER_NEXT_BILL_REDEM_SERIAL = "SELECT MAX(BILL_REDEM_NO)+1 FROM BILL_HEADER WHERE BILL_REDEM_SERIAL = ? ";
	
	public static final String SELECT_BILL_HEADER_NEXT_BILL_SERIAL = "SELECT MAX(BILL_NO)+1 FROM BILL_HEADER WHERE BILL_SERIAL = ? ";
	
	//public static final String ORDER_BY_BILL_HEADER = " ORDER BY  BILL_NO DESC";
	
	public static final String ORDER_BY_BILL_SEQUENCE = " ORDER BY  BILL_SEQUENCE DESC";
	
	public static final String ORDER_BY_BILL_HEADER_REDEM_NO = " ORDER BY STR_TO_DATE(REDEMPTION_DATE, '%d/%m/%Y' ) DESC, BILL_REDEM_NO DESC";
	
	public static final String ORDER_BY_BILL_SERIAL = " BILL_SERIAL ";
	
	public static final String SELECT_BILL_SEQ_SERIAL_AMOUNT_REDEMSTATUS_REDEMDATE_LIKE  = SELECT_ALL_BILL_HEADER + FilterClausesConstant.WHERE + 
											" BILL_NO " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
											" BILL_DATE " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
											//" CUSTOMERID " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
											" AMOUNT " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
											" REDEMPTION_STATUS " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +											
											" REDEMPTION_DATE " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +	
											ORDER_BY_BILL_SERIAL + FilterClausesConstant.IN;

	public static final String SELECT_BILL_SEQ_SERIAL_CUSTOMER_IN_AMOUNT_REDEMSTATUS_LIKE  = SELECT_ALL_BILL_HEADER + FilterClausesConstant.WHERE + 
	" BILL_NO " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
	" BILL_DATE " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
	//" CUSTOMERID " + FilterClausesConstant.IN + FilterClausesConstant.LEFT_PARANTHESIS + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.RIGHT_PARANTHESIS + FilterClausesConstant.AND +
	" AMOUNT " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
	" REDEMPTION_STATUS " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +											
	" REDEMPTION_DATE " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE ;

	
	/*public static final String SELECT_BILL_SEQ_SERIAL_CUSTOMER_AMOUNT_REDEMSTATUS_LIKE  = 
		"SELECT * FROM BILL_DETAIL WHERE PRODUCT_DESCRIPTION LIKE '%' AND BILL_SEQUENCE IN" +
	"(SELECT BILL_SEQUENCE FROM BILL_HEADER WHERE BILL_NO LIKE '%' AND BILL_DATE LIKE '%' " +
	"AND REDEMPTION_STATUS   LIKE '%' AND CUSTOMERID IN " + 
	"(SELECT CUSTOMERID FROM CUSTOMER WHERE CUSTOMERNAME LIKE '%'))";*/
	
	public static final String SELECT_DISTINCT_BILL_SERIAL = "SELECT DISTINCT BILL_SERIAL FROM BILL_HEADER" +
																" ORDER BY  BILL_SERIAL";
	
	public static final String SELECT_DISTINCT_BILL_SERIAL_NO = "SELECT DISTINCT BILL_NO FROM BILL_HEADER" +
	" ORDER BY  BILL_NO";

	public static final String SELECT_BILL_HEADER_BY_BILL_DATE_BETWEEN =  SELECT_ALL_BILL_HEADER +
	FilterClausesConstant.WHERE +
	" AMOUNT " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +
	" REDEMPTION_STATUS " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND +											
	" REDEMPTION_DATE " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE + FilterClausesConstant.AND + 
	ORDER_BY_BILL_SERIAL + FilterClausesConstant.IN ;

	public static final String FILTER_BY_BILL_HEADER_PRODUCT_TYPE = " PRODUCT_TYPE_NO = ";
	
	public static final String EXCLUDE_AUCTION_BILLS = FilterClausesConstant.AND + " REDEMPTION_STATUS " + 
	FilterClausesConstant.NOT_IN + FilterClausesConstant.LEFT_PARANTHESIS + " 'A' " + FilterClausesConstant.RIGHT_PARANTHESIS;
	//STR_TO_DATE(bill_date, '%d/%m/%Y') between '2011-05-28' and '2011-05-29'
	
	//Connection objConnection=null;
	

	
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
	
	public BillHeaderDAO(String jndiName) {
		super();
		//objConnection = getConnection(jndiName);
		this.jndiName = jndiName;
	}
	
	public Integer createBillHeader(BillHeaderBO billHeaderBO ) throws SQLException{
		//final String  METHOD_NAME = "createProductType";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		int noOfInsertedRecords=0;
		int billSequence =0;

		
		
				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(CREATE_BILL_HEADER);		
			 if(billHeaderBO != null){
				 billSequence = getMaxBillSequenceNumber();
				 //System.out.println(" Next Sequence is : " + billSequence);
				 objPreparedStatement.setInt(1, new Integer(billSequence));
				 objPreparedStatement.setString(2, billHeaderBO.getBillSerial().toUpperCase());
				 objPreparedStatement.setInt(3, billHeaderBO.getBillNumber());
				 objPreparedStatement.setString(4, billHeaderBO.getBillDate().toUpperCase());	
				 objPreparedStatement.setInt(5, billHeaderBO.getCustomerID());
				 objPreparedStatement.setString(6, billHeaderBO.getCareOf().toUpperCase());
				 objPreparedStatement.setInt(7, billHeaderBO.getProductTypeNumber());
				 objPreparedStatement.setDouble(8, billHeaderBO.getRateOfInterest());
				 objPreparedStatement.setInt(9, billHeaderBO.getAmount());
				 objPreparedStatement.setString(10, billHeaderBO.getAmountInWords().toUpperCase());
				 objPreparedStatement.setInt(11, billHeaderBO.getPresentValue());
				 objPreparedStatement.setDouble(12, billHeaderBO.getGrams());
				 objPreparedStatement.setInt(13, billHeaderBO.getMonthlyIncome());				 
				 objPreparedStatement.setString(14, 
						 (billHeaderBO.getRedemptionDate()!=null?billHeaderBO.getRedemptionDate().toUpperCase():""));
				 objPreparedStatement.setDouble(15, 
						 (billHeaderBO.getRedemptionInterest()!=null?billHeaderBO.getRedemptionInterest():0.0));
				 objPreparedStatement.setDouble(16, 
						 (billHeaderBO.getRedemptionTotal()!=null?billHeaderBO.getRedemptionTotal():0.0));
				 objPreparedStatement.setString(17, 
						 (billHeaderBO.getRedemptionStatus()!=null?billHeaderBO.getRedemptionStatus().toUpperCase():"O"));							 
				 objPreparedStatement.setString(18, 
						 (billHeaderBO.getComments()!=null?billHeaderBO.getComments().toUpperCase():""));
			 }					
			 
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();
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
		 return billSequence;
	}
	public void updateBillHeader(BillHeaderBO billHeaderBO ){
		final String  METHOD_NAME = "updateBillHeader";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		int noOfInsertedRecords=0;
				try{	
		 //objConnection = getConnection();		 
		// //System.out.println("Connection is Alive : and update the bill" + !objConnection.isClosed());
		// //System.out.println("UPDATE STATEMENT " + billHeaderBO.getCustomerID() + UPDATE_BILL_HEADER + billHeaderBO.getBillSequence());
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_BILL_HEADER);
			 if(billHeaderBO!= null){				
				 objPreparedStatement.setString(1, billHeaderBO.getBillSerial().toUpperCase());
				 objPreparedStatement.setInt(2, billHeaderBO.getBillNumber());
				 objPreparedStatement.setString(3, billHeaderBO.getBillDate().toUpperCase());	
				 objPreparedStatement.setInt(4, billHeaderBO.getCustomerID());
				 objPreparedStatement.setString(5, billHeaderBO.getCareOf().toUpperCase());
				 objPreparedStatement.setInt(6, billHeaderBO.getProductTypeNumber());
				 objPreparedStatement.setDouble(7, billHeaderBO.getRateOfInterest());
				 objPreparedStatement.setInt(8, billHeaderBO.getAmount());
				 objPreparedStatement.setString(9, billHeaderBO.getAmountInWords().toUpperCase());
				 objPreparedStatement.setInt(10, billHeaderBO.getPresentValue());
				 objPreparedStatement.setDouble(11, billHeaderBO.getGrams());
				 objPreparedStatement.setInt(12, billHeaderBO.getMonthlyIncome());								 				 
				 objPreparedStatement.setString(13, 
						 (billHeaderBO.getRedemptionDate()!=null?billHeaderBO.getRedemptionDate().toUpperCase():""));
				 objPreparedStatement.setDouble(14,
						 (billHeaderBO.getRedemptionInterest()!=null?billHeaderBO.getRedemptionInterest():0.0));
				 objPreparedStatement.setDouble(15, 
						 (billHeaderBO.getRedemptionTotal()!=null?billHeaderBO.getRedemptionTotal():0.0));
				 objPreparedStatement.setString(16, 
						 (billHeaderBO.getRedemptionStatus()!=null?billHeaderBO.getRedemptionStatus().toUpperCase():"O"));						
				 objPreparedStatement.setString(17, 
						 (billHeaderBO.getBillRedemSerial()!=null?billHeaderBO.getBillRedemSerial().toUpperCase():""));
				 objPreparedStatement.setInt(18, 
						 (billHeaderBO.getBillRedemNumber()!=null?billHeaderBO.getBillRedemNumber():0));
				 objPreparedStatement.setString(19, 
						 (billHeaderBO.getComments()!=null?billHeaderBO.getComments():""));
				 //Primary key
				 objPreparedStatement.setInt(20, billHeaderBO.getBillSequence());
			 }			 
			 noOfInsertedRecords = objPreparedStatement.executeUpdate(); 
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
		// return objProductTypeBO;
			
	}
	
	public void deleteBillHeader(BillHeaderBO billHeaderBO ){
		final String  METHOD_NAME = "deleteBillHeader";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		int noOfInsertedRecords=0;
				try{		
		// objConnection =getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_BILL_HEADER);
		
		if(billHeaderBO != null){
			objPreparedStatement.setInt(1, billHeaderBO.getBillSequence());				 
		} 
			noOfInsertedRecords = objPreparedStatement.executeUpdate();		 
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


	public BillHeaderBO executeBillHeaderByBillSequence(Integer billSequence) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		BillHeaderBO billHeaderBO = new BillHeaderBO();
				try{
		
		//ProductTypeBO objProductTypeBO;
		
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_HEADER_BY_BILL_SEQUENCE + ORDER_BY_BILL_SEQUENCE);	
		objPreparedStatement.setInt(1,billSequence );
		resultSet = objPreparedStatement.executeQuery();
		while(resultSet.next()){
			billHeaderBO = BillHeaderHelper.getBillHeaderBOFromResultSet(resultSet);
			break;
		}
		
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
		 return billHeaderBO;			
	}
	
	/*
	public List<BillHeaderBO> executeAllBillHeaders() throws SQLException,Exception{
		//final String  METHOD_NAME = "executeProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<BillHeaderBO> billHeaderList=null;
		BillHeaderHelper billHeaderHelper = new BillHeaderHelper();

				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_BILL_HEADER + ORDER_BY_BILL_HEADER);	
	
		resultSet = objPreparedStatement.executeQuery();		
		billHeaderList =  billHeaderHelper.getBillHeaderBOListFromResultSet(resultSet);
		resultSet.close();
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
		return billHeaderList;			
	}
	*/
	
	//it is to populate the bill by applying filter condition on s.no,bill date,amount,status,redem date,customer id list
	public List<BillHeaderBO> executeAllBillHeaders(ParameterHelper parameters,int currentRecord,
			SearchMasterScreenForm searchMasterScreenForm, String billSerialNumber, String billDate, 
			String customerIDList,String amount,String redemedStatus,String billredemDate, 
			String productTypeNo) throws SQLException,Exception{
		final String  METHOD_NAME = "executeAllBillHeaders";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<BillHeaderBO> billHeaderList=null;
		BillHeaderHelper billHeaderHelper = new BillHeaderHelper(parameters);
				try{	
		 //objConnection = getConnection();		 
		 //System.out.println("executeAllBillHeaders : " + SELECT_BILL_SEQ_SERIAL_CUSTOMER_IN_AMOUNT_REDEMSTATUS_LIKE
				// + FilterClausesConstant.AND + " CUSTOMERID " + FilterClausesConstant.IN + FilterClausesConstant.LEFT_PARANTHESIS + customerIDList  + FilterClausesConstant.RIGHT_PARANTHESIS + ORDER_BY_BILL_HEADER);
					
		StringBuffer sqlQuery = new StringBuffer(SELECT_BILL_SEQ_SERIAL_CUSTOMER_IN_AMOUNT_REDEMSTATUS_LIKE);
		sqlQuery.append(FilterClausesConstant.AND + " CUSTOMERID " + FilterClausesConstant.IN);
		sqlQuery.append(FilterClausesConstant.LEFT_PARANTHESIS + customerIDList);
		sqlQuery.append(FilterClausesConstant.RIGHT_PARANTHESIS );
		sqlQuery.append(FilterClausesConstant.AND + ORDER_BY_BILL_SERIAL + FilterClausesConstant.IN );
		sqlQuery.append(FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials());
		sqlQuery.append(FilterClausesConstant.RIGHT_PARANTHESIS );
		
		//Filter Clause
		filterByProductTypeInSqlQuery(sqlQuery, productTypeNo);
		filterByexcludeAuctionBillsInSqlQuery(sqlQuery, redemedStatus);
		
		//Order By Clause
		setOrderByClauseinSqlQuery(sqlQuery, redemedStatus);
		
		//System.out.println("executeAllBillHeaders"+sqlQuery.toString());
		objPreparedStatement = objConnection.prepareStatement(sqlQuery.toString());
		
		 /*objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_BILL_HEADER + FilterClausesConstant.WHERE + 
			" BILL_NO " + FilterClausesConstant.LIKE +FilterClausesConstant.SINGLE_QUOTES + billSerialNumber + "%'" +FilterClausesConstant.AND +
			" BILL_DATE " + FilterClausesConstant.LIKE +FilterClausesConstant.SINGLE_QUOTES + billDate +  "%'" +FilterClausesConstant.AND +
			" CUSTOMERID " + FilterClausesConstant.IN + FilterClausesConstant.LEFT_PARANTHESIS + customerIDList + FilterClausesConstant.RIGHT_PARANTHESIS + FilterClausesConstant.AND +
			" AMOUNT " + FilterClausesConstant.LIKE +FilterClausesConstant.SINGLE_QUOTES + amount +  "%'" + FilterClausesConstant.AND + 
			" REDEMPTION_STATUS " + FilterClausesConstant.LIKE +FilterClausesConstant.SINGLE_QUOTES + redemedStatus + "%'") ;
		*/
		objPreparedStatement.setString(1, billSerialNumber+"%");
		objPreparedStatement.setString(2, billDate+"%");
		//objPreparedStatement.setString(3, customerIDList);
		objPreparedStatement.setString(3, amount+"%");
		objPreparedStatement.setString(4, redemedStatus.toUpperCase()+"%");
		objPreparedStatement.setString(5, billredemDate.trim()+"%");
//		objPreparedStatement.setString(6, FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials()
		///		 + FilterClausesConstant.RIGHT_PARANTHESIS);
		//System.out.println(FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials()
		///		 + FilterClausesConstant.RIGHT_PARANTHESIS);
		 resultSet = objPreparedStatement.executeQuery();		
		billHeaderList =  billHeaderHelper.getSearchBillHeaderBOListFromResultSet(parameters,currentRecord,
				 searchMasterScreenForm,resultSet);
		resultSet.close();
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
		return billHeaderList;			
	}
		
	//it is to populate the bill by applying filter condition on s.no,bill date,amount,status,redem date
	public List<BillHeaderBO> executeAllBillHeaders(ParameterHelper parameters,int currentRecord,
			SearchMasterScreenForm searchMasterScreenForm, String billSerialNumber, String billDate, 
			String amount, String redemedStatus,String billredemDate, String productTypeNo) 
			throws SQLException,Exception{
		final String  METHOD_NAME = "executeAllBillHeaders";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<BillHeaderBO> billHeaderList=null;
		BillHeaderHelper billHeaderHelper = new BillHeaderHelper(parameters);
		
				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("executeAllBillHeaders" + SELECT_BILL_SEQ_SERIAL_AMOUNT_REDEMSTATUS_REDEMDATE_LIKE);
		StringBuffer sqlQuery= new StringBuffer(SELECT_BILL_SEQ_SERIAL_AMOUNT_REDEMSTATUS_REDEMDATE_LIKE);
		sqlQuery.append(FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials());
		sqlQuery.append(FilterClausesConstant.RIGHT_PARANTHESIS);
		
		//Filters
		filterByProductTypeInSqlQuery(sqlQuery, productTypeNo);
		filterByexcludeAuctionBillsInSqlQuery(sqlQuery, redemedStatus);
		
		//Order By Clause
		setOrderByClauseinSqlQuery(sqlQuery, redemedStatus);		
			
		objPreparedStatement = objConnection.prepareStatement(sqlQuery.toString());		
		objPreparedStatement.setString(1, billSerialNumber.trim()+"%");
		objPreparedStatement.setString(2, billDate.trim()+"%");
		objPreparedStatement.setString(3, amount.trim()+"%");
		objPreparedStatement.setString(4, redemedStatus.toUpperCase().trim()+"%");
		objPreparedStatement.setString(5, billredemDate.trim()+"%");
		//System.out.println(METHOD_NAME+sqlQuery.toString());
		///System.out.println("System.out.println(:"+redemedStatus.toUpperCase());
		//objPreparedStatement.setString(6, );
		//System.out.println(FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials()
		//		 + FilterClausesConstant.RIGHT_PARANTHESIS);
		 resultSet = objPreparedStatement.executeQuery();		
		billHeaderList =  billHeaderHelper.getSearchBillHeaderBOListFromResultSet(parameters,currentRecord,
				 searchMasterScreenForm,resultSet);
		resultSet.close();
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
		return billHeaderList;
		
	}
	
	
	//it is to populate the bill by applying filter condition on s.no,bill date,amount,status,redem date
	public List<BillHeaderBO> executeBillHeadersBetweenDates(ParameterHelper parameters,int currentRecord,
			SearchMasterScreenForm searchMasterScreenForm, String customerListOfIDs) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<BillHeaderBO> billHeaderList=null;
		BillHeaderHelper billHeaderHelper = new BillHeaderHelper(parameters);
		String sqlQuery = null;
		StringBuffer sqlQueryBuffer = new StringBuffer(SELECT_BILL_HEADER_BY_BILL_DATE_BETWEEN);
		try{	
			sqlQueryBuffer.append( FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials());
			sqlQueryBuffer.append(  FilterClausesConstant.RIGHT_PARANTHESIS );
		if(customerListOfIDs != null){		
			sqlQueryBuffer.append(  FilterClausesConstant.AND + " CUSTOMERID " + FilterClausesConstant.IN );
			sqlQueryBuffer.append( FilterClausesConstant.LEFT_PARANTHESIS + customerListOfIDs );
			sqlQueryBuffer.append(  FilterClausesConstant.RIGHT_PARANTHESIS );							
		}
		
		if(searchMasterScreenForm.getSearchInput1().length()>0 
				&& searchMasterScreenForm.getSearchInput2().length()>0){
			sqlQueryBuffer.append(  FilterClausesConstant.AND );
			sqlQueryBuffer.append( FilterClausesConstant.STR_TO_DATE ); 
			if (searchMasterScreenForm.getSearchOptions().length() > 0 && 
					searchMasterScreenForm.getSearchOptions().equalsIgnoreCase("R")){
				sqlQueryBuffer.append( FilterClausesConstant.LEFT_PARANTHESIS + " REDEMPTION_DATE, "); 
			}else{
				sqlQueryBuffer.append( FilterClausesConstant.LEFT_PARANTHESIS + " BILL_DATE, "); 
			}			
			sqlQueryBuffer.append( FilterClausesConstant.DATE_FORMAT_DD_MM_YYYY ); 
			sqlQueryBuffer.append( FilterClausesConstant.RIGHT_PARANTHESIS + FilterClausesConstant.BETWEEN);
			sqlQueryBuffer.append( FilterClausesConstant.QUESTIONAIRE );
			sqlQueryBuffer.append( FilterClausesConstant.AND + FilterClausesConstant.QUESTIONAIRE  );			
		}
		if(searchMasterScreenForm.getSearchInput11() != null && 
				searchMasterScreenForm.getSearchInput11().length()>0 ){
			sqlQueryBuffer.append(FilterClausesConstant.AND + FILTER_BY_BILL_HEADER_PRODUCT_TYPE + searchMasterScreenForm.getSearchInput11());
		}
		setOrderByClauseinSqlQuery(sqlQueryBuffer, searchMasterScreenForm.getSearchOptions());
		System.out.println(sqlQueryBuffer.toString());
		sqlQuery = sqlQueryBuffer.toString();
		objPreparedStatement = objConnection.prepareStatement(sqlQuery);				
		objPreparedStatement.setString(1, searchMasterScreenForm.getSearchInput4().trim()+"%");
		objPreparedStatement.setString(2, searchMasterScreenForm.getSearchInput5().trim()+"%");
		objPreparedStatement.setString(3, searchMasterScreenForm.getSearchInput6().trim()+"%");
		//objPreparedStatement.setString(4, FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials()
		//		 + FilterClausesConstant.RIGHT_PARANTHESIS);
		//System.out.println(FilterClausesConstant.LEFT_PARANTHESIS + parameters.getSearchSerials()
		//		 + FilterClausesConstant.RIGHT_PARANTHESIS);
		if(searchMasterScreenForm.getSearchInput1().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput1())
				&& searchMasterScreenForm.getSearchInput2().length()>0 && DateUtil.isValidDate(searchMasterScreenForm.getSearchInput2())){
		objPreparedStatement.setString(4, DateUtil.convertDateFormats("dd/MM/yyyy", 
				searchMasterScreenForm.getSearchInput1(), "yyyy-MM-dd"));
		objPreparedStatement.setString(5, DateUtil.convertDateFormats("dd/MM/yyyy", 
				searchMasterScreenForm.getSearchInput2(), "yyyy-MM-dd"));				
		}
		resultSet = objPreparedStatement.executeQuery();		
		billHeaderList =  billHeaderHelper.getSearchBillHeaderBOListFromResultSet(parameters,currentRecord,
				 searchMasterScreenForm,resultSet);
		resultSet.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}	finally{
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
		return billHeaderList;
		
	}
	
	
	public BillHeaderBO executeBillHeaderByBillSerialNo(String serialNo, Integer billNumber) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		BillHeaderBO billHeaderBO=null;

				try{		
		
		//ProductTypeBO objProductTypeBO;
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_HEADER_BY_BILL_SERIAL_NO + ORDER_BY_BILL_SEQUENCE);	
		//System.out.println(serialNo + "," + billNumber);
		objPreparedStatement.setString(1, serialNo);
		objPreparedStatement.setInt(2, billNumber);
		 resultSet = objPreparedStatement.executeQuery();		
		while(resultSet.next()){
			billHeaderBO = BillHeaderHelper.getBillHeaderBOFromResultSet(resultSet);
			break;
		}	
		resultSet.close();
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
		return billHeaderBO;			
	}
	
	public boolean isBillExistsByBillSerialNo(String serialNo, Integer billNumber) throws SQLException,Exception{
		//final String  METHOD_NAME = "billHeaderExistsByBillSerialNo";
		//Connection objConnection = getConnection(this.jndiName);
		
		boolean billHeaderExists = false;

		try{
			BillHeaderBO billHeaderBO = executeBillHeaderByBillSerialNo(serialNo, billNumber);	
			if (billHeaderBO!= null && billHeaderBO.getBillSequence().intValue() > 0 ){
				billHeaderExists = true;
			}
		}finally{
		}
		return billHeaderExists;			
	}
	
	
	private Integer getMaxBillSequenceNumber(){
		final String  METHOD_NAME = "getMaxBillSequence";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		int maxBillSequenceNumber = 0;
				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_HEADER_MAX_SEQUENCE);				
		resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			maxBillSequenceNumber = resultSet.getInt(1);			
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
		 //System.out.println("Next Sequence for Bill is : " + maxBillSequenceNumber);
		 return (maxBillSequenceNumber+1);			
	}
	
	public Integer getNextBillNumberForSerial(String billSerial){
		final String  METHOD_NAME = "getNextBillNumberForSerial";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
	
		int nextBillNumberForSerial = 0;

				try{		
		 //objConnection = getConnection();		 		 
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_HEADER_NEXT_BILL_SERIAL);
		objPreparedStatement.setString(1, billSerial);
		//System.out.println("getNextBillNumberForSerial : " + SELECT_BILL_HEADER_NEXT_BILL_SERIAL + billSerial );
		resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			nextBillNumberForSerial = resultSet.getInt(1);			
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
		 //System.out.println("Next serial no for Bill serial is : " + nextBillNumberForSerial);
		 return (nextBillNumberForSerial);			
	}
	
	public Integer getNextRedemBillNumberForSerial(String billSerial){
		final String  METHOD_NAME = "getNextRedemBillNumberForSerial";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		int nextRedemBillNumberForSerial = 0;

		try{	
		// objConnection = getConnection();		 		 
			objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_HEADER_NEXT_BILL_REDEM_SERIAL);
			objPreparedStatement.setString(1, billSerial);
		//System.out.println("getNextBillNumberForSerial : " + SELECT_BILL_HEADER_NEXT_BILL_SERIAL + billSerial );
			resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			nextRedemBillNumberForSerial = resultSet.getInt(1);			
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
		 //System.out.println("Next serial no for Bill serial is : " + nextBillNumberForSerial);
		 return (nextRedemBillNumberForSerial);			
	}
	
//	private populateProductTypeBO(){
		
	//}
	/*
	private void setProductTypeListFromResultSet(ResultSet resultSet, List<ProductTypeBO> productTypeList) throws SQLException{
		ProductTypeBO productTypeBO;
		while (resultSet.next()){
			productTypeBO = getProductTypeBOFromResultSet(resultSet);
			productTypeList.add(productTypeBO);
		}
	}*/
	

	public List<String> executeDistinctBillSerial() throws SQLException,Exception{
		//final String  METHOD_NAME = "executeDistinctBillSerial";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<String> billSerialList=new ArrayList<String>();
				try{		
		// objConnection = getConnection();		 
		 //System.out.println("SELECT_DISTINCT_BILL_SERIAL: " + SELECT_DISTINCT_BILL_SERIAL);
		objPreparedStatement = objConnection.prepareStatement(SELECT_DISTINCT_BILL_SERIAL);	
		resultSet = objPreparedStatement.executeQuery();		
		while(resultSet.next()){
			billSerialList.add(resultSet.getString(1));
		}
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
		return billSerialList;			
	}
	
	public List<String> executeDistinctBillSerialNo() throws SQLException,Exception{
		//final String  METHOD_NAME = "executeDistinctBillSerialNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement;
		ResultSet resultSet;
		
		List<String> billSerialList=new ArrayList<String>();
				try{		
		// objConnection = getConnection();		 
		 //System.out.println("SELECT_DISTINCT_BILL_SERIAL_NO: " + SELECT_DISTINCT_BILL_SERIAL_NO);
		objPreparedStatement = objConnection.prepareStatement(SELECT_DISTINCT_BILL_SERIAL_NO);	
		resultSet = objPreparedStatement.executeQuery();		
		while(resultSet.next()){
			billSerialList.add(resultSet.getString(1));
		}
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
		return billSerialList;			
	}
	
	/*
	 * Eliminate auction bills
	 */
	private void filterByexcludeAuctionBillsInSqlQuery(StringBuffer sqlQuery, String redemedStatus){
		final String  methodName = "filterByexcludeAuctionBillsInSqlQuery";
		LogMethods.printMethodStarts(className, methodName);
		if(redemedStatus == null || redemedStatus.toUpperCase().length() == 0){
			sqlQuery.append(EXCLUDE_AUCTION_BILLS);
		}
		LogMethods.printMethodEnds(className, methodName);
	}
	
	/*
	 * Pass selected Product Type
	 */
	private void filterByProductTypeInSqlQuery(StringBuffer sqlQuery, String productTypeNo){
		final String  methodName = "filterByProductTypeInSqlQuery";
		LogMethods.printMethodStarts(className, methodName);		
		if(productTypeNo != null && productTypeNo.length() > 0){
			sqlQuery.append(FilterClausesConstant.AND + FILTER_BY_BILL_HEADER_PRODUCT_TYPE + productTypeNo);
		}	
		LogMethods.printMethodEnds(className, methodName);
	}
	
	/*
	 * If any redemption is selected then order by will be based on redemption
	 */
	private void setOrderByClauseinSqlQuery(StringBuffer sqlQuery, String redemedStatus){
		final String  methodName = "setOrderByClauseinSqlQuery";
		LogMethods.printMethodStarts(className, methodName);
		if(redemedStatus != null && redemedStatus.equalsIgnoreCase(BankConstant.REDEM_CODE)){
			sqlQuery.append(ORDER_BY_BILL_HEADER_REDEM_NO);
		}else{
			sqlQuery.append(ORDER_BY_BILL_SEQUENCE);
		}
		LogMethods.printMethodEnds(className, methodName);
	}
	
}
