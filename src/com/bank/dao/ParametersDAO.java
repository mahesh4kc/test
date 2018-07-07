package com.bank.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.ParametersBO;
import com.bank.util.BankConstant;
import com.bank.util.LogMethods;

public class ParametersDAO extends BankDAO{
	
	final String CLASS_NAME = ParametersDAO.class.getName();
		
	public static final String CREATE_PARAMETERS = "INSERT INTO PARAMETERS (PARAM_SEQ, " +
			"PARAM_ID,PARAM_VALUE,PARAM_EXAMPLE) VALUES (?,?,?,?)";
	
	public static final String UPDATE_PARAMETERS = "UPDATE PARAMETERS SET " +
			"PARAM_VALUE = ? WHERE PARAM_SEQ = ? ";
	
	public static final String DELETE_PARAMETERS = "DELETE FROM PARAMETERS WHERE PARAM_SEQ = ? ";
	
	public static final String SELECT_ALL_PARAMETERS = "SELECT PARAM_SEQ,PARAM_ID, " +
														"PARAM_VALUE,PARAM_EXAMPLE FROM PARAMETERS  ";
	
	//public static final String WHERE_PRODUCT_DESC_LIKE = "WHERE PRODUCT_DESCRIPTION " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE;

	public static final String SELECT_PARAMETERS_MAX_NO = "SELECT MAX(PARAM_SEQ) FROM PARAMETERS";
	
	public static final String ORDER_BY_PARAM_ID = " ORDER BY PARAM_ID";
	
	//Connection objConnection;
	
	PreparedStatement objPreparedStatement;
	
