package br.com.leonardothizon.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardothizon.shoppingcart.entity.CustomerEntity;
import br.com.leonardothizon.shoppingcart.repository.CustomerRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	CustomerRepository customers;
	
	@PostMapping("/auth")
    public CustomerEntity getUser(@RequestBody String userName) {
		System.out.println("Logando como " + userName);
		CustomerEntity customer = customers.findByUsername(userName);
		if (customer != null) {
			return customer;
		}
		return null;
    }
	
}
