package com.altimetrik.playground.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.altimetrik.playground.repositories.StockRepository;
import com.altimetrik.playground.service.impl.StockServiceImpl;

public class StockServiceImplTest {

	@InjectMocks
	StockServiceImpl StockService;

	@Mock
	StockRepository productRepository;

	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testfindById() {
		long ids = 100l;
		when(productRepository.findOne(ids)).then(Mockito.any());

		StockService.findById(ids);
	}

	@Test
	public void testfindAll() {
		when(productRepository.findAll()).then(Mockito.any());

		StockService.findAll();
	}

	@Test
	public void testFindByName() {
		String name = "abc";
		when(productRepository.findByName(name)).then(Mockito.any());

		StockService.findByName(name);
	}
}
