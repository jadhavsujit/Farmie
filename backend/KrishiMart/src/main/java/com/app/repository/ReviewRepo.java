package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Product;
import com.app.Entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

	List<Review> findByProduct(Product product);
}
