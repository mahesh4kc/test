package com.bank.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bank.business.validation.FileUploadValidation;
 
public class FileUploadForm extends ActionForm{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5676683839837058185L;
	private FormFile file;
 
	public FormFile getFile() {
		return file;
	}
 
	public void setFile(FormFile file) {
		this.file = file;
	}
 
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
		
	    ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    String jndiName = (String) session.getAttribute("databaseName");
	    
	    if(action.equals("SAVE")){
	    	FileUploadValidation fileUploadValidation  = new FileUploadValidation();
	    	fileUploadValidation.validateFileUploadDetailsForSave(errors, this);
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
}