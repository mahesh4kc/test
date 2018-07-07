package com.bank.util;

public class NumberUtils {
	public static Integer convertStringToInteger(String value){
		Integer intValue=0;
		try{
			intValue = Integer.parseInt(value);
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return intValue;
	}
}
