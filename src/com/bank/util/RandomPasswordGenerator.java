package com.bank.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class RandomPasswordGenerator {
   
    public static void main( String[] av ) {
       
       // System.out.println(getResetPassword() ); 
    }
    
    public String getResetPassword() {
        StringBuffer password = new StringBuffer(20);
        int next = RandomUtils.nextInt(13) + 8;
        password.append(RandomStringUtils.randomAlphanumeric(next));
        return password.toString();
    }

}
