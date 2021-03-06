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

import com.altimetrik.playground.entities.Supplier;
import com.altimetrik.playground.service.SupplierService;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	

	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Supplier>> getSuppliers(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "enable", required = false) String enable){
		
		Collection<Supplier> suppliers = new ArrayList<Supplier>();
		if (email != null) {
			Supplier supplier = supplierService.findByEmail(email);
			suppliers.add(supplier);
		} else if (enable != null) {
			Collection<Supplier> supplier = supplierService.findByaccountEnabled();
			suppliers.addAll(supplier);
		} else {
			Collection<Supplier> allSuppliers = supplierService.findAll();
			suppliers.addAll(allSuppliers);
		}
		return new ResponseEntity<Collection<Supplier>>(suppliers, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") Long id){
		
		Supplier supplier = supplierService.findById(id);
		if (supplier == null) {
			return new ResponseEntity<Supplier>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		
	}
	

	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
		
		supplier = supplierService.create(supplier);
		if (supplier == null) {
			return new ResponseEntity<Supplier>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier){
		
		supplier = supplierService.update(supplier);
		if (supplier == null) {
			return new ResponseEntity<Supplier>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Long id){
		
		supplierService.delete(id);
		return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
		
	}
	
}
