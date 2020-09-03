package com.capfood.elef.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
    private EntityManager entityManager;
	
	@Override
    public Branch getABranchDetails(int branchId){
		return entityManager.find(Branch.class, branchId);
	}

	@Override
	public Category getACategoryDetails(int categoryId) {
		return entityManager.find(Category.class, categoryId);
	}
	
//	public List<Category> getABranchCategories(int branchId){
//		
//	}
//	
//	public List<SubCategory> getABranchSubCategories(int branchId){
//		
//	}
}
