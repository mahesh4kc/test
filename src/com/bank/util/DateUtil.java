package com.bank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DateUtil {

	public static Map<String, Integer> dateCalculation = new HashMap<String, Integer>();
	public static void main(String args[]){
		getTodaysDate();
		//System.out.println(getNoOfMonths( "12/08/2010","12/08/2011", "dd/MM/yyyy"));
		//System.out.println(DifferenceInMonths(getDate("13/09/2010", "dd/MM/yyyy"),getDate("12/08/2010", "dd/MM/yyyy")));
		System.out.println(convertDateFormats("dd/MM/yyyy", "13/09/2010", "yyyy-MM-dd"));
	}
		
	public static String getTodaysDate(){
		String todaysDate="";		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 todaysDate = dateFormat.format(new Date());
		return todaysDate;
	}
	public static Date getDate(String sourceDate,String sourceFormat){
		Date date=null;
		try{
		SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		//parse the string into Date object
		 date = sdfSource.parse(sourceDate);
	}catch(ParseException pex){
		pex.printStackTrace();
	}	
		return date;
	}
	public static String convertDateFormats(
			String sourceFormat,String sourceDate, String requiredFormat){
		String requiredDate = "";
		try{
			
			//if(isValidDate(sourceDate)){
				//create SimpleDateFormat object with source string date format
				SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
				
				//parse the string into Date object
				Date date = sdfSource.parse(sourceDate);
				
				//create SimpleDateFormat object with required string date format
				SimpleDateFormat sdfDestination = new SimpleDateFormat(requiredFormat);
				
				//parse the string into Date object
				 requiredDate = sdfDestination.format(date);
				 
			//}
		
		}catch(ParseException pex){
			pex.printStackTrace();
		}		
		
		return requiredDate;
	}

	public static boolean isValidDate(String inDate) {

	    if (inDate == null)
	      return false;

	    //set the format to use as a constructor argument
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
	    if (inDate.trim().length() != dateFormat.toPattern().length())
	      return false;

	    dateFormat.setLenient(false);
	    
	    try {
	      //parse the inDate parameter
	      dateFormat.parse(inDate.trim());
	    }
	    catch (ParseException pe) {
	      return false;
	    }
	    return true;
	  }
	
	public static double DifferenceInMonths(Date date1, Date date2)
    {
	return DifferenceInYears(date1, date2) * 12;
    }
 
    public static double DifferenceInYears(Date date1, Date date2)
    {
	double days = DifferenceInDays(date1, date2);
	return  days / 365.2425;
    }
 
    public static double DifferenceInDays(Date date1, Date date2)
    {
	return DifferenceInHours(date1, date2) / 24.0;
    }
 
    public static double DifferenceInHours(Date date1, Date date2)
    {
	return DifferenceInMinutes(date1, date2) / 60.0;
    }
 
    public static double DifferenceInMinutes(Date date1, Date date2)
    {
	return DifferenceInSeconds(date1, date2) / 60.0;
    }
 
    public static double DifferenceInSeconds(Date date1, Date date2)
    {
	return DifferenceInMilliseconds(date1, date2) / 1000.0;
    }
 
    private static double DifferenceInMilliseconds(Date date1, Date date2)
    {
	return Math.abs(GetTimeInMilliseconds(date1) - GetTimeInMilliseconds(date2));
    }
 
    private static long GetTimeInMilliseconds(Date date)
    {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	return cal.getTimeInMillis() + cal.getTimeZone().getOffset(cal.getTimeInMillis());
    }

	/*public static int monthsBetween(Date minuend, Date subtrahend)
	{
	Calendar cal = Calendar.getInstance();
	// default will be Gregorian in US Locales
	cal.setTime(minuend);
	int minuendMonth =  cal.get(Calendar.MONTH);
	int minuendYear = cal.get(Calendar.YEAR);
	cal.setTime(subtrahend);
	int subtrahendMonth =  cal.get(Calendar.MONTH);
	int subtrahendYear = cal.get(Calendar.YEAR);

	// the following will work okay for Gregorian but will not
	// work correctly in a Calendar where the number of months 
	// in a year is not constant
	return ((minuendYear - subtrahendYear) * cal.getMaximum(Calendar.MONTH)) +  
	(minuendMonth - subtrahendMonth);
	}*/
    
    private static void printDiff(String sdate1, String sdate2, String fmt, TimeZone	 tz)
    {
        SimpleDateFormat df = new SimpleDateFormat(fmt);

        Date date1  = null;
        Date date2  = null;
        
        try 
        {
            date1 = df.parse(sdate1); 
            date2 = df.parse(sdate2); 
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }

        Calendar cal1 = null; 
        Calendar cal2 = null;
        
        if (tz == null)
        {
          cal1=Calendar.getInstance(); 
          cal2=Calendar.getInstance(); 
        }
        else
        {
          cal1=Calendar.getInstance(tz); 
          cal2=Calendar.getInstance(tz); 
        }
          
        
        // different date might have different offset
        cal1.setTime(date1);          
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
        
        cal2.setTime(date2);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
        
        // Use integer calculation, truncate the decimals
        int hr1   = (int)(ldate1/3600000); //60*60*1000
        int hr2   = (int)(ldate2/3600000);

        int days1 = (int)hr1/24;
        int days2 = (int)hr2/24;

        
        int dateDiff  = days2 - days1;
        int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))<0 ? 1 : 0;
        int weekDiff  = dateDiff/7 + weekOffset; 
        int yearDiff  = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
        int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        if(cal2.get(Calendar.DATE) == cal1.get(Calendar.DATE) 
        		&& cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)
        		&& cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)){
            monthDiff = 0;
        }else if(cal2.get(Calendar.DATE) == cal1.get(Calendar.DATE)){
            monthDiff = monthDiff - 1;
        }

        dateCalculation.put("month", monthDiff);
        	
        
        /*System.out.println();  
        System.out.println("DateTime 1: " + sdate1);
        System.out.println("DateTime 2: " + sdate2);
        
        System.out.println("Date difference : " + dateDiff);
        System.out.println("Week difference : " + weekDiff);
        System.out.println("Month difference: " + monthDiff);
        System.out.println("Year difference : " + yearDiff);  */      
    }
    
    public static Integer getNoOfMonths(String firstDate, String lastDate, String fmt){
    	printDiff(firstDate, lastDate, fmt, null);
    	return (Integer)dateCalculation.get("month");
    }
   /* public static void main(String[] args)
    {
        String fmt    = null;
        String sdate1 = null;
        String sdate2 = null;
        
        fmt    = "MM-dd-yyyy HH:mm:ss";
        sdate1 = "12-31-2002 23:59:59";
        sdate2 = "01-01-2003 00:00:01";
        
        // Result is independent of format
        // null will print in local timezone
        // Print out 1 day, i month, 1 year difference
        System.out.println("In your local time:");  
        printDiff(sdate1, sdate2, fmt, null);
        
        // Beijing timezone, if you are not in +8 timezone, the resuls are all 0's
        System.out.println("In Beijing time:");  
        printDiff(sdate1, sdate2, fmt, TimeZone.getTimeZone("GMT+08:00"));
        
        // for testing the weekDiff
        fmt    = "MM-dd-yyyy HH:mm:ss";
        sdate1 = "12-31-2002 23:59:59";
        sdate2 = "01-06-2003 00:00:01";
        System.out.println("In your local time:");  
        printDiff(sdate1, sdate2, fmt, null);
        
        fmt    = "MM-dd-yyyy HH:mm:ss";
        sdate1 = "01-04-2003 23:59:59";
        sdate2 = "01-05-2003 00:00:01";
        System.out.println("In your local time:");  
        printDiff(sdate1, sdate2, fmt, null);
        
        // something interesting here
        fmt    = "MM-dd-yyyy HH:mm:ss";
        sdate1 = "12-31-1996 23:59:59";
        sdate2 = "01-01-1997 00:00:01";
        System.out.println("In your local time:");  
        printDiff(sdate1, sdate2, fmt, null);
    }*/

	}
