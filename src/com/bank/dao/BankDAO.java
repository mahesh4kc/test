package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.connection.MySQLConnection;



public abstract class BankDAO extends MySQLConnection{

	final String  CLASS_NAME = "BankDAO";
	
	//protected Connection objConnection;
	
	
	//protected PreparedStatement objPreparedStatement;
	
	//protected  ResultSet objCustomerResultSet;

	public BankDAO(){
		super();			 
	}
	
	public boolean isConnectionExists(String jndiName){
		final String  METHOD_NAME = "isConnectionExists";
		boolean isConnectionExists = false;
		Connection objConnection=null;
		try{
			objConnection = getConnection(jndiName);
		if(!objConnection.isClosed()){
			isConnectionExists = true;
		}
		}
		 catch(SQLException sqlex){
			 
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage()+jndiName);
			 sqlex.printStackTrace();
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
			objConnection = null;
		}
			return isConnectionExists;
		}
}
