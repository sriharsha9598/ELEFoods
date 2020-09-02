package com.capfood.elef.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ELEF-CUSTOMER")
public class Customer {

	@Id
	@Column(name="CUSTOMERID")
	private int customerId;
	
	@Column(name="CUSTOMERNAME")
	private String customerName;
	
	@Column(name="EMAILID")
	private String emailId;
	
	@Column(name="MOBILENUMBER")
	private String mobileNumber;
	
	@OneToMany(mappedBy="customer")
	private List<Order> myOrders;
	
	@OneToMany(mappedBy="customer")
	private List<Address> addresses;

	@OneToOne
	private CarryBox carryBox;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	public Customer(int customerId, String customerName, String emailId, String mobileNumber, List<Order> myOrders,
			List<Address> addresses, CarryBox carryBox) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.myOrders = myOrders;
		this.addresses = addresses;
		this.carryBox = carryBox;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	public CarryBox getCarryBox() {
		return carryBox;
	}


	public void setCarryBox(CarryBox carryBox) {
		this.carryBox = carryBox;
	}


	public List<Order> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(List<Order> myOrders) {
		this.myOrders = myOrders;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + ", myOrders=" + myOrders + ", addresses=" + addresses
				+ ", carryBox=" + carryBox + "]";
	}

    
}
