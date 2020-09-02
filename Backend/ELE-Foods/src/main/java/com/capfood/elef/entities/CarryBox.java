package com.capfood.elef.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ELEF-CARRYBOX")
public class CarryBox {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="BOXID")
	private int boxId;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@OneToMany(mappedBy="carryBox")
	private List<Item> items;

	@OneToOne
	private Customer customer;
	
	
	public CarryBox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarryBox(int boxId, int quantity, List<Item> items, Customer customer) {
		super();
		this.boxId = boxId;
		this.quantity = quantity;
		this.items = items;
		this.customer = customer;
	}

	public int getBoxId() {
		return boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CarryBox [boxId=" + boxId + ", quantity=" + quantity + ", items=" + items + ", customer=" + customer
				+ "]";
	}
	
	
}
