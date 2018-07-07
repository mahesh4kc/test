package com.bank.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bank.dao.BankDBScriptRunnerDAO;
import com.bank.form.FileUploadForm;
import com.bank.successmessage.SuccessMessage;
import com.bank.util.FileUtil;
 
public class FileUploadAction extends SuccessMessage{

	 protected Map<String, String> getKeyMethodMap() {
	        Map<String, String> map = new HashMap<String, String>();
	        
	        map.put("button.save", "save");
	        map.put("button.clear", "clear");
	        
	        return map;
	    }
	 
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
 
	    FileUploadForm fileUploadForm = (FileUploadForm)form;  
	    
	    
	    System.out.println("save method fired");
		 ActionErrors errors=null;
		 HttpSession session = request.getSession();
		 String jndiName = (String) session.getAttribute("databaseName");
		 BankDBScriptRunnerDAO bankDBScriptRunnerDAO = new BankDBScriptRunnerDAO(jndiName);
		 
		 //ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
		 

		 if (fileUploadForm != null)
		{
			errors = fileUploadForm.validate(mapping, request);
			saveErrors(request, errors);
						
		} 
		 if (errors!= null && errors.size() < 1){
			 FileUtil fileUtil = new FileUtil(fileUploadForm.getFile().getFileData());			 
			 bankDBScriptRunnerDAO.loadDBScript(fileUtil.getFileReader());
			 setSuccessMessage("success.fileUpload", request);
			session.removeAttribute("loggedInUser");
			session.invalidate();
		 }	 
		
	
		return mapping.findForward("login");
	}
	
	//* @see org.apache.struts.actions.DispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 public ActionForward clear(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
				 HttpServletResponse response) {
			return mapping.findForward("success");
		}
		 

/*private Reader getFileReader(FileUploadForm fileUploadForm, HttpServletRequest request){
	 
    FormFile file = fileUploadForm.getFile();

    Reader reader=null;
    //Get the servers upload directory real path name
    String filePath = 
           getServlet().getServletContext().getRealPath("/") +"upload";
    

    //create the upload folder if not exists
    File folder = new File(filePath);
    if(!folder.exists()){
    	folder.mkdir();
    }

    String fileName = file.getFileName();

    if(!("").equals(fileName)){  

        System.out.println("Server path:" +filePath);
        File newFile = new File(filePath, fileName);
        try {
        if(!newFile.exists()){
          FileOutputStream fos;
			
				fos = new FileOutputStream(newFile);
				fos.write(file.getFileData());
				 fos.flush();
					fos.close();
			
        }  
        
        request.setAttribute("uploadedFilePath",newFile.getAbsoluteFile());
        request.setAttribute("uploadedFileName",newFile.getName());
        
         reader = new BufferedReader(new FileReader(newFile));
        } catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    return reader;
}
*/	
	
	 
/*	 private Reader getFileReader(FileUploadForm fileUploadForm){
		Reader reader = null;
		try {

			File tempFile = null;
			FileOutputStream fos = null;
			tempFile = File.createTempFile("tempfile", ".tmp");
			fos = new FileOutputStream(tempFile);
			fos.write(fileUploadForm.getFile().getFileData());
			if (fos != null) {
				fos.flush();
				fos.close();
			}
			reader = new BufferedReader(new FileReader(tempFile));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return reader;
	 }
*/	 
}