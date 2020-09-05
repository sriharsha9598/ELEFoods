package com.capfood.elef.services;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capfood.elef.dao.AdminDao;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.Order;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.entities.User;
import com.capfood.elef.exceptions.NotAnAdminException;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao admin_dao;
	



	@Override
	public Item addItem(String user_name, int subCategory, Item item) {
		User user = admin_dao.getUserDetails(user_name);
		if(user.getRole().equals("Admin")) {
			SubCategory sub_category = admin_dao.getSubCategory(subCategory);
			item.setSubCategory(sub_category);
		    item.setBranch(user.getBranch());
		    item.setItemId(admin_dao.generateItemId());
		    admin_dao.addItem(user_name, item);
		}
		else {
			throw new NotAnAdminException("Sorry..You are not allowed for adding an item");
		}
	    return item;
		
	}
//	

	@Override
	public User getUserDetails(String username) {
		return admin_dao.getUserDetails(username);
	}

	@Override
	public Category getCategory(int category) {
		return admin_dao.getCategory(category);
	}
	
	@Override
	public Item getItem(int itemId) {
		return admin_dao.getItem(itemId);
	}

	
	@Override
	public SubCategory getSubCategory(int subCategory) {
		System.err.println("service"+subCategory);
		return admin_dao.getSubCategory(subCategory);
	}

	
	@Override
	public Category addCategory(String userId, Category category) {
		User user = admin_dao.getUserDetails(userId);
		if(user.getRole().equals("Admin")) {
			category.setCategoryId(admin_dao.generateCategoryId());
			category.setBranch(user.getBranch());
			admin_dao.addCategory(category);
		}
		else {
			throw new NotAnAdminException("Sorry..You are not allowed for adding an item");
		}
		return category;
		
		
		
	}

	@Override
	public SubCategory addSubCategory(int category, SubCategory subCategory) {
		Category category_details = admin_dao.getCategory(category);
		String s=category_details.getBranch().getAdmin().getCustomerName();
		System.err.println(s);
		if(category_details.getBranch().getAdmin().getRole().equals("Admin")) {
		    subCategory.setSubCategoryId(admin_dao.generateSubCategoryId());
			subCategory.setCategory(category_details);
			admin_dao.addSubCategory(subCategory);
		}
		else {
			throw new NotAnAdminException("Sorry..You are not allowed for adding subcategory");
		}
		return subCategory;
		
	}

	@Override
	public boolean editCategory(int branchId, Category category) {
		admin_dao.editCategory(branchId, category);
		return true;
		
	}
	@Override
	public boolean editSubCategory(int categoryId, SubCategory subCategory) {
		
		admin_dao.editSubCategory(categoryId, subCategory);
		return true;
	}

	@Override
	public boolean editItem(Item item) {
		admin_dao.editItem(item);
		return true;
	}
	
	@Override
	public boolean deleteCategory(int categoryId) {
	    admin_dao.deleteCategory(categoryId);
		return true;
		
	}

	@Override
	public boolean deleteSubCategory(int subCategoryId) {
		admin_dao.deleteSubCategory(subCategoryId);
		return true;
		
	}

	@Override
	public boolean deleteItem(int itemId) {
		admin_dao.deleteItem(itemId);
		return true;
		
	}

	
	
	@Override
	public Item getImageDetails(String imageName) {
		return admin_dao.getImageDetails(imageName);
	}

	@Override
	public void uploadImage(MultipartFile file) throws IOException  {
		//Item img = new Item(file.getOriginalFilename(),file.getBytes(), file.getContentType());
		//admin_dao.uploadImage(img);
		
	}

	@Override
	public int getRecentItemId() {
		return admin_dao.getRecentItemId();
	}

	@Override
	public void updateOrderStatus(int orderId, String orderStatus) {
		List<Order> orders = admin_dao.getOrders(orderId);
		for(int i=0;i<orders.size();i++) {
			Order order=orders.get(i);
			if(orderStatus.equals("Accepted")) {
				order.setOrderStatus("Accepted");
				order.setStatusDescription("Your order has been accepted by the store and is being processed");
			}
			else if(orderStatus.equals("Rejected")) {
				order.setOrderStatus("Rejected");
				order.setStatusDescription("Sorry for the inconvenience but we have to cancel your order due to some technical issue");
			}
			else if(orderStatus.equals("Delivered")) {
				order.setOrderStatus("Delivered");
				order.setStatusDescription("Your order has been delivered!Enjoy your meal");
			}
		
		 admin_dao.updateOrderStatus(order);
	}

	


	}
}


//@Override
//public void addItem(MultipartFile file, String user_name, int subCategory, Item item) throws IOException {
//	User user = admin_dao.getUserDetails(user_name);
//	SubCategory sub_category = admin_dao.getSubCategory(subCategory);
//	item.setSubCategory(sub_category);
//    item.setBranch(user.getBranch());
//   // item.setPicture(file.getOriginalFilename());
//    item.setPicByte(file.getBytes());
//   // item.setPic_type(file.getContentType());
//    item.setItemId(admin_dao.generateId());
//    admin_dao.addItem(user_name, item);
//	
//}

