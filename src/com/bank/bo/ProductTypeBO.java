package com.bank.bo;

public class ProductTypeBO {
	private Integer productTypeNo;
	private String productTypeCode;
	private String productTypeDescription;
	private Double productTypeRateOfInterest;
	private boolean checked;
	
	public boolean getChecked() {
		return this.checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getProductTypeNo() {
		return productTypeNo;
	}
	public void setProductTypeNo(Integer productTypeNo) {
		this.productTypeNo = productTypeNo;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	public String getProductTypeDescription() {
		return productTypeDescription;
	}
	public void setProductTypeDescription(String productTypeDescription) {
		this.productTypeDescription = productTypeDescription;
	}
	public Double getProductTypeRateOfInterest() {
		return productTypeRateOfInterest;
	}
	public void setProductTypeRateOfInterest(Double productTypeRateOfInterest) {
		this.productTypeRateOfInterest = productTypeRateOfInterest;
	}
}
