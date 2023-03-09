package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.SupplierAddress;

@Repository
public interface SupplierAddressRepo extends JpaRepository<SupplierAddress, Long>{

}
