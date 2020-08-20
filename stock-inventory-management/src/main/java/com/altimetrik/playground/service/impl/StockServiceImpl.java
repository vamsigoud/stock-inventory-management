package com.altimetrik.playground.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.playground.entities.Stock;
import com.altimetrik.playground.repositories.StockRepository;
import com.altimetrik.playground.service.StockService;

@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockRepository productRepository;

	@Override
	public Collection<Stock> findAll() {
		Collection<Stock> product = productRepository.findAll();
		return product;
	}

	@Override
	public Stock findById(Long id) {
		Stock product = productRepository.findOne(id);
		return product;
	}

	@Override
	public Stock findByName(String name) {
		Stock product = productRepository.findByName(name);
		return product;
	}

	@Override
	public Stock create(Stock product) {
		
		if (product.getName() == null) {
			return null;
		}
		
		productRepository.save(product);
		return product;
	}

	@Override
	public Stock update(Stock product) {
		Stock productPersisted = findById(product.getId());
		if (productPersisted == null) {
			return null;
		}
		
		productRepository.save(product);
		return product;
	}

	@Override
	public void delete(Long id) {
		Stock product = findById(id);
		if (product == null) {
			return;
		}
		if(product.isActive() == true){
			product.setIsActive(false);
		}
		else{
			product.setIsActive(true);
		}
		productRepository.save(product);
		
	}

	@Override
	public Collection<Stock> findByIsActive() {
		
		Collection<Stock> product = productRepository.findByIsActive(true);
		return product;
		
	}

	@Override
	public Collection<Stock> allSortedProducts(String sortKey) {
		Collection<Stock> product = productRepository.allSortedProducts(sortKey);
		return product;
	}

}