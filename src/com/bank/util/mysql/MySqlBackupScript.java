package com.bank.util.mysql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import com.bank.helper.ParameterHelper;
import com.bank.util.BankConstant;
import com.bank.util.FileUtil;

public class MySqlBackupScript extends FileUtil {

	static int BUFFER = 10485760;
	
	public MySqlBackupScript(){
		super();
	}
	public void downloadMySqlBackUp(HttpServletResponse response, String databaseName, ParameterHelper parameterHelper) throws Exception{
		byte[] byteArray = executeMySqlBackup(databaseName, parameterHelper.getMySqlServerPath());
		writeByteArrayInFile(byteArray);
		setResponseFormat(response, getByteArrayOutputStream(), BankConstant.APPLICATION_MIME_TYPE_SQL,parameterHelper.getLoginID()+BankConstant.FILE_TYPE_SQL_SUFFIX);
	}
	
	public byte[] executeMySqlBackup(String db, String mySQLPath) 
			throws Exception {
		
		String host = BankConstant.LOCAL_HOST;
		String port = BankConstant.DATABASE_DEFAULT_PORT_NO;
		String user = BankConstant.DATABASE_DEFAULT_USER_ID;
		String password = BankConstant.DATABASE_DEFAULT_PASSWORD;
		System.out.println(host+port+user+password);
		String command = mySQLPath + "\\mysqldump --host="  + host + " --port=" + port + 
		           " --user=" + user + " --password=" + password +
		           " --compact --databases --add-drop-table --complete-insert --extended-insert " +
		           "--skip-comments --skip-triggers "+ db;
		System.out.println(command);
		Process run = Runtime.getRuntime().exec(command);
		InputStream in = run.getInputStream(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer temp = new StringBuffer();
		int count;
		 char[] cbuf = new char[BUFFER];

		while ((count = br.read(cbuf, 0, BUFFER)) != -1)
		       	 temp.append(cbuf, 0, count);

		 br.close();
		 in.close();

		 return temp.toString().getBytes();
		}
}
