package com.bank.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bank.bo.CustomerBO;
import com.bank.form.SearchMasterScreenForm;

public class CustomerHelper extends BankHelper{
	
	 //public static final int NO_OF_RECORDS = 50;
	  	    
	    List<CustomerBO> customerList;
	    
	public List<CustomerBO> getCustomerList() {
			return this.customerList;
		}

		public void setCustomerList(List<CustomerBO> customerList) {
			this.customerList = customerList;
		}

	public static List<CustomerBO> getCustomerListFromResultSet(ResultSet resultSet, List<CustomerBO> customerList) throws SQLException{
		CustomerBO customerBO;
		while (resultSet.next()){
			customerBO = getCustomerBOFromResultSet(resultSet);
			customerList.add(customerBO);
			/*System.out.println(BankConstant.RECORDS_PER_PAGE.intValue());
			System.out.println(customerList.size());*/
		
		}
		return customerList;
	}
	
	 public static CustomerBO getCustomerBOFromResultSet(ResultSet resultSet) throws SQLException{		
			CustomerBO customerBO = new CustomerBO();
			customerBO.setCustomerID(resultSet.getInt(1));
			customerBO.setName(resultSet.getString(2));
			customerBO.setAddress(resultSet.getString(3));
			customerBO.setStreet(resultSet.getString(4));
			customerBO.setArea(resultSet.getString(5));
			customerBO.setDistrict(resultSet.getString(6));
			customerBO.setState(resultSet.getString(7));
			customerBO.setCountry(resultSet.getString(8));
			customerBO.setPincode(resultSet.getInt(9));
			customerBO.setPhoto(resultSet.getString(10));
			customerBO.setPhoneNo(resultSet.getLong(11));
			customerBO.setMobileNo(resultSet.getLong(12));
			customerBO.setMailID(resultSet.getString(13));
			customerBO.setRelationShip(resultSet.getString(14));	
			customerBO.setRelationName(resultSet.getString(15));	
		return customerBO;
	}
	
	 private void navigationOfRecords(int recordsPerPage, int currentRecord,SearchMasterScreenForm searchMasterScreenForm, ResultSet resultSet, List<CustomerBO> customerList) throws SQLException{
		 CustomerBO customerBO;	   
		     int nextRecord=0;		   
		     int previousRecord=0;		   
		     boolean nextRecordExists = false;		   
		     boolean previousRecordExists = false;
		     int counter=0;
         // Set previous record details
         if(currentRecord  > recordsPerPage){
                 previousRecordExists = true;
                 previousRecord = currentRecord - recordsPerPage;
         }
         // To skip the first set of record or to avoid exception of reading rs.absolute(0)
         if(currentRecord > recordsPerPage){
        	 resultSet.absolute(currentRecord-1);
         }                        
         while (resultSet.next()){
                if(recordsPerPage == counter){
                	
                	//getCustomerListFromResultSet1(resultSet, customerList);
                	
                        // Set next record details
                        nextRecordExists = true;
                        nextRecord = resultSet.getRow();
                        break;
                }
                customerBO = getSearchCustomerBOFromResultSet(resultSet);
				customerList.add(customerBO);
                //System.out.println(rs.getRow() + " : " + rs.getString(1));
                counter++;                                
        }
         searchMasterScreenForm.setCurrentRecord(currentRecord);
         searchMasterScreenForm.setPreviousRecord(previousRecord);
         searchMasterScreenForm.setNextRecord(nextRecord);
         searchMasterScreenForm.setNextRecordExists(nextRecordExists);
         searchMasterScreenForm.setPreviousRecordExists(previousRecordExists);
        System.out.println("previousRecordExists:"+previousRecordExists);
        System.out.println("nextRecordExists:"+nextRecordExists);
        System.out.println("previousRecord:"+previousRecord);
        System.out.println("currentRecord:"+currentRecord);
        System.out.println("nextRecord:"+nextRecord);                        
}
	 
	 public List<CustomerBO> getSearchCustomerListFromResultSet(int recordsPerPage, int currentRecord,
			 SearchMasterScreenForm searchMasterScreenForm,
			 ResultSet resultSet, List<CustomerBO> customerList) throws SQLException{
			
			navigationOfRecords(recordsPerPage,currentRecord,searchMasterScreenForm,resultSet,customerList);
				
				/*System.out.println(BankConstant.RECORDS_PER_PAGE.intValue());
				System.out.println(customerList.size());*/
			
			return customerList;
		}
	 
	 public CustomerBO getSearchCustomerBOFromResultSet(ResultSet resultSet) throws SQLException{		
			CustomerBO customerBO = new CustomerBO();
			customerBO.setCustomerID(resultSet.getInt(1));
			customerBO.setName(resultSet.getString(2));
			customerBO.setAddress(resultSet.getString(3));
			customerBO.setStreet(resultSet.getString(4));
			customerBO.setArea(resultSet.getString(5));
			customerBO.setDistrict(resultSet.getString(6));
			customerBO.setState(resultSet.getString(7));
			customerBO.setCountry(resultSet.getString(8));
			customerBO.setPincode(resultSet.getInt(9));
			customerBO.setPhoto(resultSet.getString(10));
			customerBO.setPhoneNo(resultSet.getLong(11));
			customerBO.setMobileNo(resultSet.getLong(12));
			customerBO.setMailID(resultSet.getString(13));
			customerBO.setRelationShip(resultSet.getString(14));	
			customerBO.setRelationName(resultSet.getString(15));	
		return customerBO;
	}
}
