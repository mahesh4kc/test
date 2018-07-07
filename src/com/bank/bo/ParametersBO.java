package com.bank.bo;

public class ParametersBO {
	private Integer paramSequence;
	private String paramID;
	private String paramValue;
	private String paramExample;
	private boolean checked;
	
	public boolean getChecked() {
		return this.checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getParamSequence() {
		return this.paramSequence;
	}
	public void setParamSequence(Integer paramSequence) {
		this.paramSequence = paramSequence;
	}
	public String getParamID() {
		return this.paramID;
	}
	public void setParamID(String paramID) {
		this.paramID = paramID;
	}
	public String getParamValue() {
		return this.paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamExample() {
		return paramExample;
	}
	public void setParamExample(String paramExample) {
		this.paramExample = paramExample;
	}
	
}
