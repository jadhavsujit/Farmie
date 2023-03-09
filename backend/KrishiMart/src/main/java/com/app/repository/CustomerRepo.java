package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

	
	 boolean existsByEmail(String email);
	 
	 Customer findByEmailAndPassword(String email,String password);
	 
}
