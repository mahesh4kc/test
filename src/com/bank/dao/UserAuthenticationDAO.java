package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.bo.UserAuthenticationBO;
import com.bank.form.LoginForm;
import com.bank.util.CryptoLibrary;
import com.bank.util.FilterClausesConstant;
import com.bank.util.LogMethods;
import com.bank.util.RandomPasswordGenerator;

public class UserAuthenticationDAO extends BankDAO{
	
	final String CLASS_NAME = UserAuthenticationDAO.class.getName();
		

	public static final String CREATE_USER_AUTHENTICATION= " INSERT INTO USER_AUTHENTICATION " +
															" " +
															"(USER_ID, USER_PASSWORD, USER_MAIL_ID,USER_DATABASE_NAME,USER_NAME," +
															" PHONE_NO,LOCATION,LAST_NAME,SHOP_NAME,CREATE_DATE,UPDATE_DATE) " +
															"  VALUES (?,?,?,?,?,?,?,?,?,?,? )";
	
	public static final String UPDATE_USER_AUTHENTICATION = " UPDATE USER_AUTHENTICATION SET " +
													" USER_PASSWORD = ?, USER_MAIL_ID =?, USER_NAME =?, " +
													" UPDATE_DATE = ?  WHERE USER_ID = ?";
	
	public static final String UPDATE_PASSWORD_AUTHENTICATION = " UPDATE USER_AUTHENTICATION SET " +
	" USER_PASSWORD = ? , UPDATE_DATE = ?  WHERE USER_ID = ?";
	
	public static final String DELETE_USER_AUTHENTICATION = "DELETE FROM BILL_HEADER WHERE BILL_SEQUENCE = ?";
	
	public static final String SELECT_ALL_USER_AUTHENTICATION = " SELECT " +
															    " USER_ID, USER_PASSWORD, USER_MAIL_ID, " +
															    " USER_DATABASE_NAME,USER_NAME,PHONE_NO,LOCATION,LAST_NAME,SHOP_NAME " +
															    " FROM USER_AUTHENTICATION ";
	
	public static final String SELECT_USER_AUTHENTICATION_BY_USER_ID =  SELECT_ALL_USER_AUTHENTICATION +
																		FilterClausesConstant.WHERE + " USER_ID " + FilterClausesConstant.EQUAL + " ? ";
	
	public static final String SELECT_USER_AUTHENTICATION_BY_USER_ID_PASSWORD =  SELECT_ALL_USER_AUTHENTICATION +
																		FilterClausesConstant.WHERE + " USER_ID " + FilterClausesConstant.EQUAL + " ? " +
																		FilterClausesConstant.AND + " USER_PASSWORD " + FilterClausesConstant.EQUAL + " ? ";

	public static final String ORDER_BY_USER_ID = " ORDER BY USER_ID";
	
	public static final String SELECT_USER_AUTHENTICATION_BY_DATABASE_NAME =  SELECT_ALL_USER_AUTHENTICATION +
			FilterClausesConstant.WHERE + " USER_DATABASE_NAME " + FilterClausesConstant.EQUAL + " ? ";

	public static final String SELECT_USER_AUTHENTICATION_BY_MAIL_ID =  SELECT_ALL_USER_AUTHENTICATION +
			FilterClausesConstant.WHERE + " USER_MAIL_ID " + FilterClausesConstant.EQUAL + " ? ";

	public static final String SELECT_USER_AUTHENTICATION_BY_MOBILE_NO =  SELECT_ALL_USER_AUTHENTICATION +
			FilterClausesConstant.WHERE + " PHONE_NO " + FilterClausesConstant.EQUAL + " ? ";

	public static final String SELECT_USER_AUTHENTICATION_BY_USER_ID_EMAIL_ID =  SELECT_USER_AUTHENTICATION_BY_USER_ID +
			FilterClausesConstant.AND + " USER_MAIL_ID " + FilterClausesConstant.EQUAL + " ? ";

	//Connection objConnection;
	
	//PreparedStatement objPreparedStatement;
	
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
	public UserAuthenticationDAO(String jndiName) {
		super();
		this.jndiName = jndiName;
		//objConnection = getConnection(jndiName);
	}
	
