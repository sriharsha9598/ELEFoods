package com.capfood.elef.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ELEF-ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="addressId")
	private int addressId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="LINE1")
	private String line1;
	
	@Column(name="LINE2")
	private String line2;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="LANDMARK")
	private String landmark;
	
	@Column(name="MOBILENUMBER")
	private String mobileNumber;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="customer")
	private Customer customer;
	
	
	
	public Address() {
		super();
	}


	public Address(int addressId, String name, String line1, String line2, String city, String landmark,
			String mobileNumber, Customer customer) {
		super();
		this.addressId = addressId;
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.landmark = landmark;
		this.mobileNumber = mobileNumber;
		this.customer = customer;
	}


	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLine1() {
		return line1;
	}


	public void setLine1(String line1) {
		this.line1 = line1;
	}


	public String getLine2() {
		return line2;
	}


	public void setLine2(String line2) {
		this.line2 = line2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getLandmark() {
		return landmark;
	}


	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", name=" + name + ", line1=" + line1 + ", line2=" + line2
				+ ", city=" + city + ", landmark=" + landmark + ", mobileNumber=" + mobileNumber + ", customer="
				+ customer + "]";
	}
	
	
}
