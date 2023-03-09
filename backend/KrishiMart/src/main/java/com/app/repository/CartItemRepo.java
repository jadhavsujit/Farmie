package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.CartItems;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems, Long>{

}
