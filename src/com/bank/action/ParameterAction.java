package com.bank.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.dao.ParametersDAO;
import com.bank.form.ParameterForm;
import com.bank.helper.ParameterHelper;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.BankConstant;

public class ParameterAction extends SuccessMessage{
	
	 protected Map<String, String> getKeyMethodMap() {
		 System.out.println("ParameterAction : getKeyMethodMap fired");
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("button.search", "search");
	        map.put("button.save", "save");
	        map.put("button.clear", "clear");	        
	        return map;
	    }

		 
		//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
			System.out.println("Clear method fired");
			ParameterForm parameterForm = (ParameterForm )form;
			parameterForm.setParamID("");
			parameterForm.setParameterList(null);			
			return mapping.findForward("success");
		}
	
		 //* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException{
		System.out.println("Save method fired starts");
		HttpSession session = request.getSession(); 
		 String jndiName = (String) session.getAttribute("databaseName");
		ParametersDAO parametersDAO = new ParametersDAO(jndiName);
		 ParameterForm parameterForm = (ParameterForm )form;	
		
		 parametersDAO.createUpdateDelete(parameterForm.getParameterList());
		 session.setAttribute("parameterMap",null);								//Reset the parameter after save
		 
		 //refresh the parameter in session to reflect synchonizly
		 if(session != null && session.getAttribute("parameterMap")!=null){
			 ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);	
			 parameterHelper.getParameterMapFromSession(request);
			}
		 search(mapping, form, request, response);
		
		 setSuccessMessage("success.parametersSave", request);
		 System.out.println("Save method fired ends");
		return mapping.findForward("success");
	}

	
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 public ActionForward search(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Search method fired");
		HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		ParameterForm parameterForm;
		 try{	 
			 ParametersDAO parametersDAO = new ParametersDAO(jndiName);
			 parameterForm = (ParameterForm )form;			
			 
			 //execute if only description is entered
			 if (parameterForm.getParamID() !=null && parameterForm.getParamID().length() > 0){
				 parameterForm.setParameterList(
						 parametersDAO.executeParameterIDLike(
								 parameterForm.getParamID()));
			 }else{
				 parameterForm.setParameterList(parametersDAO.executeAllParameters());
			 }			 
			
			 //System.out.println("size"+parameterForm.getParameterList().size());
			 
			
			 //saveErrors(request, errors);
			 }catch(Exception ex){
			 }  
		return mapping.findForward("success");
	}

	
}
