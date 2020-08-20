package com.altimetrik.playground.repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.altimetrik.playground.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	
	Collection<Purchase> findBytransactionDate(@Param("date") Date date);

}
