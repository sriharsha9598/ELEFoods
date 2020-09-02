package com.capfood.elef.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ELEF-BRANCH")
public class Branch {

	@Id
	@Column(name="BRANCHID")
	private int branchId;
	
//	@NotEmpty(message="Name should not be empty")
//	@Length(max=25)
	@Column(name="BRANCHNAME")
	private String branchName;
	
	@Column(name="CONTACTNUMBER")
	private String contactNumber;
	
	@OneToOne
	private Admin admin;
	
	
	@OneToMany(mappedBy="branch")
	private List<Order> orders;
	
	
	@ManyToMany(mappedBy="branch")
	private List<Item> items;
	
	
	@ManyToMany(mappedBy="branch")
	private List<Category> categories;

	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Branch(int branchId, String branchName, String contactNumber, Admin admin, List<Order> orders,
			List<Item> items, List<Category> categories) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.contactNumber = contactNumber;
		this.admin = admin;
		this.orders = orders;
		this.items = items;
		this.categories = categories;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", contactNumber=" + contactNumber
				+ ", admin=" + admin + ", orders=" + orders + ", items=" + items + ", categories=" + categories + "]";
	}

			
}
