package com.bank.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.BillHeaderBO;
import com.bank.dao.ProductTypeDAO;
import com.bank.form.BillForm;
import com.bank.form.SearchMasterScreenForm;
import com.bank.util.DateUtil;

public class BillHeaderHelper {

	// public static final int NO_OF_RECORDS = 50;
	 
	public BillHeaderHelper(ParameterHelper parameterHelper){
		setParameterHelper(parameterHelper);
	}
	
	private ParameterHelper parameterHelper;
	public List<BillHeaderBO> billHeaderList;
	
	public ParameterHelper getParameterHelper() {
		return this.parameterHelper;
	}

	public void setParameterHelper(ParameterHelper parameterHelper) {
		this.parameterHelper = parameterHelper;
	}
	
	
	public List<BillHeaderBO> getBillHeaderList() {
		return this.billHeaderList;
	}

	public void setBillHeaderList(List<BillHeaderBO> billHeaderList) {
		this.billHeaderList = billHeaderList;
	}

	public static BillHeaderBO getBillHeaderBOFromResultSet(ResultSet resultSet) throws SQLException{
		BillHeaderBO billHeaderBO = null;
		
			billHeaderBO = new BillHeaderBO();	
			billHeaderBO.setBillSequence(resultSet.getInt(1));
			billHeaderBO.setBillSerial(resultSet.getString(2));
			billHeaderBO.setBillNumber(resultSet.getInt(3));
			billHeaderBO.setBillDate(resultSet.getString(4));
			billHeaderBO.setCustomerID(resultSet.getInt(5));
			billHeaderBO.setCareOf(resultSet.getString(6));
			billHeaderBO.setProductTypeNumber(resultSet.getInt(7));			
			billHeaderBO.setRateOfInterest(resultSet.getDouble(8));
			billHeaderBO.setAmount(resultSet.getInt(9));
			billHeaderBO.setAmountInWords(resultSet.getString(10));
			billHeaderBO.setPresentValue(resultSet.getInt(11));
			billHeaderBO.setGrams(resultSet.getDouble(12));
			billHeaderBO.setMonthlyIncome(resultSet.getInt(13));
			billHeaderBO.setRedemptionDate(resultSet.getString(14));
			billHeaderBO.setRedemptionInterest(resultSet.getDouble(15));
			billHeaderBO.setRedemptionTotal(resultSet.getDouble(16));
			billHeaderBO.setRedemptionStatus(resultSet.getString(17));
			billHeaderBO.setBillRedemSerial(resultSet.getString(18));
			billHeaderBO.setBillRedemNumber(resultSet.getInt(19));
		return billHeaderBO;			
		
	}
	
	public static BillHeaderBO getSearchBillHeaderBOFromResultSet(ResultSet resultSet) throws SQLException{
		BillHeaderBO billHeaderBO = null;
		
			billHeaderBO = new BillHeaderBO();	
			billHeaderBO.setBillSequence(resultSet.getInt(1));
			billHeaderBO.setBillSerial(resultSet.getString(2));
			billHeaderBO.setBillNumber(resultSet.getInt(3));
			billHeaderBO.setBillDate(resultSet.getString(4));
			billHeaderBO.setCustomerID(resultSet.getInt(5));
			billHeaderBO.setCareOf(resultSet.getString(6));
			billHeaderBO.setProductTypeNumber(resultSet.getInt(7));			
			billHeaderBO.setRateOfInterest(resultSet.getDouble(8));
			billHeaderBO.setAmount(resultSet.getInt(9));
			billHeaderBO.setAmountInWords(resultSet.getString(10));
			billHeaderBO.setPresentValue(resultSet.getInt(11));
			billHeaderBO.setGrams(resultSet.getDouble(12));
			billHeaderBO.setMonthlyIncome(resultSet.getInt(13));
			billHeaderBO.setRedemptionDate(resultSet.getString(14));
			billHeaderBO.setRedemptionInterest(resultSet.getDouble(15));
			billHeaderBO.setRedemptionTotal(resultSet.getDouble(16));
			billHeaderBO.setRedemptionStatus(resultSet.getString(17));
			billHeaderBO.setBillRedemSerial(resultSet.getString(18));
			billHeaderBO.setBillRedemNumber(resultSet.getInt(19));
		return billHeaderBO;			
		
	}
	
	public List<BillHeaderBO> getBillHeaderBOListFromResultSet(ResultSet resultSet) throws SQLException{
		BillHeaderBO billHeaderBO = null;
		this.billHeaderList = new ArrayList<BillHeaderBO>();
		while (resultSet.next()){
			billHeaderBO = getBillHeaderBOFromResultSet(resultSet);
			this.billHeaderList.add(billHeaderBO);
			/*if(this.billHeaderList.size() > BankConstant.RECORDS_PER_PAGE.intValue()){
				break;
			}*/
		}
		return this.billHeaderList;				
	}	
	
