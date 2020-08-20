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

import com.altimetrik.playground.entities.Stock;
import com.altimetrik.playground.service.StockService;

@RestController
@RequestMapping("api/v1/products")
public class StockController {
	
	@Autowired
	private StockService productService;
	

	@RequestMapping(method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Stock>> getProduct(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enable", required = false) String enable,@RequestParam(value = "sortKey", required = false) String sortKey ) {
		
		Collection<Stock> products = new ArrayList<Stock>();
		if (name != null) {
			Stock product = productService.findByName(name);
			products.add(product);
		} else if (enable != null) {
			Collection<Stock> product = productService.findByIsActive();
			products.addAll(product);
		}else if(sortKey != null) {
			Collection<Stock> allProducts = productService.allSortedProducts(sortKey);
			products.addAll(allProducts);
		} else {
			Collection<Stock> allProducts = productService.findAll();
			products.addAll(allProducts);
		}
		return new ResponseEntity<Collection<Stock>>(products, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> getProductById(@PathVariable("id") Long id){
		
		Stock product= productService.findById(id);
		if (product == null) {
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Stock>(product, HttpStatus.OK);
		
	}
	

	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> createProduct(@RequestBody Stock product){
		
		product = productService.create(product);
		if (product == null) {
			return new ResponseEntity<Stock>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Stock>(product, HttpStatus.CREATED);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> updateProduct(@RequestBody Stock product){
		
		product = productService.update(product);
		if (product == null) {
			return new ResponseEntity<Stock>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Stock>(product, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE)
	public ResponseEntity<Stock> deleteProduct(@PathVariable("id") Long id){
		
		productService.delete(id);
		return new ResponseEntity<Stock>(HttpStatus.NO_CONTENT);
		
	}

}
