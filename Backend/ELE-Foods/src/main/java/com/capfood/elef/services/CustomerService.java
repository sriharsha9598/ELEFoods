package com.capfood.elef.services;

import java.util.List;

import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;

public interface CustomerService {

	public List<Item> getABranchItems(int branchId);
	public List<Category> getABranchCategories(int branchId);
	public List<SubCategory> getABranchSubCategories(int branchId);
	public Branch getABranchDetails(int branchId);
}
