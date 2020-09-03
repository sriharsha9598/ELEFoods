package com.capfood.elef.entities;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="ELEF_ORDER")
public class Order {
	

	@Id
	@Column(name="ID")
	private int id;
	
	
	@Column(name="ORDERDATE")
	private LocalDate orderDate;
	
	@Column(name="ORDERTIME")
	private LocalTime orderTime;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@Column(name="ORDERPRICE")
	private double orderPrice;
	
	@Column(name="ORDERSTATUS")
	private String orderStatus;
	
	@Column(name="STATUSDESCRIPTION")
	private String statusDescription;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BRANCH_ORDER")
	private Branch branch_order;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer")
	private User customer;
 
	
	@OneToMany(mappedBy="ordered")
 	private List<Item> items;
	
	public Order() {
		
	}

	public Order(int id,  LocalDate orderDate, LocalTime orderTime, int quantity, double orderPrice,
			String orderStatus, String statusDescription, Branch branch_order, User customer, List<Item> items) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
		this.orderStatus = orderStatus;
		this.statusDescription = statusDescription;
		this.branch_order = branch_order;
		this.customer = customer;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@JsonIgnore
	public Branch getBranch_order() {
		return branch_order;
	}

	public void setBranch_order(Branch branch_order) {
		this.branch_order = branch_order;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	@JsonIgnore
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", quantity=" + quantity
				+ ", orderPrice=" + orderPrice + ", orderStatus=" + orderStatus + ", statusDescription="
				+ statusDescription + ", branch_order=" + branch_order + ", customer=" + customer + ", items=" + items
				+ "]";
	}



	
	
}