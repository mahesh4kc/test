package com.bank.util.pdf;

public class BillPdfHelper {
	public String getLicenceNumber() {
		return licenceNumber;
	}
	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopNameInTamil() {
		return shopNameInTamil;
	}
	public void setShopNameInTamil(String shopNameInTamil) {
		this.shopNameInTamil = shopNameInTamil;
	}
	public String getShopAddress1() {
		return shopAddress1;
	}
	public void setShopAddress1(String shopAddress1) {
		this.shopAddress1 = shopAddress1;
	}
	public String getShopAddress2() {
		return shopAddress2;
	}
	public void setShopAddress2(String shopAddress2) {
		this.shopAddress2 = shopAddress2;
	}
	public String getBillSerial() {
		return billSerial;
	}
	public void setBillSerial(String billSerial) {
		this.billSerial = billSerial;
	}
	public String getBillSerialNumber() {
		return billSerialNumber;
	}
	public void setBillSerialNumber(String billSerialNumber) {
		this.billSerialNumber = billSerialNumber;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress1() {
		return customerAddress1;
	}
	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}
	public String getCustomerAddress2() {
		return customerAddress2;
	}
	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public String getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getPresentValue() {
		return presentValue;
	}
	public void setPresentValue(String presentValue) {
		this.presentValue = presentValue;
	}
	private String licenceNumber;
	private String shopName;
	private String shopNameInTamil;
	private String shopAddress1;
	private String shopAddress2;
	private String billSerial;
	private String billSerialNumber;
	private String billDate;
	private String customerName;
	private String customerAddress1;
	private String customerAddress2;
	private String amount;
	private String amountInWords;
	private String familyIncome;
	private String presentValue;
	
}

class BillDetail{
	public String getBillSNo() {
		return billSNo;
	}
	public void setBillSNo(String billSNo) {
		this.billSNo = billSNo;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getGrams() {
		return grams;
	}
	public void setGrams(String grams) {
		this.grams = grams;
	}
	private String billSNo;
	private String productDescription;
	private String grams;
}
