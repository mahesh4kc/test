package com.bank.util;

public class LogMethods {

	private static void printMethodName(String className, String methodName,String startOrEnds){
		System.out.println(className + " : " + methodName+ " : " + startOrEnds);
	}
	
	public static void printMethodStarts(String className, String methodName){
		printMethodName(className, methodName , BankConstant.STARTS);
	}
	
	public static void printMethodEnds(String className, String methodName){
		printMethodName(className, methodName , BankConstant.ENDS);
	}
}
