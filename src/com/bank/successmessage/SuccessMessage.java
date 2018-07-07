package com.bank.successmessage;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.bank.action.BaseAction;

public abstract class SuccessMessage  extends BaseAction{

	//key is from applicationresources.properties
	//This is to set the success message in the request object
	//Display it on the screen if transaction is success
	public void setSuccessMessage(String key, HttpServletRequest request){
		 ActionMessages messages = new ActionMessages();
		 ActionMessage message = new ActionMessage(key);
		 messages.add("message", message);
		 saveMessages(request, messages);
	}
}
