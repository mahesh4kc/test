package com.bank.util;

public class CalculatorUtil {
	
	public static void main(String args[]){
		System.out.println(getInterest(2000,2.5,12));
	}
	public static double getInterest(int amount,double rateOfInterest,int months){
		return  (amount * months * rateOfInterest / 100 );
	}
}
