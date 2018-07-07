package com.bank.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class UTF8MySqlDataStorage {


	public static void main(String args[]) throws ClassNotFoundException, SQLException, NamingException{
		//Properties pr = new Properties();
	   // pr.put("user", "root");
	  //  pr.put("password", "root");
	 //   pr.put("characterEncoding", "UTF-8");
	 //   pr.put("useUnicode", "true");
	   Class.forName("com.mysql.jdbc.Driver");
	    String connectionURL = "jdbc:mysql://localhost:3306/thulasiram?useUnicode=true&characterEncoding=utf8";
	    Connection connection = DriverManager.getConnection(connectionURL,"root","root");
	    System.out.println("Connection Open:"+!connection.isClosed());
	    
		/*String jndiName = "THULASIRAM";
		Properties jndiProps = new Properties();
        jndiProps.put("useUnicode", "true");
        jndiProps.put("characterEncoding", "UTF8");
        Context initContext = new InitialContext(jndiProps);
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        //envContext.addToEnvironment("useUnicode", "true");
        //envContext.addToEnvironment("characterEncoding", "UTF-8");
        DataSource ds = (DataSource)envContext.lookup("jdbc/"+jndiName);
        Connection connection = ds.getConnection();*/
	    /*PreparedStatement preparedStatement = connection.prepareStatement(" UPDATE PARAMETERS SET PARAM_EXAMPLE=? WHERE PARAM_SEQ = 38");
	    preparedStatement.setString(1, "ணோ");
	    preparedStatement.executeUpdate();*/
	    //preparedStatement.close();
	    
	    PreparedStatement  preparedStatement = connection.prepareStatement(" SELECT PARAM_EXAMPLE FROM PARAMETERS WHERE PARAM_SEQ = 38");
	    ResultSet  resultSet = preparedStatement.executeQuery();
	    while(resultSet.next()){
	    	System.out.println(resultSet.getString("PARAM_EXAMPLE"));
	    }
	    
	    
	}
}
