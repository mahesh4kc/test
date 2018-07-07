package com.bank.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySqlDataSource{
       
        //public static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";

        Connection connection = null;
              
        public MySqlDataSource() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        	//addConnectionProperty("useUnicode", "true");
            //addConnectionProperty("characterEncoding", "UTF-8");
        	 this.connection = null;   
        }
       
        public void closeConnection() throws SQLException{
                connection.close();
        }
        public Connection getOracleConnection(){                
                                return this.connection;
        }
       
        public Connection getJNDIConnection(String jndiName) throws NamingException, SQLException{
                this.connection = null;
                Properties jndiProps = new Properties();
                jndiProps.put("useUnicode", "true");
                jndiProps.put("characterEncoding", "UTF-8");
                Context initContext = new InitialContext(jndiProps);
                Context envContext  = (Context)initContext.lookup("java:/comp/env");
                envContext.addToEnvironment("useUnicode", "true");
                envContext.addToEnvironment("characterEncoding", "UTF-8");
                DataSource ds = (DataSource)envContext.lookup("jdbc/"+jndiName);
                /*MysqlDataSource  dataSource = new MysqlDataSource();
                dataSource.set
                BasicDataSource basicDataSource = new ;
                basicDataSource.s
                */this.connection = ds.getConnection();
                return this.connection;
        }
       
} 
