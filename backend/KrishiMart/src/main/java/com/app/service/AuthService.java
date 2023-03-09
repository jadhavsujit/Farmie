package com.app.service;

import com.app.Entity.Customer;
import com.app.dto.CustomerDto;
import com.app.dto.LoginDto;
import com.app.dto.SupplierDto;

public interface AuthService {

	CustomerDto addCustomer(CustomerDto customer);
	
	
	CustomerDto customerLogIn(LoginDto customer);
	
	SupplierDto addSupplier(SupplierDto supplier);

	SupplierDto supplierLogIn(LoginDto supplier);
	
}
