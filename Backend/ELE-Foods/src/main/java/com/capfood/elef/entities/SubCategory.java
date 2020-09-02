package com.capfood.elef.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ELEF_SUBCATEGORY")
public class SubCategory{

	@Id
	@Column(name="SUBCATEGORYID")
	private int subCategoryId;
	
	@Column(name="SUBCATEGORYNAME")
	private String subCategoryName;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category")
	private Category category;
	
	public SubCategory() {
		
	}

	public SubCategory(int subCategoryId, String subCategoryName, Category category) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.category = category;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SubCategory [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName + ", category="
				+ category + "]";
	}
	 
	
    
}