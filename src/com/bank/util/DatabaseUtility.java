package com.bank.util;


import java.io.IOException;


public class DatabaseUtility {

  public static void main(String[] args) {


  try {
  System.out.println("database connect");


  Runtime rt = Runtime.getRuntime();

  //rt.exec("C:/Program Files/MySQL/MySQL Server 5.1/bin/mysqldump -uroot -proot omprakashkag > e:/backup/database.sql");
  rt.exec("e:/backup/DatabaseBackUp.bat");
  /*rt.exec("cmd");
  rt.exec(" cd C:/Program Files/MySQL/MySQL Server 5.1/bin");
		  rt.exec("mysqldump -uroot -proot omprakashkag > e:/backup/omprakashkag.sql");*/
 // rt.exec("cmd;cd C:/Program Files/MySQL/MySQL Server 5.1/bin;mysqldump -uroot -proot omprakashkag > e:/backup/omprakashkag.sql");
//  rt.exec( "cmd.exe /c cd C:/Program Files/MySQL/MySQL Server 5.1/bin /c mysqldump -uroot -proot omprakashkag > e:/backup/omprakashkag.sql" ); 
  } 

 catch(IOException ioe) 
  {
   ioe.printStackTrace();
  }
 
  catch(Exception e) {
  e.printStackTrace();
 }
}


}