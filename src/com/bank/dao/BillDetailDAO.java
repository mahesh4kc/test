package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.BillDetailBO;
import com.bank.helper.BillDetailHelper;
import com.bank.util.FilterClausesConstant;

public class BillDetailDAO extends BankDAO{
	public static final String CLASS_NAME = "ProductTypeDatabase";
		
	public static final String CREATE_BILL_DETAIL = "INSERT INTO BILL_DETAIL ( BILL_SEQUENCE, PRODUCT_NO, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY ) " +
													" VALUES ( ?,?,?,?) ";
	public static final String UPDATE_BILL_DETAIL = "UPDATE BILL_DETAIL " + 
													" SET PRODUCT_DESCRIPTION = ?, PRODUCT_QUANTITY = ? " + 
													" WHERE BILL_SEQUENCE = ? AND PRODUCT_NO = ?";
	public static final String DELETE_BILL_DETAIL = "DELETE FROM BILL_DETAIL WHERE BILL_SEQUENCE = ? AND PRODUCT_NO = ?";
	
	public static final String SELECT_ALL_BILL_DETAIL = "SELECT BILL_SEQUENCE,  PRODUCT_NO, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY " +
														" FROM  BILL_DETAIL  ";
	
	public static final String SELECT_BILL_DETAIL_BY_BILL_SEQUENCE = "SELECT BILL_SEQUENCE,  PRODUCT_NO, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY " +
	" FROM  BILL_DETAIL WHERE BILL_SEQUENCE = ? ";
	
	public static final String SELECT_BILL_DETAIL_BY_PROD_DESC_LIKE = SELECT_ALL_BILL_DETAIL + FilterClausesConstant.WHERE + 																		
																		" PRODUCT_DESCRIPTION " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE ;
	
	public static final String SELECT_BILL_DETAIL_BY_BILL_SEQUENCE_IN = SELECT_ALL_BILL_DETAIL + FilterClausesConstant.WHERE + 																		
																		" BILL_SEQUENCE " ;

	public static final String SELECT_MAX_PRODUCT_NO_FOR_BILL_SEQUENCE = "SELECT MAX(PRODUCT_NO) FROM BILL_DETAIL WHERE BILL_SEQUENCE = ? ";
	public static final String ORDER_BY_BILL_DETAIL = " ORDER BY BILL_SEQUENCE,PRODUCT_NO ";
	
	public static final String DELETE_BILL_DETAILS = "DELETE FROM BILL_DETAIL WHERE BILL_SEQUENCE = ? ";
	
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
	
	public BillDetailDAO(String jndiName) {
		super();
		//objConnection = getConnection(jndiName);
		this.jndiName = jndiName;
		
	}
	
