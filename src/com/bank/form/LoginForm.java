package com.bank.form;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.bank.business.validation.UserAuthenticationBusinessValidation;
import com.bank.dao.UserAuthenticationDAO;
import com.bank.util.BankConstant;

public class LoginForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	private String loginID;
	private String password;
	private String oldPassword;
	private String emailID;
	private String duplicatePassword;
	private String userName;
	private String lastName;
	private String phoneNumber;
	//private String databaseName;
	private String location;
	private String shopName;
		
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/*public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}*/
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDuplicatePassword() {
		return this.duplicatePassword;
	}
	public void setDuplicatePassword(String duplicatePassword) {
		this.duplicatePassword = duplicatePassword;
	}
	public String getOldPassword() {
		return this.oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getLoginID() {
		return this.loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailID() {
		return this.emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();	  
	  try{
		  if(action !=null && action.equals("LOGIN")){
			  //UserAuthenticationDAO.databaseName = BankConstant.DEFAULT_DATABASE_NAME;//"MYSQL";
			  UserAuthenticationDAO userAuthenticationDAO = new UserAuthenticationDAO(BankConstant.DEFAULT_DATABASE_NAME);
			  //userAuthenticationDAO.getConnection(BankConstant.DEFAULT_DATABASE_NAME);
			    UserAuthenticationBusinessValidation userAuthenticationBusinessValidation = 
			    	new UserAuthenticationBusinessValidation(userAuthenticationDAO);
			  userAuthenticationBusinessValidation.validateUserAuthenticationDetailsForLogin(
					  errors, this,BankConstant.DEFAULT_DATABASE_NAME);	
		  }
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
	

	public ActionErrors validateChangePassword(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();
	    //HttpSession  session = request.getSession();
	    String jndiName = BankConstant.DEFAULT_DATABASE_NAME;
	    UserAuthenticationDAO userAuthenticationDAO = new UserAuthenticationDAO(jndiName);
		 // userAuthenticationDAO.getConnection(jndiName);
	    UserAuthenticationBusinessValidation userAuthenticationBusinessValidation = 
	    	new UserAuthenticationBusinessValidation(userAuthenticationDAO);
	  try{
		  if(action !=null && action.equals("APPLY")){
			  userAuthenticationBusinessValidation.
			  validateUserAuthenticationDetailsForChangePassword(errors, this,BankConstant.DEFAULT_DATABASE_NAME);
		  }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }
	    return errors;
	  }
	
	public ActionErrors validateRegistration(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();
	    //HttpSession  session = request.getSession();
	    String jndiName = BankConstant.DEFAULT_DATABASE_NAME;
	    UserAuthenticationDAO userAuthenticationDAO = new UserAuthenticationDAO(jndiName);
		 // userAuthenticationDAO.getConnection(jndiName);
	    UserAuthenticationBusinessValidation userAuthenticationBusinessValidation = 
	    	new UserAuthenticationBusinessValidation(userAuthenticationDAO);
	  try{
		  if(action !=null && action.equals("REGISTER")){
			  userAuthenticationBusinessValidation.
			  validateUserRegistrationDetails(errors, this,BankConstant.DEFAULT_DATABASE_NAME);
		  }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }
	    return errors;
	  }
	
	public ActionErrors validateForgotPassword(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();
	    //HttpSession  session = request.getSession();
	    String jndiName = BankConstant.DEFAULT_DATABASE_NAME;
	    UserAuthenticationDAO userAuthenticationDAO = new UserAuthenticationDAO(jndiName);
		 // userAuthenticationDAO.getConnection(jndiName);
	  
	  try{
		  if(action !=null && action.equals("FORGOTPASSWORD")){
			  UserAuthenticationBusinessValidation userAuthenticationBusinessValidation = 
				    	new UserAuthenticationBusinessValidation(userAuthenticationDAO);
			  userAuthenticationBusinessValidation.
			  validateForgotPasswordDetails(errors, this,BankConstant.DEFAULT_DATABASE_NAME);
		  }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }
	    return errors;
	  }
	
	
	
}
