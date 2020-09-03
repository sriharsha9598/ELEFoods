package com.capfood.elef.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.services.CustomerService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;
	
	
	/* Method:getABranchItems
	 * Type: GetMapping
	 * Description: Called from customer home page to view all the existing items in his location
	 * @param int: branchId
	 * @return List<Item>: a List of all the existing items in the given branch
	*/
	@GetMapping("/getABranchItems/{branchId}")
	public List<Item> getABranchItems(@PathVariable int branchId)
	{
		return service.getABranchItems(branchId);
	}
	
	
	/* Method:getABranchCategories
	 * Type: GetMapping
	 * Description: Called from customer home page to view all the existing categories of items in his location
	 * @param int: branchId
	 * @return List<Category>: a List of all the existing categories of items in the given branch
	*/
	@GetMapping("/getABranchCategories/{branchId}")
	public List<Category> getABranchCategories(@PathVariable int branchId)
	{
		return service.getABranchCategories(branchId);
	}
	
	
	/* Method:getABranchSubCategories
	 * Type: GetMapping
	 * Description: Called from customer home page to view all the existing sub-categories of items in his location
	 * @param int: branchId
	 * @return List<SubCategory>: a List of all the existing sub-categories of items in the given branch
	*/
	@GetMapping("/getABranchSubCategories/{branchId}")
	public List<SubCategory> getABranchSubCategories(@PathVariable int branchId)
	{
		return service.getABranchSubCategories(branchId);
	}
	
	
	/* Method:getABranchDetails
	 * Type: GetMapping
	 * Description: Called from customer home page to get entire info about a branch
	 * @param int: branchId
	 * @return  Branch: a object of a Branch of given branch id
	*/
	@GetMapping("/getABranchDetails/{branchId}")
	public Branch getABranchDetails(@PathVariable int branchId)
	{
		return service.getABranchDetails(branchId);
	}
	
	
}
