package com.capfood.elef.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao{
	
	@Autowired
    private EntityManager entityManager;
}
