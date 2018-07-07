package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.ProductTypeBO;

public class ProductTypeDAO extends BankDAO{
	public static final String CLASS_NAME = "ProductTypeDatabase";
		
	public static final String CREATE_PRODUCT_TYPE = "INSERT INTO PRODUCT_TYPE (PRODUCT_TYPE_NO, " +
			"PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESCRIPTION, RATE_OF_INTEREST) VALUES (?,?,?,?)";
	public static final String UPDATE_PRODUCT_TYPE = "UPDATE PRODUCT_TYPE SET PRODUCT_TYPE_CODE = ?, " +
	" PRODUCT_TYPE_DESCRIPTION = ?, RATE_OF_INTEREST =? WHERE PRODUCT_TYPE_NO = ?";
	public static final String DELETE_PRODUCT_TYPE = "DELETE FROM PRODUCT_TYPE WHERE PRODUCT_TYPE_NO = ? ";
	public static final String SELECT_ALL_PRODUCT_TYPE = "SELECT PRODUCT_TYPE_NO, " +
	"PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESCRIPTION, RATE_OF_INTEREST FROM PRODUCT_TYPE  ";
	public static final String SELECT_PRODUCT_TYPE_NO = "SELECT PRODUCT_TYPE_NO, " +
			"PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESCRIPTION, RATE_OF_INTEREST FROM PRODUCT_TYPE WHERE " +
			"PRODUCT_TYPE_NO = ? ";
	public static final String SELECT_PRODUCT_TYPE_CODE = "SELECT PRODUCT_TYPE_NO, " +
			"PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESCRIPTION, RATE_OF_INTEREST FROM PRODUCT_TYPE WHERE " +
	"PRODUCT_TYPE_CODE LIKE ? ";
	public static final String SELECT_PRODUCT_TYPE_CODE_EQUAL = "SELECT PRODUCT_TYPE_NO, " +
	"PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESCRIPTION, RATE_OF_INTEREST FROM PRODUCT_TYPE WHERE " +
"PRODUCT_TYPE_CODE = ? ";
	public static final String SELECT_PRODUCT_TYPE_MAX_NO = "SELECT MAX(PRODUCT_TYPE_NO) FROM PRODUCT_TYPE";
	public static final String ORDER_BY_PRODUCT_TYPE = " ORDER BY PRODUCT_TYPE_CODE,PRODUCT_TYPE_NO";
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
	public ProductTypeDAO(String jndiName) {
		super();
		this.jndiName = jndiName;
		//objConnection = getConnection(jndiName);
	}
	
