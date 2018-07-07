package com.bank.exception;

public abstract class BaseException extends Exception{
	String errorMessage;
	
	public BaseException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public abstract void setErrorMessage(String errorMessage);
}
