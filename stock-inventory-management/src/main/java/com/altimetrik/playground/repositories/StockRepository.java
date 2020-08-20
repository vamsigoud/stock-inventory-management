package com.altimetrik.playground.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altimetrik.playground.entities.Stock;



public interface StockRepository  extends JpaRepository<Stock, Long>{
	
    Stock findByName(@Param("name") String name);
	
	Collection<Stock> findByIsActive(@Param("isActive") boolean isActive);
	
	Stock findOne(@Param("id") Long id);

	@Query("select  * FROM product ORDER BY :sortKey asc")
	Collection<Stock> allSortedProducts(@Param("sortKey") String sortKey);

}
