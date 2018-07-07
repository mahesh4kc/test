package com.bank.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.ParametersBO;



public class ParameterForm extends BaseForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 959559207897368560L;
	
	private List<ParametersBO> parameterList;
	
	private String paramID;
	
	private String paramExample;
	
	public String getParamExample() {
		return paramExample;
	}


	public void setParamExample(String paramExample) {
		this.paramExample = paramExample;
	}


	public List<ParametersBO> getParameterList() {
		return this.parameterList;
	}


	public void setParameterList(List<ParametersBO> parameterList) {
		this.parameterList = parameterList;
	}


	public String getParamID() {
		return this.paramID;
	}


	public void setParamID(String paramID) {
		this.paramID = paramID;
	}


	@SuppressWarnings("unchecked")
	public void reset(ActionMapping mapping,HttpServletRequest request) {
		// System.out.println("Form is Reset");	
		 this.parameterList = ListUtils.lazyList(new ArrayList<ParametersBO>(),
		        new Factory() {
		            public Object create() {
		                return new ParametersBO();
		            }
		        });
		
				setUserLoggedIn(request);
			
	}
	
}
