package com.capfood.elef.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capfood.elef.dao.CustomerDao;
import com.capfood.elef.entities.Address;
import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.CarryBox;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.Order;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.entities.User;
import com.capfood.elef.exceptions.OrderContainsInactiveItemsException;
import com.capfood.elef.exceptions.OutOfLocationRangeException;
import com.capfood.elef.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao dao;
	
	@Override
	public List<Item> getABranchItems(int branchId) throws ResourceNotFoundException{
		try {
		return dao.getABranchDetails(branchId).getItems();
		}
		catch(Exception ex) {
			throw new ResourceNotFoundException("No items available currently in this Branch!!");
		}
	}

	@Override
	public List<Category> getABranchCategories(int branchId) throws ResourceNotFoundException {
		try {
		return dao.getABranchDetails(branchId).getCategory();
		}
		catch(Exception ex) {
			throw new ResourceNotFoundException("No Categories found in this Branch currently!!");
		}
	}

	@Override
	public List<SubCategory> getABranchSubCategories(int branchId) {
		List<SubCategory> subCategories = new ArrayList<>();
		List<Category> categories=getABranchCategories(branchId);
		for(int i=0;i<categories.size();i++) {
			subCategories.addAll(categories.get(i).getSubCategories());
		}
		return subCategories;
	}

	@Override
	public CarryBox getACarryBoxDetails(String emailId) throws ResourceNotFoundException{
		try {
		return dao.getAnUserDetails(emailId).getCarryBox();
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Carry Box not Found!!");
			
		}
	}

	@Override
	public List<Address> getAnUserAdresses(String emailId) throws ResourceNotFoundException {
		try {
		return dao.getAnUserDetails(emailId).getAddress();
		}
		catch(Exception ex) {
			throw new ResourceNotFoundException("No Addresses added to your account!!");
		}
	}

	@Override
	public boolean addANewAddress(String emailId,Address address) {
		try {
			User user=new User();
			user=dao.getAnUserDetails(emailId);
			address.setAddressId(dao.generateAddressId());
			user.addAddress(address);
			dao.addANewAddress(address);
		    return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteAnAddress(int addressId) {
		try {
			boolean b= dao.deleteAnAddress(addressId);
			return b;
		}
		catch(Exception ex) {
			return false;
		}
		
	}

	@Override
	public List<Order> getAnUserOrders(String emailId) throws ResourceNotFoundException {
		try {
		return dao.getAnUserDetails(emailId).getMyOrders();
		}
		catch(Exception ex) {
			throw new ResourceNotFoundException("No Orders found from your Account!!");
			
		}
	}

	@Override
	public boolean placeANewOrder(String emailId,int branchId,int addressId)throws ResourceNotFoundException,OutOfLocationRangeException,OrderContainsInactiveItemsException {
	
			User user=new User();
			user=dao.getAnUserDetails(emailId);
			Branch branch=new Branch();
			branch=dao.getABranchDetails(branchId);
			Address address=dao.getAnAddressDetails(addressId);
			
			try {
	//If carry box is empty when placing order, exception is raised
			if(user.getCarryBox().getItemlist().size()==0) {
				throw new ResourceNotFoundException("Your CarryBox is empty!! Please add some items to proceed..");
			}
			}
			catch(Exception ex) {
				throw new ResourceNotFoundException("Your CarryBox is empty!! Please add some items to proceed..");
			}
			
			List<Item> itemList=user.getCarryBox().getItemlist();
			
			
	//If the order's address is away from the branch's location, exception is raised
			for(int i=0;i<user.getAddress().size();i++) {
				if(user.getAddress().get(i).getAddressId()==addressId) {
					if(!(user.getAddress().get(i).getCity().equalsIgnoreCase(branch.getBranchName()))){
						throw new OutOfLocationRangeException("Sorry, we could not deliver to this location!!");
					}
				}
			}
			
	//If carry box contains inactive items when placing order, exception is raised
			for(int i=0;i<itemList.size();i++) {
				if(!itemList.get(i).isActive()) {
					throw new OrderContainsInactiveItemsException("Sorry, Some Items in your Carry Box are currently Inactive!!");
				}
			}
		    Order order=new Order();
	    	order.setBranch_order(branch);
	    	order.setCustomer(user);
	    	order.setOrderId(dao.generateOrderId());
	    	order.setAddress(address);
	    	order.setOrderDate(LocalDate.now());
	    	order.setOrderPrice(user.getCarryBox().getTotal_cost());
	    	order.setOrderStatus("Placed");
	    	order.setOrderTime(LocalTime.now());
	    	order.setStatusDescription("Your Order has been placed and you will updated soon about your delivery");
	    	
	   //adding the order with a list of items
	    	for(int i=0;i<itemList.size();i++) {
		    	order.setId(dao.generatePrimaryIdForOrder());
		    	order.setItem(itemList.get(i));
		    	order.setQuantity(itemList.get(i).getQuantity());
		        user.getMyOrders().add(order);			      
		    	dao.placeAnOrder(order);
	    	}
	    	
	   //after placing the order successfully, clearing the carry box
	    	while(user.getCarryBox().getItemlist().size()!=0) {
	    		user.getCarryBox().removeItem(user.getCarryBox().getItemlist().get(0));
	    	}
	    	
	   //after placing the order successfully, carryBox's cost is set to zero
	    	user.getCarryBox().setTotal_cost(0);
		    dao.updateCarryBox(user.getCarryBox());
			return true;	
	}
	
	
	@Override
	public Order trackAnOrder(int orderId) throws ResourceNotFoundException {
		try {
		return dao.getAnOrderDetails(orderId);
		}
		catch(Exception ex) {
			throw new ResourceNotFoundException("No Such Order Found!!");
		}
	}
	
	
	@Override
	public boolean addItemToCarryBox(String emailId, int itemId){
		try {
		Item item=dao.getAnItemDetails(itemId);
		User user=new User();
		user=dao.getAnUserDetails(emailId);
		CarryBox carryBox=new CarryBox();
		carryBox=dao.getACarryBoxDetails(user.getCarryBox().getBoxId());
		item.setQuantity(1);
		carryBox.addItem(item);
		
	//Increasing the carry box cost on adding an item to carry box
		carryBox.setTotal_cost(carryBox.getTotal_cost()+item.getItemPrice());
		dao.updateCarryBox(carryBox);
		return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public boolean deleteACarryBoxItem(String emailId,int itemId) {
		try {
		User user=new User();
		user=dao.getAnUserDetails(emailId);
		CarryBox carryBox=new CarryBox();
		carryBox=dao.getACarryBoxDetails(user.getCarryBox().getBoxId());
		Item item=dao.getAnItemDetails(itemId);
	
	//subtracting the cost of item from the cary box's cost when deleting an item from carry box
		for(int i=0;i<carryBox.getItemlist().size();i++) {
			if(carryBox.getItemlist().get(i).getItemId()==itemId) {
				carryBox.setTotal_cost(carryBox.getTotal_cost()-(carryBox.getItemlist().get(i).getQuantity()*item.getItemPrice()));
			}
		}
		carryBox.removeItem(item);
		dao.updateCarryBox(carryBox);
		return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean updateACarryBoxItem(String emailId, int itemId, int quantity) {
		try {
		User user=new User();
		user=dao.getAnUserDetails(emailId);
		CarryBox carryBox=new CarryBox();
		carryBox=dao.getACarryBoxDetails(user.getCarryBox().getBoxId());
		Item item=dao.getAnItemDetails(itemId);
		
	//increasing or decreasing the carry box's cost on updating quantity of any item in the carry box
		for(int i=0;i<carryBox.getItemlist().size();i++) {
			if(carryBox.getItemlist().get(i).getItemId()==itemId) {
					carryBox.setTotal_cost(carryBox.getTotal_cost()-(carryBox.getItemlist().get(i).getQuantity()*item.getItemPrice()));
					carryBox.getItemlist().get(i).setQuantity(quantity);
					carryBox.setTotal_cost(carryBox.getTotal_cost()+(carryBox.getItemlist().get(i).getQuantity()*item.getItemPrice()));
					break;
			}		
		}
		dao.updateCarryBox(carryBox);
		return true;
		}
		catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Set<Item> searchItems(int branchId,String text) {
		CharSequence searchText=text.toLowerCase();
		Set<Item> items = new HashSet<>();
		List<SubCategory> subCategories=getABranchSubCategories(branchId);
		
		Branch branch=dao.getABranchDetails(branchId);
		
	//Searching the items with the search text in their names
		for(int i=0;i<branch.getItems().size();i++) {
			if(branch.getItems().get(i).getItemName().toLowerCase().contains(searchText)) {
				items.add(branch.getItems().get(i));
			}
		}

	//Searching the categories with the search text in their names
		for(int i=0;i<subCategories.size();i++) {
			if(subCategories.get(i).getSubCategoryName().toLowerCase().contains(searchText)) {
				for(int j=0;j<subCategories.get(i).getItems().size();j++) {
					items.add(subCategories.get(i).getItems().get(j));
				}
			}
		}
		return items;
	}
	
}
