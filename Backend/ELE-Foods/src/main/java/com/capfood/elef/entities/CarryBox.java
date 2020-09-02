package com.capfood.elef.entities;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;


@Entity
@Table(name="ELEF_CARRYBOX")
public class CarryBox{
	
	

	@Id
	@Column(name="BOXID")
	private int boxId;
	
	
	@Column(name="QUANTITY")
	@Min(value=0)
	private int quantity;
	

	@Column(name="total_cost")
	@Min(value=0)
	private int total_cost;
	
	@OneToMany(mappedBy="carryBox")
	private List<Item> itemlist;
	
	
	@OneToOne(mappedBy="carryBox")
	@JoinColumn(name="CUSTOMER")
	private Customer customer;
	
	public CarryBox() {
		
	}

	public CarryBox(int boxId, int quantity, int cost, List<Item> itemlist, Customer customer) {
		super();
		this.boxId = boxId;
		this.quantity = quantity;
		this.itemlist = itemlist;
		this.customer = customer;
		this.total_cost = cost;
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

	public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	@Override
	public String toString() {
		return "CarryBox [boxId=" + boxId + ", quantity=" + quantity + ", total_cost=" + total_cost + ", itemlist="
				+ itemlist + ", customer=" + customer + "]";
	}

	
	
	
}