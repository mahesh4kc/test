package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.ProductDescriptionBO;
import com.bank.util.FilterClausesConstant;

public class ProductDescriptionDAO extends BankDAO{
	public static final String CLASS_NAME = "ProductDescriptionDAO";
		
	public static final String CREATE_PRODUCT_DESC = "INSERT INTO PRODUCT_DESC (PRODUCT_SEQ, " +
			"PRODUCT_DESCRIPTION) VALUES (?,?)";
	
	public static final String UPDATE_PRODUCT_DESC = "UPDATE PRODUCT_DESC SET " +
			"PRODUCT_DESCRIPTION = ? WHERE PRODUCT_SEQ = ? ";
	
	public static final String DELETE_PRODUCT_DESC = "DELETE FROM PRODUCT_DESC WHERE PRODUCT_SEQ = ? ";
	
	public static final String SELECT_ALL_PRODUCT_DESC = "SELECT PRODUCT_SEQ, " +
														"PRODUCT_DESCRIPTION FROM PRODUCT_DESC  ";
	
	public static final String WHERE_PRODUCT_DESC_LIKE = "WHERE PRODUCT_DESCRIPTION " + FilterClausesConstant.LIKE + FilterClausesConstant.QUESTIONAIRE;

	public static final String SELECT_PRODUCT_DESC_MAX_NO = "SELECT MAX(PRODUCT_SEQ) FROM PRODUCT_DESC";
	
	public static final String ORDER_BY_PRODUCT_DESC = " ORDER BY PRODUCT_DESCRIPTION";
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
	public ProductDescriptionDAO(String jndiName) {
		super();
		this.jndiName = jndiName;
		//objConnection = getConnection(jndiName);
	}
	
