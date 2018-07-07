package com.bank.bo;

import java.util.Map;


public class SearchMasterScreenBO {
	private Map<String,String> searchPK;
	
	private String searchPrimaryKey;
	// customer id 
	//Bill serial and bill no 
	private String search1;	
	
	// customer name 
	private String search2;		//Bill Date
	
	private CustomerBO customerBO;	//Present Value
	
	private BillHeaderBO billHeaderBO;	//Present Value
	
	public BillHeaderBO getBillHeaderBO() {
		return billHeaderBO;
	}
	public void setBillHeaderBO(BillHeaderBO billHeaderBO) {
		this.billHeaderBO = billHeaderBO;
	}
	public CustomerBO getCustomerBO() {
		return customerBO;
	}
	public void setCustomerBO(CustomerBO customerBO) {
		this.customerBO = customerBO;
	}
	// customer address 
	private String search3;		//Customer
	// customer street 
	private String search4;		//Address
	// customer area, district and pincode 
	private String search5;		//Amount
	
	private String search6;		//Product Description and status
	private String search7;		//Redemption data
	private String search8;		//Redemption serail and redemption serial no
	private String search9;		//Gram
	private String search10;	//Present Value
	
	public String getSearch9() {
		return this.search9;
	}
	public void setSearch9(String search9) {
		this.search9 = search9;
	}
	public String getSearch10() {
		return this.search10;
	}
	public void setSearch10(String search10) {
		this.search10 = search10;
	}
	public String getSearch6() {
		return this.search6;
	}
	public void setSearch6(String search6) {
		this.search6 = search6;
	}
	public String getSearchPrimaryKey() {
		return this.searchPrimaryKey;
	}
	public void setSearchPrimaryKey(String searchPrimaryKey) {
		this.searchPrimaryKey = searchPrimaryKey;
	}
	public String getSearch5() {
		return this.search5;
	}
	public void setSearch5(String search5) {
		this.search5 = search5;
	}
	public Map<String, String> getSearchPK() {
		return this.searchPK;
	}
	public void setSearchPK(Map<String, String> searchPK) {
		this.searchPK = searchPK;
	}
	public String getSearch1() {
		return this.search1;
	}
	public void setSearch1(String search1) {
		this.search1 = search1;
	}
	public String getSearch2() {
		return this.search2;
	}
	public void setSearch2(String search2) {
		this.search2 = search2;
	}
	public String getSearch3() {
		return this.search3;
	}
	public void setSearch3(String search3) {
		this.search3 = search3;
	}
	public String getSearch4() {
		return this.search4;
	}
	public void setSearch4(String search4) {
		this.search4 = search4;
	}
	public String getSearch7() {
		return this.search7;
	}
	public void setSearch7(String search7) {
		this.search7 = search7;
	}
	public String getSearch8() {
		return this.search8;
	}
	public void setSearch8(String search8) {
		this.search8 = search8;
	}
	
	
	
}
