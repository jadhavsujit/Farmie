package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

}
