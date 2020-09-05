package com.capfood.elef.controllers;

import java.util.List;
import java.util.Set;

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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request
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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request
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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request	
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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request
	*/
	@PostMapping("/addANewAddress/{emailId}")
	public boolean addANewAddress(@PathVariable String emailId, @RequestBody Address address) throws ResourceNotFoundException
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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request
	*/
	@GetMapping("/getAnUserOrders/{emailId}") 
	public List<Order> getAnUserOrders(@PathVariable String emailId) throws ResourceNotFoundException
	{
		System.err.println();
		return service.getAnUserOrders(emailId);
	}
	
	
	/* Method:getAnUserOrders
	 * Type: GetMapping
	 * Description: Called from customer account page to get a list of orders placed
	 * @param String: emailId
	 * @return List<Order>: a list of Order objects placed from the user account
	*/
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
	 * @throws ResourceNotFoundException : It is raised when no data is found with the given request
	*/
	@GetMapping("/trackAnOrder/{orderId}")
	public Order trackAnOrder(@PathVariable int orderId) throws ResourceNotFoundException
	{
		return service.trackAnOrder(orderId);
	}
	
	
	/* Method:addAnItemToCarryBox
	 * Type: GetMapping
	 * Description: Called from items page to add a desired item to carry box before buying
	 * @param String:emailId
	 * @param int: itemId
	 * @return boolean: a boolean is returned to notify whether the item is added to carry box or not
	*/
	@GetMapping("addAnItemToCarryBox/{emailId}/{itemId}")
	public boolean addItemToCarryBox(@PathVariable String emailId,@PathVariable int itemId){
		return service.addItemToCarryBox(emailId, itemId);	
	}

	
	/* Method:deleteACarryBoxItem
	 * Type: GetMapping
	 * Description: Called from Carry Box page to delete a item from carry box when not needed
	 * @param String:emailId
	 * @param int: itemId
	 * @return boolean: a boolean is returned to notify whether the item is deleted from carry box or not
	*/	
	@GetMapping("/deleteACarryBoxItem/{emailId}/{itemId}")
	public boolean deleteACarryBoxItem(@PathVariable String emailId,@PathVariable int itemId)
	{
		return service.deleteACarryBoxItem(emailId,itemId);
	}
	
	
	/* Method:updateACarryBoxItem
	 * Type: GetMapping
	 * Description: Called from Carry Box page to update the quantity of an item in carry box
	 * @param String:emailId
	 * @param int: itemId
	 * @param int: quantity
	 * @return boolean: a boolean is returned to notify whether an item in the carry box is updated or not
	*/
	@GetMapping("/updateACarryBoxItem/{emailId}/{itemId}/{quantity}")
	public boolean updateACarryBoxItem(@PathVariable String emailId,@PathVariable int itemId,@PathVariable int quantity){
		return service.updateACarryBoxItem(emailId,itemId,quantity);
	}
	
	
	/* Method:searchItems
	 * Type: GetMapping
	 * Description: Called from home page to search for desired items or categories
	 * @param int: branchId
	 * @param String: searchText
	 * @return Set<Item>: a set of items related to the search text is returned
	*/
	@GetMapping("/searchItems/{branchId}/{searchText}")
	public Set<Item> searchItems(@PathVariable int branchId, @PathVariable String searchText){
		return service.searchItems(branchId,searchText);
	}
	
	
}
