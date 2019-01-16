package com.cg.mps.model;

import java.sql.Date;

public class CustomerDetails {
	private String customerName;
	private String customerMobileNumber;
	private String MailId;
	private int mobileId;
	private int purchasedId;
	private Date purchaseDate;
	private Double mobilePrice; 
	private String mobileName;
	private int quantity;
	
	@Override
	public String toString() {
		return "CustomerDetails [customerName=" + customerName
				+ ", customerMobileNumber=" + customerMobileNumber
				+ ", MailId=" + MailId + ", mobileId=" + mobileId
				+ ", purchasedId=" + purchasedId + ", purchaseDate="
				+ purchaseDate + ", mobilePrice=" + mobilePrice
				+ ", mobileName=" + mobileName + ", quantity=" + quantity + "]";
	}

	public CustomerDetails() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDetails(String customerName, String eMailId, int mobileId,
			int purchasedId, Date purchaseDate) {
		super();
		this.customerName = customerName;
		this.MailId = eMailId;
		this.mobileId = mobileId;
		this.purchasedId = purchasedId;
		this.purchaseDate = purchaseDate;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String geteMailId() {
		return MailId;
	}
	
	public void seteMailId(String eMailId) {
		this.MailId = eMailId;
	}
	
	public int getMobileId() {
		return mobileId;
	}
	
	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}
	
	public int getPurchasedId() {
		return purchasedId;
	}
	
	public void setPurchasedId(int purchasedId) {
		this.purchasedId = purchasedId;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getMobilePrice() {
		return mobilePrice;
	}

	public void setMobilePrice(Double mobilePrice) {
		this.mobilePrice = mobilePrice;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
