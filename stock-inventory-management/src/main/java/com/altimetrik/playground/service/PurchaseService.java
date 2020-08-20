package com.altimetrik.playground.service;

import java.util.Collection;
import java.util.Date;

import com.altimetrik.playground.entities.Purchase;


public interface PurchaseService {

	/**
	 * Get all purchase transactions in the system.
	 * 
	 * @return collection of all purchase transactions in the system
	 */
	public Collection<Purchase> findAll();
	
	/**
	 * Find a purchase by transaction date.
	 * 
	 * @param date the transaction's date
	 * @return the transaction object if found else return null
	 */
	public Collection<Purchase> findByDate(Date date);
	
	/**
	 * Create new Purchase.
	 * 
	 * @param purchase the purchase to be created
	 * @return the created purchase
	 */
	public Purchase create(Long supplierId, Long productId, Integer quantity);
	
}