	private String jndiName;
	
	
	public String getJndiName() {
		return this.jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	
	public ParametersDAO(String jndiName) {
		super();
		//objConnection = getConnection(jndiName);
		this.jndiName = jndiName;
	}
	
	private void createParameters(List<ParametersBO> parametersList ) throws SQLException{
		final String  METHOD_NAME = "createParameters";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		 Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
			
		 int noOfInsertedRecords[];
		int parameterSequence =0;
		try{		
		 //objConnection = getConnection();		 
		// System.out.println("CREATE_PARAMETERS " + CREATE_PARAMETERS);
		 objPreparedStatement = objConnection.prepareStatement(CREATE_PARAMETERS);
		 parameterSequence = getMaxParameterSequenceNo();
		 for(ParametersBO parametersBO: parametersList){
			 if(parametersBO != null && parametersBO.getParamSequence() != null 
					 && parametersBO.getParamSequence().intValue() == 0){				 
				 //System.out.println(" Next Sequence is : " + parameterSequence);
				 objPreparedStatement.setInt(1, new Integer(parameterSequence));
				 //objPreparedStatement.setString(2, productDescriptionBO.getProductCode().toUpperCase());
				 objPreparedStatement.setString(2, parametersBO.getParamID().toUpperCase());		
				 objPreparedStatement.setString(3, parametersBO.getParamValue().toUpperCase());	
				 objPreparedStatement.addBatch(); 
				 parameterSequence = parameterSequence + 1;
				 noOfInsertedRecords = objPreparedStatement.executeBatch();	
			 }					
		 }

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
	private void updateParameters(List<ParametersBO> parametersList  ){
		final String  METHOD_NAME = "updateParameters";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		int noOfInsertedRecords[];
		 Connection objConnection = getConnection(this.jndiName);
			PreparedStatement objPreparedStatement = null;
		try{		
		// objConnection = getConnection();		 
		 //System.out.println("UPDATE_PARAMETERS : " + UPDATE_PARAMETERS);
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_PARAMETERS);
		 
		 for(ParametersBO parametersBO: parametersList){
			 if(parametersBO!= null && !parametersBO.getChecked()){ 
				 objPreparedStatement.setString(1, parametersBO.getParamValue().toUpperCase());
				 objPreparedStatement.setInt(2,  parametersBO.getParamSequence());
				 objPreparedStatement.addBatch();
			 }			
		 }
		 
		 noOfInsertedRecords = objPreparedStatement.executeBatch();	 
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
	}
	
	private void deleteParameters(List<ParametersBO> parametersList  ){
		final String  METHOD_NAME = "deleteParameters";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		 Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
			
		 int noOfInsertedRecords[];
		List<ParametersBO> dbParametersList = null;
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println(" DELETE_PARAMETERS : " + DELETE_PARAMETERS);
		 dbParametersList = executeAllParameters(); 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_PARAMETERS);
		// System.out.println("dbSize : "+ dbParametersList.size() + "Size : "+parametersList.size());
		 
		 for(ParametersBO parametersBO: parametersList){
			 //select the records which will have product no && also checked 
			 if(parametersBO != null && parametersBO.getChecked() && parametersBO.getParamSequence() != 0){
				 objPreparedStatement.setInt(1, parametersBO.getParamSequence());
				 //System.out.println( DELETE_PARAMETERS + parametersBO.getParamSequence());
				 objPreparedStatement.addBatch();
				// break;
			 }
			 noOfInsertedRecords = objPreparedStatement.executeBatch();
		 }	
		
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		} catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());

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
		// return noOfInsertedRecords;
			
	}

	
	public List<ParametersBO> executeAllParameters() throws SQLException,Exception{
		final String  METHOD_NAME = "executeAllParameters";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		List<ParametersBO> parametersList = new ArrayList<ParametersBO>();
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("SELECT_ALL_PARAMETERS : " + SELECT_ALL_PARAMETERS + ORDER_BY_PARAM_ID);
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PARAMETERS + ORDER_BY_PARAM_ID);		 
		resultSet = objPreparedStatement.executeQuery();
		setParameterListFromResultSet(resultSet, parametersList);
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
		 return parametersList;			
	}
	
	public List<ParametersBO> executeParameterSequence(String paramsequence ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeParameterSequence";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<ParametersBO> parametersList = new ArrayList<ParametersBO>();
		try{		
		//objConnection = getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PARAMETERS +
				" WHERE PARAM_SEQ = ? " + ORDER_BY_PARAM_ID);		
		objPreparedStatement.setInt(1, Integer.parseInt(paramsequence));		
		  resultSet = objPreparedStatement.executeQuery();
		 setParameterListFromResultSet(resultSet, parametersList);				
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
		 return parametersList;			
	}
	
	public List<ParametersBO> executeParameterIDLike(String paramID ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeParameterID";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<ParametersBO> parametersList = new ArrayList<ParametersBO>();
		
		try{		
		//objConnection = getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PARAMETERS +
				" WHERE PARAM_ID LIKE ? " + ORDER_BY_PARAM_ID);		
		objPreparedStatement.setString(1, paramID+"%");		
		  resultSet = objPreparedStatement.executeQuery();
		 setParameterListFromResultSet(resultSet, parametersList);				
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
		 return parametersList;			
	}
	
	public ParametersBO executeParameterIDEqual(String paramID ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeParameterID";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		ParametersBO parametersBO=null;
		//List<ParametersBO> parametersList = new ArrayList<ParametersBO>();
			try{		
		//objConnection = getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PARAMETERS +
				" WHERE PARAM_ID = ? " + ORDER_BY_PARAM_ID);		
		objPreparedStatement.setString(1, paramID);	
		//System.out.println("executeParameterIDEqual:" + SELECT_ALL_PARAMETERS +
			//	" WHERE PARAM_ID = " + paramID + ORDER_BY_PARAM_ID);
		 resultSet = objPreparedStatement.executeQuery();
		while(resultSet.next()){
			parametersBO = getParameterBOFromResultSet(resultSet);
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
		 return parametersBO;			
	}
	
	private Integer getMaxParameterSequenceNo(){
		final String  METHOD_NAME = "getMaxParameterSequenceNo";
		LogMethods.printMethodStarts(CLASS_NAME, METHOD_NAME);
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		int maxProduct =0;
		try{		
		 //objConnection = getConnection();		 
		// System.out.println("SELECT_PARAMETERS_MAX_NO : " + SELECT_PARAMETERS_MAX_NO);
			objPreparedStatement = objConnection.prepareStatement(SELECT_PARAMETERS_MAX_NO);				
		 resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			maxProduct = resultSet.getInt(1);			
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
		 return (maxProduct+1);			
	}
//	private populateProductTypeBO(){
		
	//}
	
	private void setParameterListFromResultSet(ResultSet resultSet, List<ParametersBO> parametersList) throws SQLException{
		
		ParametersBO parametersBO;
		while (resultSet.next()){
			parametersBO = getParameterBOFromResultSet(resultSet);
			parametersList.add(parametersBO);
		}
	}
	
	private ParametersBO getParameterBOFromResultSet(ResultSet resultSet) throws SQLException{
		ParametersBO parametersBO = new ParametersBO();		
		parametersBO.setParamSequence(resultSet.getInt(1));
		parametersBO.setParamID(resultSet.getString(2));
		//parametersBO.setParamValue(resultSet.getString(3));
		
		try {
			byte[] bs = resultSet.getBytes(3);
			String str = new String(bs, BankConstant.CHARACTER_ENCODING);
			//System.out.println(str);
			parametersBO.setParamValue(new String(resultSet.getBytes(3),BankConstant.CHARACTER_ENCODING));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parametersBO.setParamExample(resultSet.getString(4));
	
		return parametersBO;
	}
	
	public void createUpdateDelete(List<ParametersBO> parametersList) throws SQLException{
		createParameters(parametersList);
		updateParameters(parametersList);
		deleteParameters(parametersList);		
	}

	
	
	
}
