package com.capfood.elef.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.entities.User;

public interface AdminService {

	//void addItem(MultipartFile file, String user_name,int subCategory, Item item) throws IOException;

	Item addItem(String user_name,int subCategory, Item item);
	
	User getUserDetails(String username);

	SubCategory getSubCategory( int subCategory);

	Category addCategory(String admin, Category category);

	SubCategory addSubCategory(int category, SubCategory subCategory);

	boolean editCategory(int branchId, Category category);

	boolean deleteCategory(int categoryId);

	boolean deleteSubCategory(int subCategoryId);

	boolean editItem(Item item);

	boolean deleteItem(int itemId);

	boolean editSubCategory(int categoryId, SubCategory subCategory);

	Item getImageDetails(String imageName);

	void uploadImage(MultipartFile file) throws IOException;

	Category getCategory(int category);

	Item getItem(int itemId);

	int getRecentItemId();

	void updateOrderStatus(int orderId, String orderStatus);

}
