package com.bank.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import com.bank.util.dbscriptrunner.DBScriptRunner;

public class BankDBScriptRunnerDAO extends BankDAO{

	public static final String CLASS_NAME = "BankDBScriptRunnerDAO";
		
		
	private String jndiName;
	
	
	public String getJndiName() {
		return this.jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/*
	 * 
	 */
	
	public BankDBScriptRunnerDAO(String jndiName) {
		super();
		//objConnection = getConnection(jndiName);
		this.jndiName = jndiName;
	}
	
	public void loadDBScript(Reader reader)  {
		// final String METHOD_NAME = "createProductType";
		Connection objConnection = getConnection(this.jndiName);
		try {
			/*Reader reader = new BufferedReader(
					new FileReader(
							"D:\\Hosting Details\\JVMHost\\DB Back UP\\21-APR-2013\\jewelba_THULASIRAMKAG.sql"));*/

			DBScriptRunner dbScriptRunner = new DBScriptRunner(objConnection,false, true);
			dbScriptRunner.runScript(reader);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			objConnection = null;
		}
	}

}