	private void createBillDetail(List<BillDetailBO> billDetailList , Integer billSequence) throws SQLException{
		//final String  METHOD_NAME = "createBillDetail";
		
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
	
		
		int noOfInsertedRecords[];
		int productNoForBillSequence =0;

				try{
		//System.out.println("createBillDetail : " );		
		 //objConnection = getConnection();		 
		 
		 objPreparedStatement = objConnection.prepareStatement(CREATE_BILL_DETAIL);
		 productNoForBillSequence = getMaxProductNoForBillSequence(billSequence);
		 for(BillDetailBO billDetailBO: billDetailList){
			 if(billDetailBO != null && billDetailBO.getProductNumber().intValue() == 0 && 
					  billDetailBO.getProductDescription().length() > 0){					 
				 objPreparedStatement.setInt(1,billSequence);
				 objPreparedStatement.setInt(2, productNoForBillSequence);
				 objPreparedStatement.setString(3, billDetailBO.getProductDescription().toUpperCase());
				 objPreparedStatement.setInt(4, billDetailBO.getProductQuantity());
				 objPreparedStatement.addBatch(); 	
				 productNoForBillSequence = productNoForBillSequence+1;
			 }					
		 }
				
		 noOfInsertedRecords = objPreparedStatement.executeBatch();
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
			
	}
	private void updateBillDetail(List<BillDetailBO> billDetailList , Integer billSequence){
		final String  METHOD_NAME = "updateProductType";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		int noOfInsertedRecords[];

				try{		
			System.out.println("updateProductType : ");		
			//objConnection = getConnection();		  
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_BILL_DETAIL);
		 //System.out.println("updateProductType : " + billDetailList.size());		
		 for(BillDetailBO billDetailBO: billDetailList){
			 if(billDetailBO != null ){						
				 objPreparedStatement.setString(1, billDetailBO.getProductDescription().toUpperCase());
				 objPreparedStatement.setInt(2, billDetailBO.getProductQuantity());
				 objPreparedStatement.setInt(3,billSequence);
				 objPreparedStatement.setInt(4, billDetailBO.getProductNumber());
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
	}
	
	private void deleteBillDetail(List<BillDetailBO> billDetailList , Integer billSequence){
		final String  METHOD_NAME = "deleteProductType";
		@SuppressWarnings("unused")
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		
		int noOfInsertedRecords[];

				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_BILL_DETAIL);
		 for(BillDetailBO billDetailBO: billDetailList){
			 if(billDetailBO != null && billDetailBO.getChecked()){
				 objPreparedStatement.setInt(1,billSequence);
				 objPreparedStatement.setInt(2, billDetailBO.getProductNumber());
				 objPreparedStatement.addBatch();
			 }			
		 }		 
		 noOfInsertedRecords = objPreparedStatement.executeBatch();	 
		}catch(SQLException sqlex){
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
	}
	
	public void deleteBillDetails( Integer billSequence){
		final String  METHOD_NAME = "deleteProductType";
		@SuppressWarnings("unused")
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		boolean deleteSuccess;
		int noOfInsertedRecords[];

				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_BILL_DETAILS);
		 objPreparedStatement.setInt(1,billSequence);
			 
		 deleteSuccess = objPreparedStatement.execute();	 
		}catch(SQLException sqlex){
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
	}
/*	public ProductTypeBO updateProductType(ProductTypeBO objProductTypeBO ){
		final String  METHOD_NAME = "updateProductType";
		int noOfInsertedRecords =0;
		try{		
		 objConnection = new MySQLConnection().getConnection();		 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_PRODUCT_TYPE);		 
		 objPreparedStatement.setString(1, objProductTypeBO.getProductTypeCode().toUpperCase());
		 objPreparedStatement.setString(2, objProductTypeBO.getProductTypeDescription().toUpperCase());
		 objPreparedStatement.setDouble(3, objProductTypeBO.getProductTypeRateOfInterest());
		 objPreparedStatement.setInt(4,  objProductTypeBO.getProductTypeNo());
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 System.out.println(noOfInsertedRecords + " Record updated");
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 return objProductTypeBO;
			
	}*/
	/*public int deleteProductType(ProductTypeBO objProductTypeBO ){
		final String  METHOD_NAME = "deleteProductType";
		int noOfInsertedRecords =0;
		try{		
		 objConnection = new MySQLConnection().getConnection();		 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(DELETE_PRODUCT_TYPE);		 
		 objPreparedStatement.setString(1, objProductTypeBO.getProductTypeCode().toUpperCase());
		// objPreparedStatement.setString(2, objProductTypeBO.getProductTypeDescription().toUpperCase());
		 //objPreparedStatement.setDouble(3, objProductTypeBO.getProductTypeRateOfInterest());
		 //objPreparedStatement.setInt(4,  objProductTypeBO.getProductTypeNo());
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 System.out.println(noOfInsertedRecords + " Record updated");
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 return noOfInsertedRecords;
			
	}*/
	
	public List<BillDetailBO> executeAllBillDetails() throws SQLException,Exception{
		//final String  METHOD_NAME = "executeAllBillDetails";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<BillDetailBO> billDetailList = new ArrayList<BillDetailBO>();		

				try{
		//objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_BILL_DETAIL + ORDER_BY_BILL_DETAIL);			 
		 resultSet = objPreparedStatement.executeQuery();
		BillDetailHelper.setBillDetailListFromResultSet(resultSet, billDetailList);		
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
				return billDetailList;			
	}
	
	public List<BillDetailBO> executeAllBillDetails(String productDescription) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeAllBillDetails";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<BillDetailBO> billDetailList = new ArrayList<BillDetailBO>();		

		
				try{
		//objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed() + SELECT_BILL_DETAIL_BY_PROD_DESC_LIKE + productDescription);
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_DETAIL_BY_PROD_DESC_LIKE + ORDER_BY_BILL_DETAIL);	
		objPreparedStatement.setString(1, productDescription+"%");
		 resultSet = objPreparedStatement.executeQuery();
		BillDetailHelper.setBillDetailListFromResultSet(resultSet, billDetailList);		
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
				return billDetailList;			
	}
	
	public List<BillDetailBO> executeAllBillDetailsUsingBillSequenceIn(String billSequenceNumbersUsingIn) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeAllBillDetails";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<BillDetailBO> billDetailList = new ArrayList<BillDetailBO>();		

				try{
		//objConnection = getConnection();		 
		 /*System.out.println("executeAllBillDetailsUsingBillSequenceIn : " + SELECT_BILL_DETAIL_BY_BILL_SEQUENCE_IN 
				 + FilterClausesConstant.IN + FilterClausesConstant.LEFT_PARANTHESIS 
					+ billSequenceNumbersUsingIn + FilterClausesConstant.RIGHT_PARANTHESIS + ORDER_BY_BILL_DETAIL);*/
		objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_DETAIL_BY_BILL_SEQUENCE_IN + ORDER_BY_BILL_DETAIL);	
		//objPreparedStatement.setString(1, billSequenceNumbersUsingIn);
		 resultSet = objPreparedStatement.executeQuery();
		BillDetailHelper.setBillDetailListFromResultSet(resultSet, billDetailList);		
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
				return billDetailList;			
	}
	
	
	
	
	public List<BillDetailBO> executeBillDetailsByBillSequence(Integer billSequence) throws SQLException,Exception{
		//final String  METHOD_NAME = "executeBillDetailsByBillSequence";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<BillDetailBO> billDetailList = null;

				try{
			 billDetailList = new ArrayList<BillDetailBO>();		
			//objConnection = getConnection();		 
			 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_BILL_DETAIL_BY_BILL_SEQUENCE + ORDER_BY_BILL_DETAIL);	
			objPreparedStatement.setInt(1, billSequence);
			resultSet = objPreparedStatement.executeQuery();
			BillDetailHelper.setBillDetailListFromResultSet(resultSet, billDetailList);		
				
				}finally{
					 try {
						objConnection.close();
						resultSet.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resultSet = null;
					objPreparedStatement = null;
					objConnection = null;
				}
		return billDetailList;	
	}
	
