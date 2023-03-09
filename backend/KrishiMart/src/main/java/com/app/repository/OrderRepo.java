package com.app.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Customer;
import com.app.Entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	List<Order> findByCust(Customer customer);
}
