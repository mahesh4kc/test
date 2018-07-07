package com.bank.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileUtil extends ResponseUtility {

	File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public FileUtil(byte[] byteArray){
		writeByteArrayInFile(byteArray);
	}
	
	public FileUtil(){
	}
	
	
	/*public void downloadMySqlBackUp(HttpServletResponse response, String databaseName, ParameterHelper parameterHelper) throws Exception{
		byte[] byteArray = new MySqlBackupScript().executeMySqlBackup(databaseName, parameterHelper.getMySqlServerPath()).getBytes();
		//writeSqlInFile(driveName, fileName, data);
		writeByteArrayInTempFile(byteArray);
		writeToOutputStream(response,parameterHelper.getLoginID());
	}
	
	public void writeToOutputStream(HttpServletResponse response, String loginID){
		try {
			setResponseFormat(response, getByteArrayOutputStream(), "application/sql",loginID+BankConstant.FILE_TYPE_SQL_SUFFIX);
		
	}

/*
	private String getMySqlDatabase(String host, String port,    String user, String password, String db, String mySQLPath) 
			throws Exception {
		return new MySqlBackupScript().executeMySqlBackup(db, mySQLPath);
		}
		public  void writeSqlInFile( String driveName, String fileName, byte[] data) throws Exception{
		
		FileOutputStream fos =null;
		 try {  
    	  fos = new FileOutputStream(new File(driveName + "\\" + fileName));
    	  fos.write(data);
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (fos != null) {
              try {
                  fos.flush();
                  fos.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
     //return null; 
	}
*/
	public void writeByteArrayInFile(byte[] byteArray){
		//File tempFile = null;
		try {		
			FileOutputStream fos = null;
			this.file = File.createTempFile("tempfile", ".tmp");
			fos = new FileOutputStream(this.file);
			fos.write(byteArray);
			if (fos != null) {
				fos.flush();
				fos.close();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
		//return tempFile;
	 }

	public ByteArrayOutputStream getByteArrayOutputStream(){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				FileInputStream fileIn;
				try {
					fileIn = new FileInputStream(this.file);
					byte[] outputByte = new byte[4096];
					
				//copy binary contect to output stream
				while(fileIn.read(outputByte, 0, 4096) != -1)
				{
					byteArrayOutputStream.write(outputByte, 0, 4096);
				}
				fileIn.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return byteArrayOutputStream;
	 }
	public Reader getFileReader(){
		Reader reader = null;
		try {			
			reader = new BufferedReader(new FileReader(this.file));
		}catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return reader;
	 }
	 
}
