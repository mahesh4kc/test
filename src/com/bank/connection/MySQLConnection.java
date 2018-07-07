package com.bank.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.bank.util.LogMethods;

abstract public class MySQLConnection{
	//private Connection connection;
	
	final String CLASS_NAME = MySQLConnection.class.getName();
	
	//public static String databaseName = BankConstant.DEFAULT_DATABASE_NAME;

	public MySQLConnection(){
		 super();
		 //this.connection=getConnection();
	 }

	/*
	 * 
	 
	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public Connection getConnection(String jndiName){
		MySqlDataSource mySqlDataSource;
		String methodName = "getConnection"+jndiName;
		LogMethods.printMethodStarts(CLASS_NAME, methodName);
		//System.out.println("getConnection:jndiName"+jndiName);
		Connection connection=null;
		try {
			mySqlDataSource = new MySqlDataSource();			
		 connection = mySqlDataSource.getJNDIConnection(jndiName);
		 
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//mySqlDataSource = null;
		}
		LogMethods.printMethodEnds(CLASS_NAME, methodName);
		return connection;		
	}
	/*
	 * 
	
	public Connection getConnection(int a){
		if(MySQLConnection.databaseName ==null || MySQLConnection.databaseName.length() == 0){
			MySQLConnection.databaseName = BankConstant.DEFAULT_DATABASE_NAME;
		}else{
			MySQLConnection.databaseName = MySQLConnection.databaseName;
		}
		System.out.println("MySQLConnection.databaseName:"+MySQLConnection.databaseName);
	try{	 
		
	     //Register the JDBC driver for MySQL.
	     Class.forName(BankConstant.MYSQL_DRIVER);
	     //Class.forName("com.mysql.jdbc.Driver");
	     //Define URL of database server for
	     // database named mysql on the localhost
	     // with the default port number 3306.
	     String url = BankConstant.MYSQL_URL + BankConstant.FORWARD_SLASH + BankConstant.FORWARD_SLASH +
					     BankConstant.DATABASE_IPADDRESS + BankConstant.COLON + BankConstant.MYSQL_PORT + 
					     BankConstant.FORWARD_SLASH +  MySQLConnection.databaseName;
	     //String url = "jdbc:mysql://localhost:3306/thulasiramkag";
	
	     //Get a connection to the database for a
	     // user named root with a blank password.
	     // This user is the default administrator
	     // having full privileges to do anything.
	     //this.connection = DriverManager.getConnection(url,BankConstant.USER_ID, BankConstant.PASSWORD);
	     this.connection = DriverManager.getConnection(url,"root", "root");
     }
	catch(SQLException sqlex){
		System.out.println(sqlex.getMessage());
		 sqlex.printStackTrace();
	}
	catch(ClassNotFoundException sqlex){
		System.out.println("MYSQL jar is missing");
	}
	return this.connection;		
	} */
}
