package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{

}
