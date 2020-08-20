package com.altimetrik.playground.repositories;


import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.altimetrik.playground.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	Collection<Sale> findByTransactionDate(Date date);
	Sale findOne(@Param("id") Long id);
}
