package com.altimetrik.playground.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.playground.entities.User;



/**
 * Repository used by UserService to access database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);
    
    Collection<User> findByAccountEnabled(@Param("accountEnabled") boolean accountEnabled);

	User findOne(@Param("id") Long id);

    
}
