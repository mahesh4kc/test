package com.bank.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import com.bank.form.BackUpandRestoreMySqlForm;
import com.bank.helper.ParameterHelper;
import com.bank.util.mysql.MySqlBackupScript;

public class BackUpandRestoreMySqlAction extends LookupDispatchAction{
	
	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        //map.put("button.upload", "upload");	 
	        map.put("button.download", "download");
	        map.put("button.clear", "clear");
	        return map;
	    }

	/* public ActionForward upload(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("upload method fired");
		BackUpandRestoreMySqlForm backUpandRestoreMySqlForm = (BackUpandRestoreMySqlForm) form;
		//FileOutputStream outputStream = null;
		
		return mapping.findForward("success");
	 }
	 */
	 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("clear method fired");
		BackUpandRestoreMySqlForm backUpandRestoreMySqlForm = (BackUpandRestoreMySqlForm) form;
		return mapping.findForward("success");
	 }
	 
	 public ActionForward download(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			 HttpServletResponse response) throws SQLException,Exception{
		System.out.println("download method fired");
		HttpSession session = request.getSession();
		String databaseName = (String)session.getAttribute("databaseName");
		String jndiName = (String) session.getAttribute("databaseName");
		BackUpandRestoreMySqlForm backUpandRestoreMySqlForm = (BackUpandRestoreMySqlForm) form;		
		ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);		
		//Map<String, String> parameterMap = parameterHelper.getParameterMap();
		//response.setContentType("application/octet-stream");
        //response.setHeader("Content-disposition", "attachment;filename="+databaseName+".sql");
		MySqlBackupScript mySqlBackupScript = new MySqlBackupScript();
		mySqlBackupScript.downloadMySqlBackUp(response,databaseName,parameterHelper);        
		return mapping.findForward("success");
	 }
	 
	 
	 
	}
