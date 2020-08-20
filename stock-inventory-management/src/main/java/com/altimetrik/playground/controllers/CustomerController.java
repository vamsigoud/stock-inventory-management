package com.altimetrik.playground.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.playground.entities.Customer;
import com.altimetrik.playground.entities.User;
import com.altimetrik.playground.service.CustomerService;



@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@RequestMapping(
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomers(@RequestParam(value = "enable", required = false) String enable){
		
		Collection<Customer> customers = new ArrayList<Customer>();
		if(enable != null){
			Collection<Customer> customer = customerService.findByIsActive();
			customers.addAll(customer);
		} else {
			Collection<Customer> customer = customerService.findAll();
			customers.addAll(customer);
		}
		return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);	
	}

	@RequestMapping(
			value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
		
		Customer customer = customerService.findById(id);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	

	@RequestMapping(
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		
		customer = customerService.create(customer);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateUser(@RequestBody Customer customer){
		
		customer = customerService.update(customer);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
		
		customerService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
}