package com.bank.bo;

import java.util.Comparator;



public class BillHeaderBO implements Comparable<BillHeaderBO>{
		
	private Integer billSequence;
	private String billSerial;
	private Integer billNumber;
	private String billDate ;
	private Integer customerID;
	private String careOf;
	private Integer productTypeNumber;
	private Double rateOfInterest;
	private Integer amount;
	private String amountInWords;
	private Integer presentValue;
	private Double grams;
	private Integer monthlyIncome;
	
	private String redemptionDate ;
	private Double redemptionInterest;
	private Double redemptionTotal;
	private String redemptionStatus ;
	
	private String billRedemSerial;
	private Integer billRedemNumber;
	
	
	public String getBillRedemSerial() {
		return this.billRedemSerial;
	}
	public void setBillRedemSerial(String billRedemSerial) {
		this.billRedemSerial = billRedemSerial;
	}
	public Integer getBillRedemNumber() {
		return this.billRedemNumber;
	}
	public void setBillRedemNumber(Integer billRedemNumber) {
		this.billRedemNumber = billRedemNumber;
	}
	public String getRedemptionDate() {
		return this.redemptionDate;
	}
	public void setRedemptionDate(String redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
	public Double getRedemptionInterest() {
		return this.redemptionInterest;
	}
	public void setRedemptionInterest(Double redemptionInterest) {
		this.redemptionInterest = redemptionInterest;
	}
	public Double getRedemptionTotal() {
		return this.redemptionTotal;
	}
	public void setRedemptionTotal(Double redemptionTotal) {
		this.redemptionTotal = redemptionTotal;
	}
	public String getRedemptionStatus() {
		return this.redemptionStatus;
	}
	public void setRedemptionStatus(String redemptionStatus) {
		this.redemptionStatus = redemptionStatus;
	}
	public Integer getBillSequence() {
		return this.billSequence;
	}
	public void setBillSequence(Integer billSequence) {
		this.billSequence = billSequence;
	}
	public String getBillSerial() {
		return this.billSerial;
	}
	public void setBillSerial(String billSerial) {
		this.billSerial = billSerial;
	}
	public Integer getBillNumber() {
		return this.billNumber;
	}
	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}
	public String getBillDate() {
		return this.billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public Integer getCustomerID() {
		return this.customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getCareOf() {
		return this.careOf;
	}
	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}
	public Integer getProductTypeNumber() {
		return this.productTypeNumber;
	}
	public void setProductTypeNumber(Integer productTypeNumber) {
		this.productTypeNumber = productTypeNumber;
	}
	public Double getRateOfInterest() {
		return this.rateOfInterest;
	}
	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAmountInWords() {
		return this.amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public Integer getPresentValue() {
		return this.presentValue;
	}
	public void setPresentValue(Integer presentValue) {
		this.presentValue = presentValue;
	}
	public Double getGrams() {
		return this.grams;
	}
	public void setGrams(Double grams) {
		this.grams = grams;
	}
	public Integer getMonthlyIncome() {
		return this.monthlyIncome;
	}
	public void setMonthlyIncome(Integer monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getStatusDescription() {
		String statusDescription ="";
		if(this.redemptionStatus.equalsIgnoreCase("O")){
			statusDescription = "OPEN";
		}else if(this.redemptionStatus.equalsIgnoreCase("R")){
			statusDescription = "REDEM";
		}else if(this.redemptionStatus.equalsIgnoreCase("C")){
			statusDescription = "CANCEL";
		}else if(this.redemptionStatus.equalsIgnoreCase("A")){
			statusDescription = "AUCTION";
		}
		return statusDescription ;
	}
	
	public String toString(){
		return this.billSequence + this.billSerial + this.billNumber + this.getBillDate()+this.getCustomerID()
		+this.getCareOf()+this.getAmount()+this.getAmount()+this.getAmountInWords()+this.getProductTypeNumber()
		+this.getGrams()+this.getPresentValue()+this.getRateOfInterest();
	}
	
	//Sort by first name, then last name and then by age
		public int compareTo(BillHeaderBO billHeaderBO) {
			int i = getBillSerial().toUpperCase().compareTo(
					billHeaderBO.getBillSerial().toUpperCase());
			if(i!=0) return i;
			
			i = getBillNumber().compareTo(billHeaderBO.getBillNumber());
			if(i!=0) return i;
			
			//return age - person.getAge();
			return 0;
		}
		
		
		//Sort by first name
		public static Comparator<Object> billSerialComp = new Comparator<Object>(){
			  public int compare(Object billHeaderBO, Object anotherbillHeaderBO) {
				  Integer billNumber1 = ((BillHeaderBO) billHeaderBO).getBillNumber();
			      String billSerial1 = ((BillHeaderBO) billHeaderBO).getBillSerial().toUpperCase();
			      Integer billNumber2 = ((BillHeaderBO) anotherbillHeaderBO).getBillNumber();
			      String billSerial2 = ((BillHeaderBO) anotherbillHeaderBO).getBillSerial().toUpperCase();

			      if (!(billSerial1.equals(billSerial2)))
			        return billSerial1.compareTo(billSerial2);
			      else
			        return billNumber1.compareTo(billNumber2);

			}
		};
		
		//Sort by first name
				public static Comparator<Object> billNumberComp = new Comparator<Object>(){
					  public int compare(Object billHeaderBO, Object anotherbillHeaderBO) {
						  Integer billNumber1 = ((BillHeaderBO) billHeaderBO).getBillNumber();
					      String billSerial1 = ((BillHeaderBO) billHeaderBO).getBillSerial().toUpperCase();
					      Integer billNumber2 = ((BillHeaderBO) anotherbillHeaderBO).getBillNumber();
					      String billSerial2 = ((BillHeaderBO) anotherbillHeaderBO).getBillSerial().toUpperCase();
					      
					      if (!(billNumber1.equals(billNumber2)))
						        return billNumber1.compareTo(billNumber2);
						      else
						        return billSerial1.compareTo(billSerial2);

					}
				};

		/*//Sort by last name
		public static Comparator<Object> ageComp = new Comparator<Object>(){
			  public int compare(Object person, Object anotherPerson) {
				  String lastName1 = ((Person) person).getLastName().toUpperCase();
			      String firstName1 = ((Person) person).getFirstName().toUpperCase();
			      String lastName2 = ((Person) anotherPerson).getLastName().toUpperCase();
			      String firstName2 = ((Person) anotherPerson).getFirstName().toUpperCase();

			      if (!(lastName1.equals(lastName2)))
			        return lastName1.compareTo(lastName2);
			      else
			        return firstName1.compareTo(firstName2);
			}
		};	*/
}
