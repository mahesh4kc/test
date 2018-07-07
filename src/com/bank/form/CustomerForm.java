package com.bank.form;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.bank.business.validation.CustomerBusinessValidation;



public class CustomerForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerID;
	private String name;
	private String address;
	private String street;
	private String area;
	private String state;
	private String district;
	private String country;
	private String pincode;
	private String photo;
	private String phoneNo;
	private String mobileNo;
	private String mailID;
	private String relationShip;
	private String relationName;
	private String createDuplicateCustomer;
	
	
	
	private String customerList;
	
	public String getCustomerList() {
		return this.customerList;
	}

	public void setCustomerList(String customerList) {
		this.customerList = customerList;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(String customerID) {
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	// The method providing validation of this classes attributes
	/*	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	    ActionErrors errors = new ActionErrors();
	    System.out.println("Validate Method of Customer");

* 
   if ($SOME_CODE_TO_VALIDATE_AN_ATTRIBUTE_OF_THIS_CLASS_RETURNING_true_ON_SUCCESS$) {
	      // 
	    } else {
	      // Add an error to the errors to be returned that designates the validation of the
	      // current attribute failed. The information will come from the Application Resources.
	      errors.add("$THE_ATTRIBUTES_NAME$", new ActionError("$SOME_KEY_FROM_THE_ApplicationResources.properties_FILE$"));
	    }

	    // Return the errors
	 
	    return errors;
	 }*/

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
	    ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    String jndiName = (String)session.getAttribute("databaseName");
	    CustomerBusinessValidation customerBusinessValidation = new CustomerBusinessValidation(jndiName);
	  try{
		  if(action.equals("SEARCH")){
		    	customerBusinessValidation.validateCustomerDetailsForSearch(errors, this,jndiName);	
		    	//CustomerValidation.validateSearchCustomer(errors, this);
		  }else if(action.equals("CREATE")){
		    	customerBusinessValidation.validateCustomerDetailsForCreate(errors, this,jndiName);	
		    	//CustomerValidation.validateSearchCustomer(errors, this);
		    }else if(action.equals("UPDATE")){
		    	customerBusinessValidation.validateCustomerDetailsForUpdate(errors, this,jndiName);	
		    	//CustomerValidation.validateSearchCustomer(errors, this);
		    }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }
	   /*else if(action.equals("CREATE")){
	    	CustomerValidation.validateCreateCustomer(errors, this);
	    }*/
	   /* if ((this.name == null)  || (this.customerID == null))
		      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));
	    else if ((this.name.length() == 0) || (this.customerID.length() == 0))
	      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));*/
	    return errors;
	  }

	public void reset(ActionMapping mapping, HttpServletRequest request){
		//System.out.println("Reset method fired");
		setUserLoggedIn(request);
	}

	public String getRelationShip() {
		return this.relationShip;
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

	public String getCreateDuplicateCustomer() {
		return this.createDuplicateCustomer;
	}

	public void setCreateDuplicateCustomer(String createDuplicateCustomer) {
		this.createDuplicateCustomer = createDuplicateCustomer;
	}
}
