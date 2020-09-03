package com.capfood.elef.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capfood.elef.dao.CustomerDao;
import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao dao;
	
	@Override
	public List<Item> getABranchItems(int branchId){
		return getABranchDetails(branchId).getItems();
	}
	
	@Override
	public Branch getABranchDetails(int branchId) {
		return dao.getABranchDetails(branchId);
	}

	@Override
	public List<Category> getABranchCategories(int branchId) {
		return getABranchDetails(branchId).getCategory();
	}

	@Override
	public List<SubCategory> getABranchSubCategories(int branchId) {
		List<SubCategory> subCategories = new LinkedList();
		List<Category> categories=getABranchCategories(branchId);
		for(int i=0;i<categories.size();i++) {
			subCategories.addAll(categories.get(i).getSubCategories());
		}
		return subCategories;
	}
	
//	public List<Category> getABranchCategories(int branchId){
//		
//	}
//	
//	public List<SubCategory> getABranchSubCategories(int branchId){
//		
//	}
}
