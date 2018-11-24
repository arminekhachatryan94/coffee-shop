package com.api.controllers;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datastax.driver.core.utils.UUIDs;
import com.api.models.Customer;
import com.api.services.CustomerService;
import org.springframework.stereotype.Controller;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("customers")
public class CustomerController {
 
	@Autowired
	private CustomerService customerService;
 
    @GetMapping("/")  
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable UUID id) {
		Customer customer = customerService.getCustomer(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	// @GetMapping("/customers/{first_name}")
	// public Customer getCustomer(@PathVariable(value="first_name") String first_name){
	// 	return customerService.getCustomerByFirstName(first_name);
	// }
}