	private void createProductType(List<ProductTypeBO> productTypeList ) throws SQLException{
		//final String  METHOD_NAME = "createProductType";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		
		int noOfInsertedRecords[];
		int productTypeSequence =0;
		
		try{ 
		 System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 objPreparedStatement = objConnection.prepareStatement(CREATE_PRODUCT_TYPE);
		 productTypeSequence = getMaxProductTypeNo();
		 for(ProductTypeBO productTypeBO: productTypeList){
			 if(productTypeBO != null && productTypeBO.getProductTypeNo() != null && productTypeBO.getProductTypeNo().intValue() == 0
					 && productTypeBO.getProductTypeCode().length() > 0){				 
				 //System.out.println(" Next Sequence is : " + productTypeSequence);
				 objPreparedStatement.setInt(1, new Integer(productTypeSequence));
				 objPreparedStatement.setString(2, productTypeBO.getProductTypeCode().toUpperCase());
				 objPreparedStatement.setString(3, productTypeBO.getProductTypeDescription().toUpperCase());
				 objPreparedStatement.setDouble(4, productTypeBO.getProductTypeRateOfInterest());
				 objPreparedStatement.addBatch(); 
				 productTypeSequence = productTypeSequence + 1;
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
		 /*objPreparedStatement = objConnection.prepareStatement(CREATE_PRODUCT_TYPE);
		 productTypeSequence = getMaxProductTypeNo();System.out.println(" Next Sequence is : " + productTypeSequence);
		 objPreparedStatement.setInt(1, new Integer(productTypeSequence));
		 objPreparedStatement.setString(2, objProductTypeBO.getProductTypeCode().toUpperCase());
		 objPreparedStatement.setString(3, objProductTypeBO.getProductTypeDescription().toUpperCase());
		 objPreparedStatement.setDouble(4, objProductTypeBO.getProductTypeRateOfInterest());
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 System.out.println(noOfInsertedRecords + " Record inserted");
		}*/
		
		// return objProductTypeBO;
			
	}
	private void updateProductType(List<ProductTypeBO> productTypeList){
		final String  METHOD_NAME = "updateProductType";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
				
		int noOfInsertedRecords[];		
		try{		
		// objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());		 
		 
		// objPreparedStatement = objConnection.prepareStatement();	
		 //conn.setAutoCommit(false);
	    //  String query = "insert into add_batch_table(stringCol, intCol) values(?, ?)";
	    
	   /*   pstmt.setString(1, "1");
	      pstmt.setInt(2, 100);
	      pstmt.addBatch();
*/
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_PRODUCT_TYPE);
		 for(ProductTypeBO productTypeBO: productTypeList){
			 if(productTypeBO!= null && !productTypeBO.getChecked() && productTypeBO.getProductTypeCode().length() > 0){
				 objPreparedStatement.setString(1, productTypeBO.getProductTypeCode().toUpperCase());
				 objPreparedStatement.setString(2, productTypeBO.getProductTypeDescription().toUpperCase());
				 objPreparedStatement.setDouble(3, productTypeBO.getProductTypeRateOfInterest());
				 objPreparedStatement.setInt(4,  productTypeBO.getProductTypeNo());
				 objPreparedStatement.addBatch();
			 }			
		 }
		 
		 noOfInsertedRecords = objPreparedStatement.executeBatch();	 
		}
		// noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		
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
		// return objProductTypeBO;
			
	}
	
	private void deleteProductType(List<ProductTypeBO> productTypeList ) {
		final String  METHOD_NAME = "deleteProductType";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
			
		int noOfInsertedRecords[];
		List<ProductTypeBO> dbProductTypeList = null;
		//ProductTypeDAO productTypeDAO = new ProductTypeDAO();
		StringBuffer deleteProductTypeNo = new StringBuffer();
		boolean productExists = false;		
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		 //dbProductTypeList = productTypeDAO.executeAllProductType(); 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_PRODUCT_TYPE);
		 //System.out.println("dbSize : "+dbProductTypeList.size() + "Size : "+productTypeList.size());
		 
		 for(ProductTypeBO productTypeBO: productTypeList){
			 if(productTypeBO != null && productTypeBO.getChecked()){
				 objPreparedStatement.setInt(1, productTypeBO.getProductTypeNo());
				 //System.out.println("cahrs 1"+ DELETE_PRODUCT_TYPE + productTypeBO.getProductTypeNo());
				 objPreparedStatement.addBatch();
				 break;
			 }
	 }
		 for(ProductTypeBO dbProductTypeBO: dbProductTypeList){
			 productExists = false;
			// System.out.println("productTypeBO"+productTypeBO.getProductTypeCode());
			 for(ProductTypeBO productTypeBO: productTypeList){
				 if(productTypeBO != null && productTypeBO.getProductTypeNo().intValue() == 0
						 && productTypeBO.getProductTypeCode().length() > 0){
					 productExists = true;					
					 break;
				 } else if(productTypeBO != null && dbProductTypeBO.getProductTypeNo().intValue() == productTypeBO.getProductTypeNo().intValue()){
					 productExists = true;					
					 break;
				 } 
			 }
			 if(!productExists ){
				 objPreparedStatement.setInt(1, dbProductTypeBO.getProductTypeNo());
				 //System.out.println("cahrs 1"+ DELETE_PRODUCT_TYPE + dbProductTypeBO.getProductTypeNo());
				 objPreparedStatement.addBatch();
				 //deleteProductTypeNo.append("'" + dbProductTypeBO.getProductTypeNo() + "',");
			 }
			 			
		 }
		/* if(deleteProductTypeNo.length() < 1){
			 objPreparedStatement.setString(1, "''");
			 System.out.println("cahrs "+ DELETE_PRODUCT_TYPE + "''");
		 }else{
			 objPreparedStatement.setString(1,deleteProductTypeNo.substring(0, deleteProductTypeNo.length()-1));
			 System.out.println("cahrs "+ DELETE_PRODUCT_TYPE + deleteProductTypeNo.substring(0, deleteProductTypeNo.length()-1));
		 }*/
		 
		 //noOfInsertedRecords = objPreparedStatement.executeUpdate();
		 noOfInsertedRecords = objPreparedStatement.executeBatch();
		
		 //System.out.println("no Of deleted Records "+ noOfInsertedRecords);
		/* objPreparedStatement = objConnection.prepareStatement(DELETE_PRODUCT_TYPE);		 
		 objPreparedStatement.setString(1, objProductTypeBO.getProductTypeCode().toUpperCase());
		// objPreparedStatement.setString(2, objProductTypeBO.getProductTypeDescription().toUpperCase());
		 //objPreparedStatement.setDouble(3, objProductTypeBO.getProductTypeRateOfInterest());
		 //objPreparedStatement.setInt(4,  objProductTypeBO.getProductTypeNo());
		 noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		 System.out.println(noOfInsertedRecords + " Record updated");
		*/}
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
		} catch(Exception ex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + ex.getMessage());
		}
		finally{
			 try {
				objConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objPreparedStatement = null;
			objConnection = null;
		}
		
		
		// return noOfInsertedRecords;
			
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
	
	public List<ProductTypeBO> executeAllProductType() throws SQLException,Exception{
		//final String  METHOD_NAME = "executeProductTypeNo";
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		//ProductTypeBO objProductTypeBO;
		// objConnection = getConnection();		 
		 //System.out.println("Connection is Alive :executeAllProductType " + !objConnection.isClosed());
		try{		
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PRODUCT_TYPE + ORDER_BY_PRODUCT_TYPE);	
		  resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet,productTypeList);
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
		 return productTypeList;			
	}
	
