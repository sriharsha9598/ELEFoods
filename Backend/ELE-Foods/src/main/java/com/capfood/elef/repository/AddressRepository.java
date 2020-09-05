package com.capfood.elef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capfood.elef.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	@Query("SELECT count(a) from Address a")
	public Long getCountOfAddress();
	
	@Query("SELECT max(a.addressId) from Address a")
	public int getMaxOfAddressId();
	
}
