package com.capfood.elef.controllers;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capfood.elef.entities.Address;
import com.capfood.elef.entities.CarryBox;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.Order;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.exceptions.OrderContainsInactiveItemsException;
import com.capfood.elef.exceptions.OutOfLocationRangeException;
import com.capfood.elef.exceptions.ResourceNotFoundException;
import com.capfood.elef.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;
	
	@Autowired
	EntityManager em;
	
	
	/* Method:getABranchItems
	 * Type: GetMapping
	 * Description: Called from customer home page to view all the existing items in his location
	 * @param int: branchId
	 * @return List<Item>: a List of all the existing items in the given branch
	*/
	@GetMapping("/getABranchItems/{branchId}")
	public List<Item> getABranchItems(@PathVariable int branchId) throws ResourceNotFoundException
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
	public List<Category> getABranchCategories(@PathVariable int branchId) throws ResourceNotFoundException
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
	
	
	
	/* Method:getACarryBoxDetails
	 * Type: GetMapping
	 * Description: Called from customer carry box page to get a list of items present in one's carrybox and related info
	 * @param int: carryBoxId
	 * @return CarryBox: a object of a CarryBox of given Carry Box id
	*/
	@GetMapping("/getACarryBoxDetails/{emailId}")
	public CarryBox getACarryBoxDetails(@PathVariable String emailId)
	{
		return service.getACarryBoxDetails(emailId);
	}
	
	
	
	/* Method:getAnUserAddresses
	 * Type: GetMapping
	 * Description: Called from customer account page and place order page to get a list of addresses saved
	 * @param String: emailId
	 * @return List<Address>: a list of Address objects saved to a user account
	*/
	@GetMapping("/getAnUserAddresses/{emailId}") 
	public List<Address> getAnUserAddresses(@PathVariable String emailId) throws ResourceNotFoundException
	{
		return service.getAnUserAdresses(emailId);
	}
	
	
	
	/* Method:addANewAddress
	 * Type: PostMapping
	 * Description: Called from customer account page and place order page to add a new address to the list of addresses saved
	 * @param Address: address 
	 * @return boolean: a boolean is returned to notify whether the new address is added or not
	*/
	@PostMapping("/addANewAddress/{emailId}")
	public boolean addANewAddress(@PathVariable String emailId, @RequestBody Address address)
	{
		return service.addANewAddress(emailId,address);
	}
	
	
	
	/* Method:deleteAnAddress
	 * Type: DeleteMapping
	 * Description: Called from customer account page to delete an address from the list of addresses saved
	 * @param Address: address 
	 * @return boolean: a boolean is returned to notify whether the address is deleted or not
	*/
	@GetMapping("/deleteAnAddress/{addressId}")
	public boolean deleteAnAddress(@PathVariable int addressId)
	{
		return service.deleteAnAddress(addressId);
	}
	
	
	/* Method:getAnUserOrders
	 * Type: GetMapping
	 * Description: Called from customer account page to get a list of orders placed
	 * @param String: emailId
	 * @return List<Order>: a list of Order objects placed from the user account
	*/
	@GetMapping("/getAnUserOrders/{emailId}") 
	public List<Order> getAnUserOrders(@PathVariable String emailId) throws ResourceNotFoundException
	{
		System.err.println();
		return service.getAnUserOrders(emailId);
	}
	

		
	@GetMapping("/placeANewOrder/{emailId}/{branchId}/{addressId}")
	public boolean placeANewOrder(@PathVariable String emailId,@PathVariable int branchId,@PathVariable int addressId) throws ResourceNotFoundException,OutOfLocationRangeException,OrderContainsInactiveItemsException{
		System.err.println(emailId);
		return service.placeANewOrder(emailId, branchId, addressId);
	}
	
	/* Method:trackAnOrder
	 * Type: GetMapping
	 * Description: Called from customer account page to get the status of an order placed
	 * @param int: orderId
	 * @return Order: an Order object placed from the user account
	*/
	@GetMapping("/trackAnOrder/{orderId}")
	public Order trackAnOrder(@PathVariable int orderId) throws ResourceNotFoundException
	{
		return service.trackAnOrder(orderId);
	}
	
	
	@GetMapping("addAnItemToCarryBox/{emailId}/{itemId}")
	public boolean addItemToCarryBox(@PathVariable String emailId,@PathVariable int itemId) throws OrderContainsInactiveItemsException{
		System.err.println("Entered Contorller");
		return service.addItemToCarryBox(emailId, itemId);
	
	}

	@GetMapping("/deleteACarryBoxItem/{emailId}/{itemId}")
	public boolean deleteAnCarryBoxItem(@PathVariable String emailId,@PathVariable int itemId)
	{
		return service.deleteACarryBoxItem(emailId,itemId);
	}

	@GetMapping("/updateACarryBoxItem/{emailId}/{itemId}/{quantity}")
	public boolean updateACarryBoxItem(@PathVariable String emailId,@PathVariable int itemId,@PathVariable int quantity){
		return service.updateACarryBoxItem(emailId,itemId,quantity);
	}
	
	
	
//	@GetMapping("/searchItems/{searchText}")
//	public List<Item> searchItems(@PathVariable String searchText){
//		return service.searchItems(searchText);
//	}
//	
	
	
	
}
