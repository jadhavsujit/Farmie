package com.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
