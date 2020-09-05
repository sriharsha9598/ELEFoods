package com.capfood.elef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	
	@Test
	public void test1_addItem() 
	{
		 Item item = addItem();
		 assertEquals("Paneer Butter Masala", item.getItemName());
		
	}
	@Test
	public void test2_addCategory() 
	{
		 Category category = addCategory();
		 assertEquals("Desserts", category.getCategoryName());
		
	}
	
	@Test
	public void test3_addSubCategory() 
	{
		 SubCategory subCategory = addSubCategory();
		 assertEquals("Ice creams", subCategory.getSubCategoryName());
		
	}
	
	@Test
	public void test4_getItem()
	{
		Item item = addItem();
	    Item item_details = adminService.getItem(item.getItemId());
		assertEquals("Paneer Butter Masala",item_details.getItemName());
	}
	
	@Test
	public void test5_getCategory()
	{
		Category category = addCategory();
		Category category_details = adminService.getCategory(category.getCategoryId());
		assertEquals("Desserts", category_details.getCategoryName());
	}
	
	@Test
	public void test6_getSubCategory()
	{
		SubCategory subCategory = addSubCategory();
		SubCategory subCategory_details = adminService.getSubCategory(subCategory.getSubCategoryId());
		assertEquals("Ice creams", subCategory_details.getSubCategoryName());
	}
	
	@Test
	public void test7_editItem()
	{
		Item item = addItem();
		item.setItemId(item.getItemId());
		item.setItemDescription("Rich, Creamy & Buttery");
	    boolean result = adminService.editItem(item);
		assertEquals(true, result);
	}
	
	@Test
	public void test8_editCategory()
	{
		Category category = addCategory();
		category.setCategoryId(category.getCategoryId());
		category.setCategoryName("Dessert");
		boolean result = adminService.editCategory(category.getBranch().getBranchId(), category);
		assertEquals(true, result);
	}
	
	
	@Test
	public void test9_editSubCategory()
	{
		SubCategory subCategory = addSubCategory();
		subCategory.setSubCategoryId(subCategory.getSubCategoryId());
		subCategory.setSubCategoryName("Ice cream");
		boolean result = adminService.editSubCategory(subCategory.getCategory().getCategoryId(),subCategory);
		assertEquals(true, result);
	}
	
	@Test
	public void test10_deleteItem()
	{
		Item item = addItem();
		boolean result = adminService.deleteItem(item.getItemId());
		assertEquals(true, result);
	}
	
	@Test
	public void test11_NotanAdmin() {
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
