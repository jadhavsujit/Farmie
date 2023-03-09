package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entity.Supplier;
import com.app.Entity.SupplierProductInventory;

@Repository
public interface SupplierProductInventoryRepo extends JpaRepository<SupplierProductInventory, Long> {

	List<SupplierProductInventory> findBySupplier(Supplier supplier);
	
}
