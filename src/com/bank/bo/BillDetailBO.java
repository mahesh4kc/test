package com.bank.bo;


public class BillDetailBO {
	
private Integer billSequence;
private Integer productNumber;
private String productDescription;
private Integer productQuantity ;
private boolean checked;

public boolean getChecked() {
	return this.checked;
}
public void setChecked(boolean checked) {
	this.checked = checked;
}
public Integer getBillSequence() {
	return this.billSequence;
}
public void setBillSequence(Integer billSequence) {
	this.billSequence = billSequence;
}
public Integer getProductNumber() {
	return this.productNumber;
}
public void setProductNumber(Integer productNumber) {
	this.productNumber = productNumber;
}
public String getProductDescription() {
	return this.productDescription;
}
public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}
public Integer getProductQuantity() {
	return this.productQuantity;
}
public void setProductQuantity(Integer productQuantity) {
	this.productQuantity = productQuantity;
}

}
