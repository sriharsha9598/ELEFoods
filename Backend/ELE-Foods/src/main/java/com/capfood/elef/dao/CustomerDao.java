package com.capfood.elef.dao;

import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.Category;

public interface CustomerDao {

	public Branch getABranchDetails(int branchId);
	public Category getACategoryDetails(int categoryId);
}
