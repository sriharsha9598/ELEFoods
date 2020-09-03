package com.capfood.elef.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ELEF_USER")
public class User {
	@Id
	@Column(name="USERID")
	private int userId;
	
	@NotEmpty(message="customer name should not be empty")
	@Length(max=30)
	@Column(name="NAME")
	private String customerName;
	
	@NotEmpty(message="emailId should not be empty")
	@Length(max=30)
	@Column(name="EMAILID")
	private String emailId;
	
	@NotEmpty(message="user name cannot be empty")
	@Length(max=10)
	@Column(name="USERNAME")
	private String userName;
	
	@NotEmpty(message="password cannot be empty")
	@Length(max=10)
	@Column(name="PASSWORD")
	private String password;
	
	@NotEmpty(message="security question cannot be empty")
	@Length(max=30)
	private String security_question;
	
	@NotEmpty(message="security question cannot be empty")
	@Length(max=30)
	private String security_answer;
	
    @NotEmpty(message="mobile number should not be empty")
    @Length(max=30)
	@Column(name="MOBILENUMBER")
	private String mobileNumber;
    
    @NotEmpty(message="role should not be empty")
    @Pattern(regexp="Admin|Customer")
    private String role;
	
    @OneToMany(mappedBy="customer")
    private List<Address> address;
    
    @OneToOne
    private CarryBox carryBox;
    

	@OneToMany(mappedBy="customer")
	private List<Order> myOrders;
	

	
	@OneToOne(mappedBy="admin")
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	public User() {
		
	}

	public User(int userId,
			@NotEmpty(message = "customer name should not be empty") @Length(max = 30) String customerName,
			@NotEmpty(message = "emailId should not be empty") @Length(max = 30) String emailId,
			@NotEmpty(message = "user name cannot be empty") @Length(max = 10) String userName,
			@NotEmpty(message = "password cannot be empty") @Length(max = 10) String password,
			@NotEmpty(message = "security question cannot be empty") @Length(max = 30) String security_question,
			@NotEmpty(message = "security question cannot be empty") @Length(max = 30) String security_answer,
			@NotEmpty(message = "mobile number should not be empty") @Length(max = 30) String mobileNumber,
			@NotEmpty(message = "role should not be empty") @Pattern(regexp = "Admin|Customer") String role,
			List<Address> address, CarryBox carryBox, List<Order> myOrders, Branch branch) {
		super();
		this.userId = userId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
		this.security_question = security_question;
		this.security_answer = security_answer;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.address = address;
		this.carryBox = carryBox;
		this.myOrders = myOrders;
		this.branch = branch;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurity_question() {
		return security_question;
	}

	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}

	public String getSecurity_answer() {
		return security_answer;
	}

	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@JsonIgnore
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

	@JsonIgnore
	public List<Order> getMyOrders() {
		return myOrders;
	}

	
	public void setMyOrders(List<Order> myOrders) {
		this.myOrders = myOrders;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	
	
}
