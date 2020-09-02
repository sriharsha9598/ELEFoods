package com.capfood.elef.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="ELEF-ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ITEMID")
	private int itemId;
	
	@Column(name="ITEMNAME")
	private String itemName;
	
	@Column(name="ITEMDESCRIPTION")
	private String itemDescription;
	
	@Column(name="ITEMPRICE")
	private double itemPrice;
	
	@Column(name="SPECIALITY")
	private String speciality;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinColumn(name="branch")
	private Branch branch;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="order")
	private Order order;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="carryBox")
	private CarryBox carryBox;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subCategory")
	private SubCategory subCategory;

	
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int itemId, String itemName, String itemDescription, double itemPrice, String speciality,
			boolean active, Branch branch, Order order, CarryBox carryBox, SubCategory subCategory) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.speciality = speciality;
		this.active = active;
		this.branch = branch;
		this.order = order;
		this.carryBox = carryBox;
		this.subCategory = subCategory;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@JsonIgnore
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@JsonIgnore
	public CarryBox getCarryBox() {
		return carryBox;
	}

	public void setCarryBox(CarryBox carryBox) {
		this.carryBox = carryBox;
	}

	@JsonIgnore
	public SubCategory getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", speciality=" + speciality + ", branch=" + branch + ", order=" + order
				+ ", carryBox=" + carryBox + ", subCategory=" + subCategory + "]";
	}
	
}
