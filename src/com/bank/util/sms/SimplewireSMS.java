package com.bank.util.sms;

import com.simplewire.sms.*;

// Public Class
public class SimplewireSMS extends java.lang.Object {

    public static void main(String[] args) throws Exception 
    { 
        // Create SMS Object 
        SMS sms = new SMS();

        // Set Message Properties 
        sms.setMsgPin("919940543805"); 
        sms.setMsgFrom("mahesh"); 
        sms.setMsgCallback("919940543805"); 
        sms.setMsgText("Hello World From Java SMS!"); 

        // Display The Status 
        System.out.println("Submitting SMS To Simplewire..."); 

        // Send the request to simplewire 
        sms.msgSend(); 

        // Check if the request was sent 
        if (sms.isSuccess()) 
        { 
            // Display the status 
            System.out.println( "The message was sent!" ); 
        } 
        // Display the error info 
        else 
        { 
            // Check If Carrier Recognition Error 
            if(sms.getErrorCode().equals("345")) 
            { 
                System.out.println("Sample request was success."); 
                System.out.println("However, could not recognize carrier.\n"); 
            } 
            else 
            { 
                // Display Error Info 
                System.out.println("The message was not sent!"); 
                System.out.println("Error Code: " + sms.getErrorCode()); 
                System.out.println("Error Description: " + sms.getErrorDesc() + "\n"); 
            } 
        } 
    }
}