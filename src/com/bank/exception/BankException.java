package com.bank.exception;

public class BankException extends BaseException{
	public BankException(String errorMessage){
		super(errorMessage);
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
