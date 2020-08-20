package com.altimetrik.playground.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.playground.entities.Customer;


/**
 * Repository used by CustomerService.java to access database.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
	
	Collection<Customer> findByIsActive(@Param("isActive") boolean isActive);   
	
	Customer findOne(@Param("id") Long id);
	
}
