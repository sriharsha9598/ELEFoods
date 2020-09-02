package com.capfood.elef.entities;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


@Entity
@Table (name="ELEF_ITEM")
public class Item  {

	@Id
	@Column(name="ITEMID")
	private int itemId;
	
	@Length(max=30)
	@NotEmpty(message="iten name cannot be empty")
	@Column(name="ITEMNAME")
	private String itemName;
	
	@Length(max=50)
	@Column(name="ITEMDESCRIPTION")
	private String itemDescription;
	
	@Min(value=0)
	@Column(name="ITEMPRICE")
	private double itemPrice;
	
	
	@Column(name="SPECIALITY")
	private String speciality;
	
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Branch> branch_item;
	   
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="carryBox")
	private CarryBox carryBox;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ordered")
	private Order ordered;
	
	@Column(nullable=true)
    private int subcatId;
	
	public Item() {
		
	}

	public Item(int itemId, @Length(max = 30) @NotEmpty(message = "iten name cannot be empty") String itemName,
			@Length(max = 50) String itemDescription, @Min(0) double itemPrice, String speciality, boolean active,
			List<Branch> branch_item, CarryBox carryBox, Order ordered, int subcatId) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.speciality = speciality;
		this.active = active;
		this.branch_item = branch_item;
		this.carryBox = carryBox;
		this.ordered = ordered;
		this.subcatId = subcatId;
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

	public List<Branch> getBranch_item() {
		return branch_item;
	}

	public void setBranch_item(List<Branch> branch_item) {
		this.branch_item = branch_item;
	}

	public CarryBox getCarryBox() {
		return carryBox;
	}

	public void setCarryBox(CarryBox carryBox) {
		this.carryBox = carryBox;
	}

	public Order getOrdered() {
		return ordered;
	}

	public void setOrdered(Order ordered) {
		this.ordered = ordered;
	}

	public int getSubcatId() {
		return subcatId;
	}

	public void setSubcatId(int subcatId) {
		this.subcatId = subcatId;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", speciality=" + speciality + ", active=" + active + ", branch_item="
				+ branch_item + ", carryBox=" + carryBox + ", ordered=" + ordered + ", subcatId=" + subcatId + "]";
	}
	
	
	}