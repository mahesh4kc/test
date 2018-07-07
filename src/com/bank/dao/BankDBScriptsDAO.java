package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BankDBScriptsDAO extends BankDAO{
	public static final String CLASS_NAME = "BankDBScriptsDAO";
		
	public static final String CREATE_CUSTOMER_DDL = "CREATE TABLE CUSTOMER (" +
														"CUSTOMERID BIGINT PRIMARY KEY," +
														"CUSTOMERNAME VARCHAR(50),"+
														"ADDRESS VARCHAR(50),"+
														"STREET VARCHAR(50),"+
														"AREA VARCHAR(50) DEFAULT 'KELLAMBAKKAM',"+
														"DISTRICT VARCHAR(50) DEFAULT 'KANCHEEPURAM',"+
														"STATE VARCHAR(50) DEFAULT 'TAMIL NADU',"+
														"COUNTRY VARCHAR(50) DEFAULT 'INDIA',"+
														"PINCODE INT(6) DEFAULT 603103,"+
														"PHOTO VARCHAR(50),"+
														"PHONENO BIGINT(15),"+
														"MOBILENO BIGINT(15),"+
														"MAILID VARCHAR(50)" +
														")";

	//Connection objConnection;
	
	
	PreparedStatement objPreparedStatement;
	
	ResultSet objCustomerResultSet;
	
/*
 * 
 */
	
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
	
	public BankDBScriptsDAO(String jndiName) {
			super();
			//objConnection = getConnection(jndiName);
			this.jndiName = jndiName;
		}
	
	
	
	public void createBankDBScripts() throws SQLException{
		final String  METHOD_NAME = "createBankDBScripts";
		int noOfInsertedRecords =0;

		Connection objConnection = getConnection(this.jndiName);
				try{		
		 //objConnection = getConnection();		 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());		 
		 objPreparedStatement = objConnection.prepareStatement(CREATE_CUSTOMER_DDL);		
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 System.out.println(noOfInsertedRecords + " Record inserted");	

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
	
	
}