	private void createProductDescription(List<ProductDescriptionBO> productDescriptionList ) throws SQLException{
		//final String  METHOD_NAME = "createProductDescription";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
				
		int noOfInsertedRecords[];
		int productSequence =0;
		try {
		 //System.out.println("CREATE_PRODUCT_DESC " + CREATE_PRODUCT_DESC);
		 objPreparedStatement = objConnection.prepareStatement(CREATE_PRODUCT_DESC);
		 productSequence = getMaxProductSequenceNo();
		 for(ProductDescriptionBO productDescriptionBO: productDescriptionList){
			 if(productDescriptionBO != null && productDescriptionBO.getProductNo() != null 
					 && productDescriptionBO.getProductNo().intValue() == 0){
					 //&& productDescriptionBO.getProductCode().length() > 0){				 
				 //System.out.println(" Next Sequence is : " + productSequence);
				 objPreparedStatement.setInt(1, new Integer(productSequence));
				 //objPreparedStatement.setString(2, productDescriptionBO.getProductCode().toUpperCase());
				 objPreparedStatement.setString(2, productDescriptionBO.getProductDescription().toUpperCase());				
				 objPreparedStatement.addBatch(); 
				 productSequence = productSequence + 1;
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
		// return objProductTypeBO;
			
	}
	private void updateProductDescription(List<ProductDescriptionBO> productDescriptionList ){
		final String  METHOD_NAME = "updateProduct";
		int noOfInsertedRecords[];
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		try{		
		// objConnection = getConnection(this.jndiName);		 
		 //System.out.println("UPDATE_PRODUCT_DESC : " + UPDATE_PRODUCT_DESC);		 
		 
		// objPreparedStatement = objConnection.prepareStatement();	
		 //conn.setAutoCommit(false);
	    //  String query = "insert into add_batch_table(stringCol, intCol) values(?, ?)";
	    
	   /*   pstmt.setString(1, "1");
	      pstmt.setInt(2, 100);
	      pstmt.addBatch();
*/
		 objPreparedStatement = objConnection.prepareStatement(UPDATE_PRODUCT_DESC);
		 for(ProductDescriptionBO productDescriptionBO : productDescriptionList){
			 if(productDescriptionBO!= null && !productDescriptionBO.getChecked()){ 
					 //&& productDescriptionBO.getProductCode().length() > 0){
				// objPreparedStatement.setString(1, productDescriptionBO.getProductCode().toUpperCase());
				 objPreparedStatement.setString(1, productDescriptionBO.getProductDescription().toUpperCase());
				 objPreparedStatement.setInt(2,  productDescriptionBO.getProductNo());
				/* System.out.println(UPDATE_PRODUCT_DESC + 
						 productDescriptionBO.getProductDescription().toUpperCase() + ":" +  
						 productDescriptionBO.getProductNo());*/
				 objPreparedStatement.addBatch();
			 }			
		 }
		 
		 noOfInsertedRecords = objPreparedStatement.executeBatch();	 
		}
		// noOfInsertedRecords = objPreparedStatement.executeUpdate();	
		
		 catch(SQLException sqlex){
			 System.out.println("Class Name : " + CLASS_NAME + "Method Name : " + 
						METHOD_NAME + sqlex.getMessage());
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
		// return objProductTypeBO;
			
	}
	
	private void deleteProductDescription(List<ProductDescriptionBO> productDescriptionList ) {
		final String  METHOD_NAME = "deleteProductDescription";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		
		int noOfInsertedRecords[];
		List<ProductDescriptionBO> dbProductDescriptionList = null;
		
		//ProductDAO productTypeDAO = new ProductDAO();
		//StringBuffer deleteProductTypeNo = new StringBuffer();
		boolean productDescriptionExists = false;
		try{		
		 //objConnection = getConnection();		 
		 //System.out.println(" deleteProductDescription : " + DELETE_PRODUCT_DESC);
		 dbProductDescriptionList = executeAllProducts(); 
		 objPreparedStatement = objConnection.prepareStatement(DELETE_PRODUCT_DESC);
		 //System.out.println("dbSize : "+ dbProductDescriptionList.size() + "Size : "+productDescriptionList.size());
		 
		 for(ProductDescriptionBO productDescriptionBO: productDescriptionList){
			 //select the records which will have product no && also checked 
			 if(productDescriptionBO != null && productDescriptionBO.getChecked() && productDescriptionBO.getProductNo() != 0){
				 objPreparedStatement.setInt(1, productDescriptionBO.getProductNo());
				 //System.out.println( DELETE_PRODUCT_DESC + productDescriptionBO.getProductNo());
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
		// return noOfInsertedRecords;
			
	}

	
	public List<ProductDescriptionBO> executeAllProducts() throws SQLException,Exception{
		final String  METHOD_NAME = "executeAllProducts";
		 System.out.println(METHOD_NAME+" starts ");
		 Connection objConnection = getConnection(this.jndiName);
		 PreparedStatement objPreparedStatement = null;
		 ResultSet resultSet = null;
		 
		 List<ProductDescriptionBO> productDescriptionList = new ArrayList<ProductDescriptionBO>();

				try{
		//ProductTypeBO objProductTypeBO;
		 //objConnection = getConnection();		 
		 //System.out.println("SELECT_ALL_PRODUCT_DESC : " + SELECT_ALL_PRODUCT_DESC + ORDER_BY_PRODUCT_DESC);
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PRODUCT_DESC + ORDER_BY_PRODUCT_DESC);		 
		 resultSet = objPreparedStatement.executeQuery();
		 setProductListFromResultSet(resultSet,productDescriptionList);		
		 System.out.println(METHOD_NAME+" ends "); 
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
				return productDescriptionList;			
	}
	
	public List<ProductDescriptionBO> executeProductNo(String productNo ) 
	throws SQLException,Exception{
		final String  METHOD_NAME = "executeProductNo";
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
		List<ProductDescriptionBO> productDescriptionList = new ArrayList<ProductDescriptionBO>();
		
		try{		
		//objConnection = getConnection();		
		objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PRODUCT_DESC +
				" WHERE PRODUCT_SEQ = ? " + ORDER_BY_PRODUCT_DESC);		
		objPreparedStatement.setInt(1, Integer.parseInt(productNo));		
		  resultSet = objPreparedStatement.executeQuery();
		 setProductListFromResultSet(resultSet, productDescriptionList);		
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
			objPreparedStatement = null;
			objConnection = null;
		}
		 return productDescriptionList;			
	}
	
	public List<ProductDescriptionBO> executeProductDescriptionLike(String productDescription ){
		final String  METHOD_NAME = "executeProductDescription";
		List<ProductDescriptionBO> productDescriptionList = new ArrayList<ProductDescriptionBO>();
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
		
				try{		
		 //objConnection = getConnection();		 
		 //System.out.println("Query for executeProductDescription : " + SELECT_ALL_PRODUCT_DESC +
				// WHERE_PRODUCT_DESC_LIKE + ORDER_BY_PRODUCT_DESC);
		 objPreparedStatement = objConnection.prepareStatement(SELECT_ALL_PRODUCT_DESC +
				 WHERE_PRODUCT_DESC_LIKE + ORDER_BY_PRODUCT_DESC);			
		 //System.out.println(" Value for Product id is : " + productDescription);
		 objPreparedStatement.setString(1, productDescription.toUpperCase()+"%");
		 resultSet = objPreparedStatement.executeQuery();
		 setProductListFromResultSet(resultSet, productDescriptionList);		 		
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
			objPreparedStatement = null;
			objConnection = null;
		}
		 return productDescriptionList;			
	}
	
	private Integer getMaxProductSequenceNo(){
		final String  METHOD_NAME = "getMaxProductSequenceNo";
		int maxProduct =0;
		Connection objConnection = getConnection(this.jndiName);
		PreparedStatement objPreparedStatement = null;
		ResultSet resultSet = null;
				try{		
		// objConnection = getConnection();		 
		 //System.out.println("SELECT_PRODUCT_DESC_MAX_NO : " + SELECT_PRODUCT_DESC_MAX_NO);
					objPreparedStatement = objConnection.prepareStatement(SELECT_PRODUCT_DESC_MAX_NO);				
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
			objPreparedStatement = null;
			objConnection = null;
		}
		 //System.out.println("Next Sequence for Product : " + maxProduct);
		 return (maxProduct+1);			
	}
//	private populateProductTypeBO(){
		
	//}
	
	private void setProductListFromResultSet(ResultSet resultSet, List<ProductDescriptionBO> productDescriptionList) throws SQLException{
		ProductDescriptionBO productDescriptionBO;
		while (resultSet.next()){
			productDescriptionBO = getProductBOFromResultSet(resultSet);
			productDescriptionList.add(productDescriptionBO);
		}
	}
	
	private ProductDescriptionBO getProductBOFromResultSet(ResultSet resultSet) throws SQLException{
		ProductDescriptionBO productDescriptionBO = new ProductDescriptionBO();		
		productDescriptionBO.setProductNo(resultSet.getInt(1));
		//productDescriptionBO.setProductCode(resultSet.getString(2));
		productDescriptionBO.setProductDescription(resultSet.getString(2));			
		return productDescriptionBO;
	}
	
	public void createUpdateDelete(List<ProductDescriptionBO> productDescriptionList) throws SQLException{
		createProductDescription(productDescriptionList);
		updateProductDescription(productDescriptionList);
		deleteProductDescription(productDescriptionList);
	}

	
	
	
}