	/*public List<ProductTypeBO> executeByProductTypeNoAndCode(String productTypeNo,String productTypeCode) throws SQLException{
		//final String  METHOD_NAME = "executeProductTypeNo";
		StringBuffer queryByProductNoAndCode = new StringBuffer(SELECT_ALL_PRODUCT_TYPE);
		queryByProductNoAndCode.append(" WHERE PRODUCT_TYPE_NO = ? AND PRODUCT_TYPE_CODE LIKE ? " + ORDER_BY_PRODUCT_TYPE);
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		
		 objConnection = new MySQLConnection().getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(queryByProductNoAndCode.toString());	
		objPreparedStatement.setInt(1, Integer.parseInt(productTypeNo));
		objPreparedStatement.setString(2, productTypeCode+"%");
		
		 ResultSet resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet,productTypeList);
		
		 return productTypeList;			
	}*/
	
	/*public List<ProductTypeBO> executeProductTypeNo(String productTypeNo ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeProductTypeNo";
		
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		try{		
		objConnection = new MySQLConnection().getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_NO + ORDER_BY_PRODUCT_TYPE);		
		 objPreparedStatement.setInt(1, Integer.parseInt(productTypeNo));		
		 ResultSet resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet, productTypeList);		
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		}
		 return productTypeList;			
	}
	
	public List<ProductTypeBO> executeProductTypeCode(String productTypeCode ){
		final String  METHOD_NAME = "executeProductTypeCode";
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		try{		
		 objConnection = new MySQLConnection().getConnection();		 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_CODE + ORDER_BY_PRODUCT_TYPE);	
		 System.out.println(" Value for Product Type is : " + productTypeCode);
		 objPreparedStatement.setString(1, productTypeCode+"%");
		 ResultSet resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet, productTypeList);		 		
		}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		}
		 catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		}
		 return productTypeList;			
	}*/
	
	private Integer getMaxProductNoForBillSequence(Integer billSequence){
		final String  METHOD_NAME = "getMaxProductNoForBillSequence";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		int maxProductNoForBillSequence =0;

				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
					objPreparedStatement = objConnection.prepareStatement(SELECT_MAX_PRODUCT_NO_FOR_BILL_SEQUENCE);	
					objPreparedStatement.setInt(1, billSequence);
					resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			maxProductNoForBillSequence = resultSet.getInt(1);			
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
		 //System.out.println("Next Sequence for Product Type : " + maxProductNoForBillSequence);
		 return (maxProductNoForBillSequence+1);			
	}
//	private populateProductTypeBO(){
		
	//}
	
	
	public void createUpdateDelete(List<BillDetailBO> billDetailList, Integer billSequence ) throws SQLException{
		
		createBillDetail(billDetailList, billSequence);
		updateBillDetail(billDetailList, billSequence);
		deleteBillDetail(billDetailList, billSequence);
	}
	

}
