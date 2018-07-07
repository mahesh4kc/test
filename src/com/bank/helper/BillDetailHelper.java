package com.bank.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.BillDetailBO;
import com.bank.form.BillForm;
import com.bank.util.BankConstant;

public class BillDetailHelper {
	public static void setBillDetailListFromResultSet(ResultSet resultSet, List<BillDetailBO> billDetailList) throws SQLException{
		BillDetailBO billDetailBO;
		while (resultSet.next()){
			billDetailBO = getBillDetailBOFromResultSet(resultSet);
			billDetailList.add(billDetailBO);
			/*if(billDetailList.size() > BankConstant.RECORDS_PER_PAGE.intValue()){
				break;
			}*/
		}
	}
	
	public static BillDetailBO getBillDetailBOFromResultSet(ResultSet resultSet) throws SQLException{
			BillDetailBO billDetailBO = new BillDetailBO();		
			billDetailBO.setBillSequence(resultSet.getInt(1));
			billDetailBO.setProductNumber(resultSet.getInt(2));
			billDetailBO.setProductDescription(resultSet.getString(3));
			billDetailBO.setProductQuantity(resultSet.getInt(4));				
		return billDetailBO;
	}
	
	public static void moveBillDetailsToBillForm(List<BillDetailBO> billDetailList,BillForm billForm){
		billForm.setBillDetailList(billDetailList);	
		if (billDetailList.size() < 1 ){
			billForm.getBillDetailList().add(new BillDetailBO());
		}
	}

	public static void clearBillDetailsBillForm(BillForm billForm){
		List<BillDetailBO> billDetailList = new ArrayList<BillDetailBO>();
		billForm.setBillDetailList(billDetailList);
		if (billDetailList.size() < 1 ){
			billForm.getBillDetailList().add(new BillDetailBO());
		}
	}
	
	
	
}
