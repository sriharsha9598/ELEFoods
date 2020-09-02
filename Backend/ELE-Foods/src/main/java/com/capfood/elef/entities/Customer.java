package com.capfood.elef.entities;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="ELEF_CUSTOMER")
public class Customer  {

	@Id
	@Column(name="CUSTOMERID")
	private int customerId;
	
	@NotEmpty(message="customer name should not be empty")
	@Length(max=30)
	@Column(name="CUSTOMERNAME")
	private String customerName;
	
	@NotEmpty(message="emailId should not be empty")
	@Length(max=30)
	@Column(name="EMAILID")
	private String emailId;
	
    @NotEmpty(message="mobile number should not be empty")
    @Length(max=30)
	@Column(name="MOBILENUMBER")
	private String mobileNumber;
	
    @OneToMany(mappedBy="customer")
    private List<Address> address;
    
    @OneToOne
    private CarryBox carryBox;
    

	@OneToMany(mappedBy="customer")
	private List<Order> myOrders;
	
	public Customer() {
		
	}

	public Customer(int customerId,
			@NotEmpty(message = "customer name should not be empty") @Length(max = 30) String customerName,
			@NotEmpty(message = "emailId should not be empty") @Length(max = 30) String emailId,
			@NotEmpty(message = "mobile number should not be empty") @Length(max = 30) String mobileNumber,
			List<Address> address, CarryBox carryBox, List<Order> myOrders) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.carryBox = carryBox;
		this.myOrders = myOrders;
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

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + ", carryBox=" + carryBox + ", myOrders="
				+ myOrders + "]";
	}
	
	
	
}