package com.bank.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PropertyUtil {
	
	private static Properties properties; 
	/*public static Properties loadProperties() throws IOException{
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("ApplicationResources.properties");
		properties.load(stream);
		return properties;
	}*/
	
	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		PropertyUtil.properties = properties;
	}

	public static Properties loadPropertiesOutsideWar(){
		System.out.println("loadPropertiesOutsideWar");
		String folderName = null;
		properties = new Properties();
		try {
		   InitialContext context = new InitialContext();
		   folderName = (String) context.lookup("java:comp/env/config");
		} catch (NamingException ex) {
		   System.out.println("exception in jndi lookup");
		}
		if(folderName != null) {
		   File configFile = new File(folderName + "jewelbankers.properties");
		   try {
		       InputStream is = new FileInputStream(configFile);
		       properties.load(is);
		   } catch(IOException ex) {
		      System.out.println("exception loading properties file");
		      ex.printStackTrace();
		   }
		}
		return properties;
	}
	
	
}
