package com.altimetrik.playground.service.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.playground.entities.Stock;
import com.altimetrik.playground.entities.Purchase;
import com.altimetrik.playground.entities.Supplier;
import com.altimetrik.playground.repositories.PurchaseRepository;
import com.altimetrik.playground.service.StockService;
import com.altimetrik.playground.service.PurchaseService;
import com.altimetrik.playground.service.SupplierService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private StockService productService;

	@Override
	public Collection<Purchase> findAll() {
		
		Collection<Purchase> purchase = purchaseRepository.findAll();
		return purchase;
		
	}

	@Override
	public Collection<Purchase> findByDate(Date date) {
		
		Collection<Purchase> purchase = purchaseRepository.findBytransactionDate(date);
		return purchase;
		
	}

	@Override
	public Purchase create(Long supplierId, Long productId, Integer quantity) {
		
		Supplier supplier = supplierService.findById(supplierId);
		Stock product = productService.findById(productId);
		if ( product.getQuantity() < quantity ) {
			return null;
		}
		
		long newProductQuantity = product.getQuantity() - quantity;
		product.setQuantity(newProductQuantity);
		Purchase purchase = new Purchase(product, supplier, quantity);
		purchaseRepository.save(purchase);
		return purchase;
		
	}
	
}