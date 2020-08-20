package com.altimetrik.playground.service;


import java.util.Collection;

import com.altimetrik.playground.entities.Stock;

public interface StockService {
	
	
	public Collection<Stock> findAll();
	

	public Collection<Stock> findByIsActive();
	

	public Stock findById(Long id);
	

	public Stock findByName(String name);
	

	public Stock create(Stock product);
	

	public Stock update(Stock product);
	
	
	public void delete(Long id);


	public Collection<Stock> allSortedProducts(String sortKey);

}
