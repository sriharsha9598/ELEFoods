package com.capfood.elef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.SubCategory;
import com.capfood.elef.exceptions.NotAnAdminException;
import com.capfood.elef.services.AdminService;

@SpringBootTest
@Transactional
@Rollback(true)
public class AdminApplicationTests {

	@Autowired
	AdminService adminService;
	
	
	public  Item addItem()
	{

		Item item = new Item();
		item.setItemName("Paneer Butter Masala");
		item.setItemDescription("Rich & Creamy");
		item.setItemPrice(180);
		item.setActive(true);
		item.setType("veg");
		Item item_details = adminService.addItem("pravallikakonduru17@gmail.com", 5004, item);
		return item_details;
		
	}

	public Category addCategory()
	{
		Category category = new Category();
		category.setCategoryName("Desserts");
		Category category_details = adminService.addCategory("pravallikakonduru17@gmail.com", category);
		return category_details;
		
	}
	
	public SubCategory addSubCategory()
	{
		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryName("Ice creams");	
		SubCategory subCategory_details = adminService.addSubCategory(7003, subCategory);
		return subCategory_details;
	}
	
	
	/*
	 * check the addItem function
	 */
	
	@Test
	public void test1_addItem() 
	{
		 Item item = addItem();
		 assertEquals("Paneer Butter Masala", item.getItemName());
		
	}
	
	/*
	 * check the add category function
	 */
	@Test
	public void test2_addCategory() 
	{
		 Category category = addCategory();
		 assertEquals("Desserts", category.getCategoryName());
		
	}
	
	
	/*
	 * check the add sub category function
	 */
	@Test
	public void test3_addSubCategory() 
	{
		 SubCategory subCategory = addSubCategory();
		 assertEquals("Ice creams", subCategory.getSubCategoryName());
		
	}
	
	/*
	 * check the get Item function
	 */
	@Test
	public void test4_getItem()
	{
		Item item = addItem();
	    Item item_details = adminService.getItem(item.getItemId());
		assertEquals("Paneer Butter Masala",item_details.getItemName());
	}
	
	/*
	 * check the get category function
	 */
	@Test
	public void test5_getCategory()
	{
		Category category = addCategory();
		Category category_details = adminService.getCategory(category.getCategoryId());
		assertEquals("Desserts", category_details.getCategoryName());
	}
	
	/*
	 * check the sub category function
	 */
	
	@Test
	public void test6_getSubCategory()
	{
		SubCategory subCategory = addSubCategory();
		SubCategory subCategory_details = adminService.getSubCategory(subCategory.getSubCategoryId());
		assertEquals("Ice creams", subCategory_details.getSubCategoryName());
	}
	
	
	/*
	 * check the edit item function
	 */	
	@Test
	public void test7_editItem()
	{
		Item item = addItem();
		item.setItemId(item.getItemId());
		item.setItemDescription("Rich, Creamy & Buttery");
	    boolean result = adminService.editItem(item);
		assertEquals(true, result);
	}
	
	/*
	 * check the edit category function
	 */
	@Test
	public void test8_editCategory()
	{
		Category category = addCategory();
		category.setCategoryId(category.getCategoryId());
		category.setCategoryName("Dessert");
		boolean result = adminService.editCategory(category.getBranch().getBranchId(), category);
		assertEquals(true, result);
	}
	
	/*
	 * check the edit sub category function
	 */
	@Test
	public void test9_editSubCategory()
	{
		SubCategory subCategory = addSubCategory();
		subCategory.setSubCategoryId(subCategory.getSubCategoryId());
		subCategory.setSubCategoryName("Ice cream");
		boolean result = adminService.editSubCategory(subCategory.getCategory().getCategoryId(),subCategory);
		assertEquals(true, result);
	}
	
	/*
	 * check the delete item function
	 */
	@Test
	public void test10_deleteItem()
	{
		Item item = addItem();
		boolean result = adminService.deleteItem(item.getItemId());
		assertEquals(true, result);
	}
	
	/* 
	 * check the delete category function
	 */
	@Test
	public void test11_deleteCategory()
	{
		Category category = addCategory();
		boolean result = adminService.deleteCategory(category.getCategoryId());
		assertEquals(true, result);
	}
	
	/*
	 * check the delete sub category function
	 */
	@Test
	public void test12_deleteSubCategory()
	{
		SubCategory subCategory = addSubCategory();
		boolean result = adminService.deleteSubCategory(subCategory.getSubCategoryId());
		assertEquals(true, result);
	}
	
	/*
	 * check the not an admin exception
	 */
	@Test
	public void test13_NotanAdmin() {
		Item item = new Item();
		item.setItemName("Paneer Butter Masala");
		item.setItemDescription("Rich & Creamy");
		item.setItemPrice(180);
		item.setActive(true);
		item.setType("veg");
		
		
	    assertThrows(NotAnAdminException.class, ()->{
	    	Item item_details = adminService.addItem("aar@gmail.com", 5004, item);
	    });
	    
	    
	}
	
	
	
}
