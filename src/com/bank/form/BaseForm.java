package com.bank.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.UserAuthenticationBO;

public class BaseForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userLoggedIn;
	private String shopDetails;
	private String message;
		
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserLoggedIn() {		
		return this.userLoggedIn;
	}
	public void setUserLoggedIn(String userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
	public String setUserLoggedIn(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		UserAuthenticationBO userAuthenticationBO = null;
		userAuthenticationBO = (UserAuthenticationBO)session.getAttribute("loggedInUser");
		this.shopDetails = (String)session.getAttribute("shopDetails");;
		if(userAuthenticationBO != null){
			this.userLoggedIn = userAuthenticationBO.getUserName();
		}
		return this.userLoggedIn;
	}
	
	public void isValidSession(ActionMapping mapping, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUser") == null){
			  System.out.println("isValidSession1");
			  request.setAttribute("error.invalidSession", "error.invalidSession");
			 // errors.add("error.invalidSession", new ActionError("error.invalidSession"));
			 // mapping.findForward("inValidSession");
		  }	
	}
	public String getShopDetails() {
		return this.shopDetails;
	}
		
}
