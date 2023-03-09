package com.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.OrderDetails;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Long>{

}
