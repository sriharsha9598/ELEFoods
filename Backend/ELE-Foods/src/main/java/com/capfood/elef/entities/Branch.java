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
@Table(name="ELEF_BRANCH")
public class Branch {


	@Id
	@Column(name="BRANCHID")
	private int branchId;
	
	@NotEmpty(message=" Branch Name should not be empty")
	@Length(max=25)
	@Column(name="BRANCHNAME")
	private String branchName;
	
	@Length(max=10)
	@NotEmpty(message="Name should not be empty")
	@Column(name="CONTACTNUMBER")
	private String contactNumber;
	
	@OneToOne
	private User admin;
	
	@OneToMany(mappedBy="branch_order")
	private List<Order> orders;
	
	@OneToMany(mappedBy="branch")
	private List<Item> items;
	
	
	@OneToMany(mappedBy="branch")
	private List<Category> category;
	
	public Branch() {
		
	}

	public Branch(int branchId,
			@NotEmpty(message = " Branch Name should not be empty") @Length(max = 25) String branchName,
			@Length(max = 10) @NotEmpty(message = "Name should not be empty") String contactNumber, User admin,
			List<Order> orders, List<Item> items, List<Category> category) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.contactNumber = contactNumber;
		this.admin = admin;
		this.orders = orders;
		this.items = items;
		this.category = category;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}


   
}