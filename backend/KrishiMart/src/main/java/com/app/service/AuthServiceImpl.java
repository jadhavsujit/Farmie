package com.app.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Entity.Cart;
import com.app.Entity.Customer;
import com.app.Entity.Supplier;
import com.app.custom_exceptions.CustomerAlreadyExistException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CustomerDto;
import com.app.dto.LoginDto;
import com.app.dto.SupplierDto;
import com.app.repository.CartRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.SupplierRepo;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private SupplierRepo supplierRepo;
	
	@Autowired
	private CartRepo cartRepo;

	@Override
	public CustomerDto addCustomer(CustomerDto customer) {

		Customer cust = mapper.map(customer, Customer.class);
		
		Cart cart=new Cart(new Date(), 0, 0, new Date());
		cust.setCart(cart);
		

		if (custRepo.existsByEmail(cust.getEmail())) {
			throw new CustomerAlreadyExistException(
					"customer already exist with " + cust.getEmail() + " this email id");
		}

		Customer savedCustomer=custRepo.save(cust);
		cart.setCustomer(savedCustomer);
		cartRepo.save(cart);
		return mapper.map(savedCustomer, CustomerDto.class);

	}

	@Override
	public CustomerDto customerLogIn(LoginDto customer) {
		System.out.println(customer);
		Customer cust = custRepo.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
		System.out.println(cust);
		if (cust == null) {
			throw new ResourceNotFoundException("Invalid email or password");
		}

		return mapper.map(cust, CustomerDto.class);
	}

	@Override
	public SupplierDto addSupplier(SupplierDto supplier) {
		Supplier sup = mapper.map(supplier, Supplier.class);

		if (custRepo.existsByEmail(sup.getEmail())) {
			throw new CustomerAlreadyExistException("supplier already exist with " + sup.getEmail() + " this email id");
		}
		return mapper.map(supplierRepo.save(sup), SupplierDto.class);
	}

	@Override
	public SupplierDto supplierLogIn(LoginDto supplier) {
		System.out.println(supplier);
		Supplier sup = supplierRepo.findByEmailAndPassword(supplier.getEmail(), supplier.getPassword());
		System.out.println(sup);
		if (sup == null) {
			throw new ResourceNotFoundException("Invalid email or password");
		}

		return mapper.map(sup, SupplierDto.class);
	}

}
