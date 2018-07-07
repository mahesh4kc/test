package com.bank.helper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.bo.BillDetailBO;
import com.bank.bo.BillHeaderBO;
import com.bank.bo.CustomerBO;
import com.bank.bo.SearchMasterScreenBO;

public class SearchMasterScreenHelper {
	public static void setSearchMasterScreenListForCustomerList( 
			List<SearchMasterScreenBO> searchMasterScreenBOList, List<CustomerBO> customerList) throws SQLException{		
		SearchMasterScreenBO searchMasterScreenBO;
		for(CustomerBO customerBO : customerList){
			searchMasterScreenBO = moveCustomerBOToSearchMasterScreenBO(customerBO);
			searchMasterScreenBOList.add(searchMasterScreenBO);
		}		
	}
	
	private static SearchMasterScreenBO moveCustomerBOToSearchMasterScreenBO(CustomerBO customerBO) throws SQLException{		
		SearchMasterScreenBO searchMasterScreenBO = new SearchMasterScreenBO();
		Map<String,String> search1 = new HashMap<String, String>();
		String customerID = customerBO.getCustomerID().toString();
		search1.put(customerID, customerID);
		searchMasterScreenBO.setSearchPK(search1);	
		searchMasterScreenBO.setSearch1(customerID);
		searchMasterScreenBO.setSearch2(customerBO.getName() + " " + customerBO.getRelationShipDetails() + " " + customerBO.getRelationName());
		searchMasterScreenBO.setSearch3(customerBO.getAddress());
		searchMasterScreenBO.setSearch4(customerBO.getStreet());
		searchMasterScreenBO.setSearch5(customerBO.getArea() + " " + customerBO.getDistrict() + " " + 
				customerBO.getPincode().toString());
		searchMasterScreenBO.setSearch6(customerBO.getMobileNo().toString());
		return searchMasterScreenBO;
	}
	
	public static void setSearchMasterScreenListForBillList( 
			List<SearchMasterScreenBO> searchMasterScreenBOList, List<BillHeaderBO> billHeaderList,
			List<BillDetailBO> billDetailList, List<CustomerBO> customerBOList) throws SQLException{		
		SearchMasterScreenBO searchMasterScreenBO;
		for(BillHeaderBO billHeaderBO : billHeaderList){
			searchMasterScreenBO = moveBillBOToSearchMasterScreenBO(billHeaderBO,billDetailList,
					customerBOList);
			searchMasterScreenBO.setBillHeaderBO(billHeaderBO);
			searchMasterScreenBOList.add(searchMasterScreenBO);
		}		
	}
	
	private static SearchMasterScreenBO moveBillBOToSearchMasterScreenBO(BillHeaderBO billHeaderBO, 
			List<BillDetailBO> billDetailList, List<CustomerBO> customerList) throws SQLException{		
		SearchMasterScreenBO searchMasterScreenBO = new SearchMasterScreenBO();
		Map<String,String> searchPK = new HashMap<String, String>();
		Integer billSequence = billHeaderBO.getBillSequence();
		searchPK.put(billSequence.toString(), billSequence.toString());
		searchMasterScreenBO.setSearchPK(searchPK);
		searchMasterScreenBO.setSearchPrimaryKey(billSequence.toString());
		searchMasterScreenBO.setSearch1(billHeaderBO.getBillSerial() + ":"+ billHeaderBO.getBillNumber());
		searchMasterScreenBO.setSearch2(billHeaderBO.getBillDate());
		for(CustomerBO customerBO : customerList){
			if(customerBO.getCustomerID().intValue() == billHeaderBO.getCustomerID().intValue() ){
				//searchMasterScreenBO.setSearch3(customerBO.getName() + " ("+billHeaderBO.getCustomerID()+")");
				searchMasterScreenBO.setCustomerBO(customerBO);	//set the customer object
				searchMasterScreenBO.setSearch3(
					customerBO.getName() + 
					(
						customerBO.getRelationShipDetails() != null && customerBO.getRelationShipDetails().length() > 0 
						? " [" + " " + customerBO.getRelationShipDetails()+  " " + customerBO.getRelationName() 
						+ " ]" : ""
					)						
						) ;
				searchMasterScreenBO.setSearch4(
						customerBO.getAddress() + " " + customerBO.getStreet()+ " " + customerBO.getArea()
						+ " " + customerBO.getPincode());
				break;
			}
			
		}			
		searchMasterScreenBO.setSearch5(billHeaderBO.getAmount().toString());
		searchMasterScreenBO.setSearch6(getProductDescription(billSequence, billDetailList) + " ( "+
										(billHeaderBO.getStatusDescription()+ " ) "));	
		searchMasterScreenBO.setSearch7(billHeaderBO.getRedemptionDate() != null && 
				billHeaderBO.getRedemptionDate().length() > 0 ? billHeaderBO.getRedemptionDate() : ""); 
		searchMasterScreenBO.setSearch8(
				( billHeaderBO.getRedemptionDate() != null && billHeaderBO.getRedemptionDate().length() > 0 ?  
					billHeaderBO.getBillRedemSerial()+":"+billHeaderBO.getBillRedemNumber() : "" ) 
				);
		searchMasterScreenBO.setSearch9(billHeaderBO.getGrams().toString());
		searchMasterScreenBO.setSearch10(billHeaderBO.getPresentValue().toString());
		return searchMasterScreenBO;
	}
	
	private static String getProductDescription(Integer billSequence, List<BillDetailBO> billDetailList){
		StringBuffer productDescription = new StringBuffer("");		
		for (BillDetailBO billDetailBO : billDetailList){
			if(billSequence.intValue() == billDetailBO.getBillSequence().intValue()){
				productDescription.append(billDetailBO.getProductDescription());
				productDescription.append(" ; ");
			}			
		}
		return productDescription.toString();
	}
}
