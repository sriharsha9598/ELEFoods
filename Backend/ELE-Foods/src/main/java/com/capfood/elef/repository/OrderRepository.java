package com.capfood.elef.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capfood.elef.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("SELECT count(o) from Order o")
	public Long getCountOfOrder();
	
	@Query("SELECT max(o.orderId) from Order o")
	public int getMaxOfOrderId();

	@Query("SELECT max(o.id) from Order o")
	public int getMaxOfPrimaryOrderId();
	
	@Query("SELECT o.orderStatus from Order o where o.orderId=:orderId")
	public List<String> trackAnOrder(@Param("orderId") int orderId);
	
	@Query("select order from Order order where order.orderId=:orderId")
	public List<Order> getOrders(@Param("orderId") int orderId);
	
}
