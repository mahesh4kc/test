package com.bank.bo;

public class ProductDescriptionBO {
	
	//private String productCode;
	private String productDescription;
	private Integer productNo;
	private boolean checked;
	
	public boolean getChecked() {
		return this.checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public Integer getProductNo() {
		return this.productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	/*public String getProductCode() {
		return this.productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}*/
	public String getProductDescription() {
		return this.productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
