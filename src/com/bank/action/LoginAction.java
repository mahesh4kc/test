package com.bank.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.UserAuthenticationBO;
import com.bank.dao.UserAuthenticationDAO;
import com.bank.form.LoginForm;
import com.bank.helper.ParameterHelper;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.BankConstant;
import com.bank.util.LogMethods;
import com.bank.util.PropertyUtil;
import com.bank.util.SendMail;

public class LoginAction extends SuccessMessage{
//	public class CustomerAction extends Action{
	
	//static Logger logger = Logger.getLogger(LoginAction.class.getName());
	
	final String CLASS_NAME = LoginAction.class.getName(); 
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();	       
	        map.put("button.login", "login");
	        map.put("button.clear", "clear");
	        map.put("button.apply", "apply");
	        map.put("button.create", "create");
	        map.put("button.register", "register");
	        map.put("button.forgot", "forgot");	 	        
	        map.put("logout", "logout");
	        map.put("home", "home");
	        System.out.println("map fired");
	        return map;
	    } 
	
	public ActionForward login(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException{
		String methodName="login";
		
		//logger.info("login starts");
		LogMethods.printMethodStarts(CLASS_NAME, methodName);
		ActionForward actionForward = null;
		HttpSession session = request.getSession();
		session.setAttribute("databaseName",BankConstant.DEFAULT_DATABASE_NAME );
		if(request.getParameter("action") !=null && ((String)request.getParameter("action")).equalsIgnoreCase("logout")){
			actionForward = logout(mapping, form, request, response);
		}else{
			ActionErrors errors=null;			
			UserAuthenticationBO userAuthenticationBO=null;			
			LoginForm loginForm = (LoginForm)form;	
			
			UserAuthenticationDAO  userAuthenticationDAO = 
			new UserAuthenticationDAO(BankConstant.DEFAULT_DATABASE_NAME);
					//(UserAuthenticationDAO) getWebApplicationContext().getBean("customerBo");
			
			//Get User Authentication Business Object
			if (loginForm != null)
			{			
				errors = loginForm.validateChangePassword(mapping, request);
				saveErrors(request, errors);
				if(errors != null && errors.size() < 1){
					
						userAuthenticationBO = userAuthenticationDAO.executeUserAuthenticationByLoginIdPassword(
								loginForm.getLoginID(), loginForm.getPassword());
						//load the customer business object to customer form
				
						session.setAttribute("loggedInUser", userAuthenticationBO);				
				}
			}		
			//load all the parameters
			//MySQLConnection.databaseName =userAuthenticationBO.getDatabaseName();
			String loggedInJndiName = userAuthenticationBO.getDatabaseName();
			session.setAttribute("databaseName", loggedInJndiName);
			//String jndiName = (String) session.getAttribute("databaseName");
			userAuthenticationDAO.getConnection(loggedInJndiName);			
			ParameterHelper parameterHelper = new ParameterHelper(loggedInJndiName,request);	
			 parameterHelper.getParameterMapFromSession(request);
			 session.setAttribute("shopDetails", parameterHelper.getShopDetails());	
			actionForward = mapping.findForward("success");
			
		}
		LogMethods.printMethodEnds(CLASS_NAME, methodName);
		return actionForward;

	}

	public ActionForward apply(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException{
		//String navigationPage="";
		System.out.println("changePassword starts");
		HttpSession session = request.getSession();
		ActionForward actionForward = null;
		ActionErrors errors=null;		
		//String tempSqlDatabaseInstance=MySQLConnection.databaseName;
		String loggedInJndiName = (String) session.getAttribute("databaseName");
			LoginForm loginForm = (LoginForm)form;		
			loginForm.setLoginID(loggedInJndiName);
			UserAuthenticationDAO  userAuthenticationDAO = new UserAuthenticationDAO(BankConstant.DEFAULT_DATABASE_NAME);
			
			
			//MySQLConnection.databaseName=null;
			//userAuthenticationDAO.getConnection(tempSqlDatabaseInstance);
			//Get User Authentication Business Object
			if (loginForm != null)
			{			
				errors = loginForm.validateChangePassword(mapping, request);
				saveErrors(request, errors);
				actionForward = mapping.findForward("same");
				
			}	
			
			if(errors != null && errors.size() < 1){					
				userAuthenticationDAO.updatePassword(loginForm,false);
					actionForward = mapping.findForward("success");
					session.setAttribute("databaseName",loggedInJndiName );
					
					//MySQLConnection.databaseName =tempSqlDatabaseInstance;
					userAuthenticationDAO.getConnection(loggedInJndiName);
					clear(mapping, loginForm, request, response);
			}
			
		System.out.println("changePassword ends");
		return actionForward;

	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException, AddressException, MessagingException{
		//String navigationPage="";
		System.out.println("register starts");
		//HttpSession session = request.getSession();
		ActionForward actionForward = null;
		ActionErrors errors=null;		
		//String tempSqlDatabaseInstance=MySQLConnection.databaseName;
		//String tempSqlDatabaseInstance = (String) session.getAttribute("databaseName");
			LoginForm loginForm = (LoginForm)form;			
			UserAuthenticationDAO  userAuthenticationDAO = new UserAuthenticationDAO(BankConstant.DEFAULT_DATABASE_NAME);
			
			
			//MySQLConnection.databaseName=null;
			//userAuthenticationDAO.getConnection(tempSqlDatabaseInstance);
			//Get User Authentication Business Object
			if (loginForm != null)
			{			
				errors = loginForm.validateRegistration(mapping, request);
				saveErrors(request, errors);
				actionForward = mapping.findForward("same");
				
			}
			if(errors == null || errors.size() < 1){					
				userAuthenticationDAO.createRegistration(loginForm);
				
				//Send mail after successful registration
				SendMail sendMail = new SendMail();
				String[] toAddress = {loginForm.getEmailID(),BankConstant.SUPPORT_MAIL_ID}; // added this line
				String subject = sendMail.getSubject();
				String body = sendMail.getBody(loginForm);				
				sendMail.sendGmail(toAddress, subject, body);
				
				clear(mapping, loginForm, request, response);
				 setSuccessMessage("success.registrationSuccess", request);
				actionForward = mapping.findForward("success");
				/*session.setAttribute("databaseName",tempSqlDatabaseInstance );
				//MySQLConnection.databaseName =tempSqlDatabaseInstance;
				if(tempSqlDatabaseInstance == null){
					tempSqlDatabaseInstance = BankConstant.DEFAULT_DATABASE_NAME;
				}
				userAuthenticationDAO.getConnection(tempSqlDatabaseInstance);*/		
				
		}	
			
			 
		System.out.println("register ends");
		return actionForward;

	}
	
	public ActionForward forgot(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException, AddressException, MessagingException{
		System.out.println("forgotPassword starts");
		//HttpSession session = request.getSession();
		ActionForward actionForward = null;
		ActionErrors errors=null;		
		//String tempSqlDatabaseInstance=MySQLConnection.databaseName;
		//String tempSqlDatabaseInstance = (String) session.getAttribute("databaseName");
			LoginForm loginForm = (LoginForm)form;			
			UserAuthenticationDAO  userAuthenticationDAO = new UserAuthenticationDAO(BankConstant.DEFAULT_DATABASE_NAME);
			
			
			//MySQLConnection.databaseName=null;
			//userAuthenticationDAO.getConnection(tempSqlDatabaseInstance);
			//Get User Authentication Business Object
			if (loginForm != null)
			{			
				errors = loginForm.validateForgotPassword(mapping, request);
				saveErrors(request, errors);
				actionForward = mapping.findForward("same");
				
			}
			if(errors == null || errors.size() < 1){					
				userAuthenticationDAO.updatePassword(loginForm, true);
				UserAuthenticationBO userAuthenticationBO = userAuthenticationDAO.executeUserAuthenticationByLoginId(loginForm.getLoginID());
				//Send mail after successful registration
				SendMail sendMail = new SendMail();
				String[] toAddress = {userAuthenticationBO.getEmailID(),BankConstant.SUPPORT_MAIL_ID}; // added this line
				String subject = sendMail.getSubjectForAccountPasswordReset();
				String body = sendMail.getBodyForResetPassword(userAuthenticationBO);				
				sendMail.sendGmail(toAddress, subject, body);
				
				clear(mapping, loginForm, request, response);
				 setSuccessMessage("success.forgotPasswordSuccess", request);
				actionForward = mapping.findForward("success");
				/*session.setAttribute("databaseName",tempSqlDatabaseInstance );
				//MySQLConnection.databaseName =tempSqlDatabaseInstance;
				if(tempSqlDatabaseInstance == null){
					tempSqlDatabaseInstance = BankConstant.DEFAULT_DATABASE_NAME;
				}
				userAuthenticationDAO.getConnection(tempSqlDatabaseInstance);*/		
				
		}	
			
			 
		System.out.println("forgotPassword ends");
		return actionForward;

	}
	
	
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws SQLException{
		
		//ActionErrors errors=null;	
		System.out.println("logout");
		LoginForm loginForm = (LoginForm)form;	
		clearLoginPage(loginForm);
		HttpSession session = request.getSession();
		session.removeAttribute("loggedInUser");
		session.invalidate();
		return mapping.findForward("same");
	}
	 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			LoginForm loginForm = (LoginForm)form;				
			//Clear the Customer Details
			clearLoginPage(loginForm);
			return mapping.findForward("same");
	}
	 
	
	 
		//Clear the login  Details
		 private void clearLoginPage(LoginForm loginForm){
			 loginForm.setLoginID("");
			 loginForm.setPassword("");
			 loginForm.setOldPassword("");
			 loginForm.setDuplicatePassword("");
			 loginForm.setEmailID("");
			 loginForm.setUserName("");
			 loginForm.setLastName("");
			 loginForm.setShopName("");
			 loginForm.setPhoneNumber("");
			 loginForm.setEmailID("");
			 loginForm.setLocation("");
		 }
		 
	
		 public ActionForward home(ActionMapping mapping, ActionForm form,HttpServletRequest request,
					HttpServletResponse response) throws SQLException{
				return mapping.findForward("success");
			}	 

		 
}
