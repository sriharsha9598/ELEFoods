package com.capfood.elef.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ELEF-ORDER")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="ORDERID")
	private int orderId;
	
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
	
	
	@OneToMany(mappedBy="order")
	private List<Item> items;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer")
	private Customer customer;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="branch")
	private Branch branch;

	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(int id, int orderId, LocalDate orderDate, LocalTime orderTime, int quantity, double orderPrice,
			String orderStatus, String statusDescription, List<Item> items, Customer customer, Branch branch) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
		this.orderStatus = orderStatus;
		this.statusDescription = statusDescription;
		this.items = items;
		this.customer = customer;
		this.branch = branch;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
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


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@JsonIgnore
	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", quantity=" + quantity + ", orderPrice=" + orderPrice + ", orderStatus=" + orderStatus
				+ ", statusDescription=" + statusDescription + ", items=" + items + ", customer=" + customer
				+ ", branch=" + branch + "]";
	}

}