package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDto;
import com.app.dto.LoginDto;
import com.app.dto.SupplierDto;
import com.app.service.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@Validated
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/customer/login")
	public ResponseEntity<?> customerLogIn(@RequestBody @Valid LoginDto cust) {
		return ResponseEntity.ok(authService.customerLogIn(cust));
	}

	@PostMapping("/customer/signup")
	public ResponseEntity<?> customerRegistration(@RequestBody @Valid CustomerDto customer) {
		System.out.println(customer);
		CustomerDto cust = authService.addCustomer(customer);
		return new ResponseEntity<>(cust, HttpStatus.CREATED);
	}

	@PostMapping("/supplier/signup")
	public ResponseEntity<?> supplierRegistration(@RequestBody @Valid SupplierDto supplier) {
		System.out.println(supplier);
		SupplierDto sup = authService.addSupplier(supplier);
		return new ResponseEntity<>(sup, HttpStatus.CREATED);
	}
	
	@PostMapping("/supplier/login")
	public ResponseEntity<?> supplierLogIn(@RequestBody @Valid LoginDto sup) {
		return ResponseEntity.ok(authService.supplierLogIn(sup));
	}

}
