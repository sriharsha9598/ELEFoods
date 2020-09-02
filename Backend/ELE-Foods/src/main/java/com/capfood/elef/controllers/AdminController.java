package com.capfood.elef.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.capfood.elef.services.AdminService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AdminController {

	@Autowired
	AdminService service;
	
}