	public UserAuthenticationBO executeUserAuthenticationByLoginIdPassword(String userID,String password){
		final String  METHOD_NAME = "executeUserAuthenticationByLoginIdPassword";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		//System.out.println("executeUserAuthenticationByLoginIdPassword");
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{
			CryptoLibrary cryptoLibrary = new CryptoLibrary();
			String encryptUserID = cryptoLibrary.encrypt(userID.toUpperCase().trim());
			String encryptPassword = cryptoLibrary.encrypt(password);
			//String decryptUserID = cryptoLibrary.decrypt(userID);
			//String decryptPassword = cryptoLibrary.decrypt(password);
			 //objConnection = getConnection();		 
			 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_USER_ID_PASSWORD + ORDER_BY_USER_ID);	
			 //System.out.println(SELECT_USER_AUTHENTICATION_BY_USER_ID_PASSWORD + " Value for userID is : " + userID + " Password : " + password);
			 objPreparedStatement.setString(1, encryptUserID);
			 objPreparedStatement.setString(2, encryptPassword);
			  resultSet = objPreparedStatement.executeQuery();
			while (resultSet.next()){
				//System.out.println("resultSet");
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);				
			}
			
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage() );
			 sqlex.printStackTrace();
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
			 ex.printStackTrace();
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}		
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		return userAuthenticationBO;			
	}
	
	
	public UserAuthenticationBO executeUserAuthenticationByLoginId(String userID){
		final String  METHOD_NAME = "executeUserAuthenticationByLoginId";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{
			CryptoLibrary cryptoLibrary = new CryptoLibrary();
			String encryptUserID = cryptoLibrary.encrypt(userID.toUpperCase().trim());
			//String encryptPassword = cryptoLibrary.encrypt(password);
			//String decryptUserID = cryptoLibrary.decrypt(userID);
			//String decryptPassword = cryptoLibrary.decrypt(password);
			 //objConnection = getConnection();		 
			 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_USER_ID + ORDER_BY_USER_ID);	
			// System.out.println(" Value for userID is : " + userID);
			 objPreparedStatement.setString(1, encryptUserID);
			  resultSet = objPreparedStatement.executeQuery();
			while (resultSet.next()){
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);
				break;	//since user id is unique we are coming out of it
			}
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}		
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 return userAuthenticationBO;			
	}
	
	public UserAuthenticationBO executeUserAuthenticationByDatabaseName(String databaseName){
		final String  METHOD_NAME = "executeUserAuthenticationByLoginId";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_DATABASE_NAME + ORDER_BY_USER_ID);	
			// System.out.println(" Value for userID is : " + userID);
			 objPreparedStatement.setString(1, databaseName.toUpperCase().trim());
			  resultSet = objPreparedStatement.executeQuery();
			while (resultSet.next()){
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);
				break;//always it will exists with only one database
			}
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 return userAuthenticationBO;			
	}
	
	
	
	public boolean userExists(String userID,String password) throws SQLException{
		
		boolean userExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByLoginIdPassword(
				userID.toUpperCase().trim(), password);
		if (userAuthenticationBO != null){			
			userExists = true;
			//System.out.println("userExists:"+userExists);
		}
		}finally{
			
		}
		return userExists;
	}
	
	public boolean userExists(String userID) throws SQLException{
		
		boolean userExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByLoginId(userID.toUpperCase().trim());
		if (userAuthenticationBO != null){			
			userExists = true;
			//System.out.println("userExists:"+userExists);
		}
		}finally{
			
		}
		return userExists;
	}
	
	public boolean databaseExists(String databaseName) throws SQLException{
		
		boolean databaseExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByDatabaseName(databaseName);
		if (userAuthenticationBO != null){			
			databaseExists = true;
			//System.out.println("userExists:"+userExists);
		}
		}finally{
			
		}
		return databaseExists;
	}

	private UserAuthenticationBO getUserAuthenticationBOFromResultSet(ResultSet resultSet) throws SQLException{
		
	
		UserAuthenticationBO userAuthenticationBO = null;
		try{
		userAuthenticationBO = new UserAuthenticationBO();		
		userAuthenticationBO.setLoginID(resultSet.getString(1));
		userAuthenticationBO.setPassword(resultSet.getString(2));
		userAuthenticationBO.setEmailID(resultSet.getString(3));
		userAuthenticationBO.setDatabaseName(resultSet.getString(4));
		userAuthenticationBO.setUserName(resultSet.getString(5));
		userAuthenticationBO.setPhoneNumber(resultSet.getLong(6));
		userAuthenticationBO.setLocation(resultSet.getString(7));
		userAuthenticationBO.setLastName(resultSet.getString(8));
		userAuthenticationBO.setShopName(resultSet.getString(9));
		//System.out.println("User Details : " + userAuthenticationBO.getLoginID() + " : " +  userAuthenticationBO.getPassword());
	}finally{		
		
	}
	return userAuthenticationBO;
}
	
	public void updatePassword(LoginForm loginForm, boolean resetPassword){
		final String  METHOD_NAME = "updatePassword";
		//System.out.println("updatePassword starts");
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;		
		
		String loginID=loginForm.getLoginID().toUpperCase().trim();
		String newPassword = loginForm.getPassword();		
		try{		
			
			CryptoLibrary cryptoLibrary = new CryptoLibrary();
			String encryptPassword = cryptoLibrary.encrypt(newPassword);
			String encryptUserID = cryptoLibrary.encrypt(loginID);
			if(resetPassword)
				encryptPassword = cryptoLibrary.encrypt(new RandomPasswordGenerator().getResetPassword());					
			 //objConnection = getConnection();		 
			 objPreparedStatement = objConnection.prepareStatement(UPDATE_PASSWORD_AUTHENTICATION);
			 System.out.println("UPDATE_PASSWORD_AUTHENTICATION"+UPDATE_PASSWORD_AUTHENTICATION);
			 objPreparedStatement.setString(1, encryptPassword);
			 objPreparedStatement.setDate(2,  new java.sql.Date(new java.util.Date().getTime()));	//shop name
			 objPreparedStatement.setString(3,  encryptUserID);							
			 //noOfInsertedRecords = 
			 objPreparedStatement.executeUpdate();	
			
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objPreparedStatement = null;
			objConnection = null;
		}LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 //System.out.println("updatePassword ends");
		// return objProductTypeBO;
			
	}
	
	public void createRegistration(LoginForm loginForm){
		final String  METHOD_NAME = "createUserIDPassword";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);

		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		String loginID=loginForm.getLoginID().toUpperCase().trim();
		String newPassword = loginForm.getPassword();
				try{	
					CryptoLibrary cryptoLibrary = new CryptoLibrary();
					String encryptUserID = cryptoLibrary.encrypt(loginID);
					String encryptPassword = cryptoLibrary.encrypt(newPassword);
		 //objConnection = getConnection();		 
		 objPreparedStatement = objConnection.prepareStatement(CREATE_USER_AUTHENTICATION);
		 System.out.println("CREATE_USER_AUTHENTICATION"+CREATE_USER_AUTHENTICATION);
		 objPreparedStatement.setString(1, encryptUserID);
		 objPreparedStatement.setString(2,  encryptPassword);
		 objPreparedStatement.setString(3,  loginForm.getEmailID().toUpperCase().trim());
		 objPreparedStatement.setString(4,  loginForm.getLoginID().toUpperCase().trim());
		 objPreparedStatement.setString(5,  loginForm.getUserName());
		 objPreparedStatement.setLong(6,  Long.parseLong(loginForm.getPhoneNumber()));
		 objPreparedStatement.setString(7,  loginForm.getLocation());
		 objPreparedStatement.setString(8,  loginForm.getLastName());
		 objPreparedStatement.setString(9,  loginForm.getShopName());	//shop name
		 objPreparedStatement.setDate(10,  new java.sql.Date(new java.util.Date().getTime()));	//shop name
		 objPreparedStatement.setDate(11,  new java.sql.Date(new java.util.Date().getTime()));	//shop name
		 //noOfInsertedRecords = 
			 objPreparedStatement.executeUpdate();	 
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objPreparedStatement = null;
			objConnection = null;
		}
				LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		// return objProductTypeBO;
			
	}
	
	public UserAuthenticationBO executeUserAuthenticationByMobileNo(Long mobileNo){
		final String  METHOD_NAME = "executeUserAuthenticationByMobileNo";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_MOBILE_NO + ORDER_BY_USER_ID);	
			// System.out.println(" Value for userID is : " + userID);
			 objPreparedStatement.setLong(1, mobileNo);
			  resultSet = objPreparedStatement.executeQuery();
			while (resultSet.next()){
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);
				break;	//since user id is unique we are coming out of it
			}
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 return userAuthenticationBO;			
	}	

	public UserAuthenticationBO executeUserAuthenticationByMailId(String mailID){
		final String  METHOD_NAME = "executeUserAuthenticationByMailId";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_MAIL_ID + ORDER_BY_USER_ID);	
			// System.out.println(" Value for userID is : " + userID);
			 objPreparedStatement.setString(1, mailID);
			  resultSet = objPreparedStatement.executeQuery();
			while (resultSet.next()){
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);
				break;	//since user id is unique we are coming out of it
			}
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 return userAuthenticationBO;			
	}
	
	public boolean isMobileNumberExists(Long mobileNo) throws SQLException{
		
		boolean mobileNoExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByMobileNo(mobileNo);
		if (userAuthenticationBO != null){			
			mobileNoExists = true;
			//System.out.println("userExists:"+userExists);
		}
		}finally{
			
		}
		return mobileNoExists;
	}
	
	public boolean isEmailExists(String mailID) throws SQLException{
		
		boolean eMailExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByMailId(mailID.toUpperCase().trim());
		if (userAuthenticationBO != null){			
			eMailExists = true;
			//System.out.println("userExists:"+userExists);
		}
		}finally{
			
		}
		return eMailExists;
	}
	
	public UserAuthenticationBO executeUserAuthenticationByLoginIdMailId(String userID, String emailID){
		final String  METHOD_NAME = "executeUserAuthenticationByLoginIdMailId";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		UserAuthenticationBO userAuthenticationBO = null;
		try{
			CryptoLibrary cryptoLibrary = new CryptoLibrary();
			String encryptUserID = cryptoLibrary.encrypt(userID.toUpperCase().trim());
			//String encryptPassword = cryptoLibrary.encrypt(password);
			//String decryptUserID = cryptoLibrary.decrypt(userID);
			//String decryptPassword = cryptoLibrary.decrypt(password);
			 //objConnection = getConnection();		 
			 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_USER_AUTHENTICATION_BY_USER_ID_EMAIL_ID + ORDER_BY_USER_ID);	
			
			 objPreparedStatement.setString(1, encryptUserID);
			 objPreparedStatement.setString(2, emailID.toUpperCase().trim());
			  resultSet = objPreparedStatement.executeQuery();
			  System.out.println(SELECT_USER_AUTHENTICATION_BY_USER_ID_EMAIL_ID);
			  System.out.println(" Value for userID is : " + encryptUserID);
				 System.out.println(" Value for emailID is : " + emailID.toUpperCase().trim());
			while (resultSet.next()){
				
				userAuthenticationBO = getUserAuthenticationBOFromResultSet(resultSet);
				break;	//since user id is unique we are coming out of it
			}
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		 }finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
			objPreparedStatement = null;
			objConnection = null;
		}
		LogMethods.printMethodEnds(CLASS_NAME, METHOD_NAME);
		 return userAuthenticationBO;			
	}
	
	public boolean isUserIdEmailIdExists(String userID, String mailID) throws SQLException{
		System.out.println("isUserIdEmailIdExists:");
		boolean loginIdEMailExists = false;
		UserAuthenticationBO userAuthenticationBO = null;
		try{			
		///System.out.println("userID:"+userID+"password:"+password);
		userAuthenticationBO = executeUserAuthenticationByLoginIdMailId(userID, mailID);
		if (userAuthenticationBO != null){			
			loginIdEMailExists = true;
			System.out.println("loginIdEMailExists:"+loginIdEMailExists);
		}
		}finally{
			
		}
		return loginIdEMailExists;
	}
	
}
