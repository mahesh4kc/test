package com.bank.business.validation;

import org.apache.struts.action.ActionErrors;

import com.bank.form.FileUploadForm;

public class FileUploadValidation extends GenericBusinessValidation{

	public void validateFileUploadDetailsForSave(ActionErrors errors, FileUploadForm fileUploadForm) {
		if(fileUploadForm.getFile().getFileSize() == 0 ) {
			addErrors(errors, "Select File ", "errors.required");		
		}
		 //file size cant larger than 1000kb
	    /*if(fileUploadForm.getFile().getFileSize() > 1024000000){ 
	    	addErrors(errors, "File size exceeding ", " ");
	    }*/
	}
}
