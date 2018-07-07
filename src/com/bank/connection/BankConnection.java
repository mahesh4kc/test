package com.bank.connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BankConnection{
	private Connection connection;
	
	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void closeConnection() throws SQLException{
		this.connection.close();
	}
}