	public List<ProductTypeBO> executeByProductTypeNoAndCode(String productTypeNo,String productTypeCode) throws SQLException{
		//final String  METHOD_NAME = "executeProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		try{
		StringBuffer queryByProductNoAndCode = new StringBuffer(SELECT_ALL_PRODUCT_TYPE);
		queryByProductNoAndCode.append(" WHERE PRODUCT_TYPE_NO = ? AND PRODUCT_TYPE_CODE LIKE ? " + ORDER_BY_PRODUCT_TYPE);
				
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(queryByProductNoAndCode.toString());	
		objPreparedStatement.setInt(1, Integer.parseInt(productTypeNo));
		objPreparedStatement.setString(2, productTypeCode+"%");
		
		 resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet,productTypeList);
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
		 return productTypeList;			
	}
	
	public List<ProductTypeBO> executeProductTypeNo(String productTypeNo ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeProductTypeNo";
		
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		try{		
		//objConnection = getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_NO + ORDER_BY_PRODUCT_TYPE);		
		 objPreparedStatement.setInt(1, Integer.parseInt(productTypeNo));		
		 resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet, productTypeList);		
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
		 return productTypeList;			
	}
	
	public List<ProductTypeBO> executeProductTypeCode(String productTypeCode ){
		final String  METHOD_NAME = "executeProductTypeCode";
		List<ProductTypeBO> productTypeList = new ArrayList<ProductTypeBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
		objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_CODE + ORDER_BY_PRODUCT_TYPE);	
		 //System.out.println(" Value for Product Type is : " + productTypeCode);
		 objPreparedStatement.setString(1, productTypeCode+"%");
		  resultSet = objPreparedStatement.executeQuery();
		 setProductTypeListFromResultSet(resultSet, productTypeList);		 		
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
		 return productTypeList;			
	}
	
	public Integer executeProductTypeCodeReturnProductNo(String productTypeCode ){
		final String  METHOD_NAME = "executeProductTypeCodeReturnProductNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println(SELECT_PRODUCT_TYPE_CODE_EQUAL + ORDER_BY_PRODUCT_TYPE);
		objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_CODE_EQUAL + ORDER_BY_PRODUCT_TYPE);	
		 //System.out.println(" Value for Product Type is : " + productTypeCode);
		 objPreparedStatement.setString(1, productTypeCode);
		  resultSet = objPreparedStatement.executeQuery();
			while(resultSet.next()){
				return Integer.parseInt(resultSet.getString(1));
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
		 return 0;			
	}
	private Integer getMaxProductTypeNo(){
		final String  METHOD_NAME = "getMaxProductTypeNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		int maxProductType =0;
		try{		
		// objConnection = getConnection();		 
		 //System.out.println("Connection is Alive : " + !objConnection.isClosed());
			objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_TYPE_MAX_NO);				
			resultSet = objPreparedStatement.executeQuery();
		while (resultSet.next()){
			maxProductType = resultSet.getInt(1);			
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
		 //System.out.println("Next Sequence for Product Type : " + maxProductType);
		 return (maxProductType+1);			
	}
//	private populateProductTypeBO(){
		
	//}
	
	private void setProductTypeListFromResultSet(ResultSet resultSet, List<ProductTypeBO> productTypeList) throws SQLException{
		ProductTypeBO productTypeBO;
		while (resultSet.next()){
			productTypeBO = getProductTypeBOFromResultSet(resultSet);
			productTypeList.add(productTypeBO);
		}
	}
	
	private ProductTypeBO getProductTypeBOFromResultSet(ResultSet resultSet) throws SQLException{
			ProductTypeBO productTypeBO = new ProductTypeBO();		
			productTypeBO.setProductTypeNo(resultSet.getInt(1));
			productTypeBO.setProductTypeCode(resultSet.getString(2));
			productTypeBO.setProductTypeDescription(resultSet.getString(3));
			productTypeBO.setProductTypeRateOfInterest(resultSet.getDouble(4));				
		return productTypeBO;
	}
	
	public void createUpdateDelete(List<ProductTypeBO> productTypeList ) throws SQLException{
		createProductType(productTypeList);
		updateProductType(productTypeList);
		deleteProductType(productTypeList);
	}
	
	public boolean productTypeExists(String productTypeNo) throws SQLException,Exception{
		boolean productType = false;
		List<ProductTypeBO> ProductTypeBOList=null;
		try{
		ProductTypeBOList = executeProductTypeNo(productTypeNo);
		if (ProductTypeBOList != null && ProductTypeBOList.size() > 0){
			productType = true;
		}
	}finally{
		
	}
		return productType;
	}
}