	private void navigationOfRecords(ParameterHelper parameters,int currentRecord,SearchMasterScreenForm searchMasterScreenForm, 
			ResultSet resultSet, List<BillHeaderBO> billHeaderList) throws SQLException{
		BillHeaderBO billHeaderBO;   
		     int nextRecord=0;		   
		     int previousRecord=0;		   
		     boolean nextRecordExists = false;		   
		     boolean previousRecordExists = false;
		     int counter=0;
		     int recordsPerPage=parameters.getRecordsPerPage();
		     
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
               billHeaderBO = getSearchBillHeaderBOFromResultSet(resultSet);
               billHeaderList.add(billHeaderBO);
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
	
	public List<BillHeaderBO> getSearchBillHeaderBOListFromResultSet(ParameterHelper parameters, int currentRecord,
			SearchMasterScreenForm searchMasterScreenForm,ResultSet resultSet) throws SQLException{
		BillHeaderBO billHeaderBO = null;
		this.billHeaderList = new ArrayList<BillHeaderBO>();
		navigationOfRecords(parameters,currentRecord,searchMasterScreenForm,resultSet,this.billHeaderList);
		return this.billHeaderList;				
	}	
	
/*	private void moveResultSetToBillHeaderBO(ResultSet resultSet,BillHeaderBO billHeaderBO){
		billHeaderBO = new BillHeaderBO();	
		billHeaderBO.setBillSequence(resultSet.getInt(1));
		billHeaderBO.setBillSerial(resultSet.getString(2));
		billHeaderBO.setBillNumber(resultSet.getInt(3));
		billHeaderBO.setBillDate(resultSet.getString(4));
		billHeaderBO.setCustomerID(resultSet.getInt(5));
		billHeaderBO.setCareOf(resultSet.getString(6));
		billHeaderBO.setProductTypeNumber(resultSet.getInt(7));			
		billHeaderBO.setRateOfInterest(resultSet.getDouble(8));
		billHeaderBO.setAmount(resultSet.getInt(9));
		billHeaderBO.setAmountInWords(resultSet.getString(10));
		billHeaderBO.setPresentValue(resultSet.getInt(11));
		billHeaderBO.setGrams(resultSet.getDouble(12));
		billHeaderBO.setMonthlyIncome(resultSet.getInt(13));
		billHeaderBO.setRedemptionDate(resultSet.getString(14));
		billHeaderBO.setRedemptionInterest(resultSet.getDouble(15));
		billHeaderBO.setRedemptionTotal(resultSet.getDouble(16));
		billHeaderBO.setRedemptionStatus(resultSet.getString(17));
	}*/
	public void moveBillHeaderToBillForm(BillHeaderBO billHeaderBO, BillForm billForm){
		billForm.setBillHeaderBO(billHeaderBO);		
	}
	public  void clearBillHeadBillForm(BillForm billForm,String jndiName){
		clearBillHeadBillFormKey(billForm);
		clearBillHeadBillFormOtherThanBillKey(billForm,jndiName);
		clearBillHeadBillFormForBillRedemption(billForm);
	}
	public static void clearBillHeadBillFormKey(BillForm billForm){
		billForm.getBillHeaderBO().setBillSerial("");
		billForm.getBillHeaderBO().setBillNumber(0);		
	}
	public  void clearBillHeadBillFormOtherThanBillKey(BillForm billForm,String jndiName){		
		billForm.getBillHeaderBO().setBillDate(
				getParameterHelper().getCurrentDate() != null && getParameterHelper().getCurrentDate().length()>0?						
						getParameterHelper().getCurrentDate():DateUtil.getTodaysDate());
		billForm.getBillHeaderBO().setCustomerID(0);		
		billForm.setCustomerName("");
		billForm.setCustomerAddress("");
		billForm.getBillHeaderBO().setCareOf("");
		billForm.getBillHeaderBO().setProductTypeNumber(new ProductTypeDAO(jndiName).executeProductTypeCodeReturnProductNo("GOLD"));
		billForm.getBillHeaderBO().setAmount(0);
		billForm.getBillHeaderBO().setAmountInWords("");
		billForm.getBillHeaderBO().setGrams(0.0);
		billForm.getBillHeaderBO().setPresentValue(0);
		billForm.getBillHeaderBO().setMonthlyIncome(getParameterHelper().getMonthlyIncome());
		billForm.getBillHeaderBO().setRateOfInterest(getParameterHelper().getRateOfInterest());
		 billForm.getBillHeaderBO().setRedemptionDate(
				 getParameterHelper().getRedemCurrentDate() != null && getParameterHelper().getRedemCurrentDate().length()>0?						
						 getParameterHelper().getRedemCurrentDate():DateUtil.getTodaysDate());
		
	}
	
	private void clearBillHeadBillFormForBillRedemption(BillForm billForm){
		billForm.getBillHeaderBO().setRedemptionInterest(0.0);
		billForm.getBillHeaderBO().setRedemptionTotal(0.0);
		billForm.getBillHeaderBO().setRedemptionStatus("");
		billForm.getBillHeaderBO().setBillRedemSerial("");
		billForm.getBillHeaderBO().setBillRedemNumber(0);
		 //this will be seen only at the time of bill redemption
		 billForm.getBillHeaderBO().setBillRedemSerial(getParameterHelper().getCurrentBillRedemSerial());
	}
}
