package com.bank.validation;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;


public class GenericValidation {
	
	
	protected void addErrors(ActionErrors errors,String label,String errorID){
		errors.add(errorID, new ActionError(errorID,label));
	}
	
	protected void addErrors(ActionErrors errors,String errorID){
		errors.add(errorID, new ActionError(errorID));
	}
	
	public boolean isValidInteger(String value){
		boolean isValidInteger = false;
		try{
		 Integer.parseInt(value);
		 isValidInteger = true;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return isValidInteger;
	}
	
	public boolean isValidLong(String value){
		boolean isValidLong = false;
		try{
		 Long.parseLong(value);
		 isValidLong = true;
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return isValidLong;
	}
	
	/*public boolean isValidDate(String value){
		boolean isValidDate = false;
		try{
			Date date = new Date(value);			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			simpleDateFormat.format(date);
			simpleDateFormat.parse("dd/MM/yyyy");
			isValidDate = true;
		}catch(Exception ex){
			ex.printStackTrace();
			isValidDate = false;
		}
		return isValidDate;
	}*/
	
	
	
}
