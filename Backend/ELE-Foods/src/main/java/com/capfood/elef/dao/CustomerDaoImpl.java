package com.capfood.elef.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capfood.elef.entities.Address;
import com.capfood.elef.entities.Branch;
import com.capfood.elef.entities.CarryBox;
import com.capfood.elef.entities.Category;
import com.capfood.elef.entities.Item;
import com.capfood.elef.entities.Order;
import com.capfood.elef.entities.User;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
    private EntityManager entityManager;
	

	@Override
	public int generateAddressId() {
		int tempId = 1000;

		String command = "SELECT count(address) from Address address";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();

		if (count > 0) {
			command = "SELECT max(address.addressId) from Address address";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			int temp = query2.getSingleResult();
			tempId = temp + 1;
		}
		return tempId;
	}

	@Override
	public int generatePrimaryIdForOrder() {
		int tempId = 100;

		String command = "SELECT count(o) from Order o";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();

		if (count > 0) {
			command = "SELECT max(o.id) from Order o";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			int temp = query2.getSingleResult();
			tempId = temp + 1;
		}
		return tempId;
	}
	
	@Override
	public int generateOrderId() {
		int tempId = 8000;

		String command = "SELECT count(o) from Order o";
		TypedQuery<Long> query = entityManager.createQuery(command, Long.class);
		long count = query.getSingleResult();

		if (count > 0) {
			command = "SELECT max(o.orderId) from Order o";
			TypedQuery<Integer> query2 = entityManager.createQuery(command, Integer.class);
			int temp = query2.getSingleResult();
			tempId = temp + 1;
		}
		return tempId;
	}		
	@Override
    public Branch getABranchDetails(int branchId){
		return entityManager.find(Branch.class, branchId);
	}

	@Override
	public Category getACategoryDetails(int categoryId) {
		return entityManager.find(Category.class, categoryId);
	}
	
	@Override
	public CarryBox getACarryBoxDetails(int carryBoxId) {
		return entityManager.find(CarryBox.class, carryBoxId);
	}

	@Override
	public User getAnUserDetails(String emailId) {
		return entityManager.find(User.class, emailId);
	}

	@Override
	public Address getAnAddressDetails(int addressId) {
		return entityManager.find(Address.class, addressId);
	}
	
	@Override
	public boolean addANewAddress(Address address) {
		try {
		     entityManager.persist(address);
		     return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteAnAddress(int addressId) {
		try {
		entityManager.remove(entityManager.find(Address.class, addressId));
		return true;
		}
		catch(Exception ex) {
			return true;
		}
	}
	
	@Override
	public void placeAnOrder(Order order) {
		entityManager.merge(order);

	}
	
	@Override
	public Order getAnOrderDetails(int orderId) {
		return entityManager.find(Order.class, orderId);
	}
	
	@Override
	public Item getAnItemDetails(int itemId) {
		return entityManager.find(Item.class, itemId);
	}
	
	@Override
	public void updateCarryBox(CarryBox carryBox) {
		entityManager.merge(carryBox);
	}
}
