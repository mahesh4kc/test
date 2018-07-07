package com.bank.form;

import org.apache.struts.upload.FormFile;


public class BackUpandRestoreMySqlForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FormFile file;

    public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

}
