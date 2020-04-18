package com.bank.bo;

import java.util.Map;

public class BillBO {

	public BillHeaderBO getBillHeaderBO() {
		return billHeaderBO;
	}
	public void setBillHeaderBO(BillHeaderBO billHeaderBO) {
		this.billHeaderBO = billHeaderBO;
	}
	public BillDetailBO getBillDetailBO() {
		return billDetailBO;
	}
	public void setBillDetailBO(BillDetailBO billDetailBO) {
		this.billDetailBO = billDetailBO;
	}
	public CustomerBO getCustomerBO() {
		return customerBO;
	}
	public void setCustomerBO(CustomerBO customerBO) {
		this.customerBO = customerBO;
	}
	private BillHeaderBO billHeaderBO;
	private BillDetailBO billDetailBO;
	private CustomerBO customerBO;
	private Map<String, String> parameterMap;
	public Map<String, String> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
}
