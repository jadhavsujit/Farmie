package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long>{

	
	 boolean existsByEmail(String email);
	 
	 Supplier findByEmailAndPassword(String email,String password);
	 
}