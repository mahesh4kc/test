package com.bank.bo;


public class CustomerBO {
private int customerID;
private String name;
private String address;
private String street;
private String area;
private String state;
private String district;
private String country;
private Integer pincode;
private String photo;
private Long phoneNo;
private Long mobileNo;
private String mailID;
private String relationShip;
private String relationName;

public String getRelationShip() {
	return this.relationShip;
}

public String getRelationShipDetails() {
	String relationShipDetails = "";
	if (this.relationShip.equalsIgnoreCase("S")){
		relationShipDetails = "S/O";
	}else if (this.relationShip.equalsIgnoreCase("F")){
		relationShipDetails = "F/O";
	}else if (this.relationShip.equalsIgnoreCase("H")){
		relationShipDetails = "H/O";
	}else if (this.relationShip.equalsIgnoreCase("D")){
		relationShipDetails = "D/O";
	}else if (this.relationShip.equalsIgnoreCase("W")){
		relationShipDetails = "W/O";
	}
	return relationShipDetails;
}

public void setRelationShip(String relationShip) {
	this.relationShip = relationShip;
}

public String getRelationName() {
	return this.relationName;
}

public void setRelationName(String relationName) {
	this.relationName = relationName;
}

public Integer getCustomerID() {
	return customerID;
}
public void setCustomerID(Integer customerID) {
	this.customerID = customerID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public Integer getPincode() {
	return pincode;
}
public void setPincode(Integer pincode) {
	this.pincode = pincode;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Long getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(Long phoneNo) {
	this.phoneNo = phoneNo;
}
public Long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(Long mobileNo) {
	this.mobileNo = mobileNo;
}
public String getMailID() {
	return mailID;
}
public void setMailID(String mailID) {
	this.mailID = mailID;
}}
