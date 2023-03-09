package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.PaymentCard;
import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDto;
import com.app.dto.CustomerDto;
import com.app.dto.OrderDto;
import com.app.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<?> getAllCustomer() {
		List<CustomerDto> allCustomer = customerService.getAllCustomer();
		if (allCustomer.size() == 0) {
			return new ResponseEntity<>(new ApiResponse("no customer found"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(allCustomer, HttpStatus.OK);

	}

	@PostMapping("/{custId}/address")
	public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDto addressDto, @PathVariable Long custId) {
		return ResponseEntity.ok(customerService.addAddress(addressDto, custId));
	}

	@GetMapping("/{custId}/address")
	public ResponseEntity<?> getAllAddress(@PathVariable Long custId) {
		List<AddressDto> allAddressDto = customerService.getAllAddress(custId);
		if (allAddressDto.size() == 0)
			return new ResponseEntity<>(new ApiResponse("no address found"), HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(allAddressDto);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.deleteCustomer(customerId));
	}

	@PostMapping("/{customerId}/cart/add")
	public ResponseEntity<?> addCartItem(@PathVariable Long customerId, @RequestBody CartItemDto cartItemDto) {
		return ResponseEntity.ok(customerService.addCartItem(customerId, cartItemDto));
	}

	@PostMapping("/{customerId}/cart")
	public ResponseEntity<?> getAllCartItems(@PathVariable Long customerId) {
		List<CartItemDto> allCartItem = customerService.getAllCartItems(customerId);
		if (allCartItem.size() == 0)
			return ResponseEntity.ok(new ApiResponse("no cart items"));

		return ResponseEntity.ok(allCartItem);
	}

	@PostMapping("/cart/delete/cartItem")
	public ResponseEntity<?> deleteCartItem(@RequestParam Long cartItemId) {

		return ResponseEntity.ok(customerService.deleteCartItem(cartItemId));
	}

	@PostMapping("/cart/cartItem/update/quantity")
	public ResponseEntity<?> updateCartItemQuantity(@RequestParam Long cartItemId, @RequestParam int quantity) {

		return ResponseEntity.ok(ResponseEntity.ok(customerService.updateCartItemQuantity(cartItemId,quantity)));
	}
	
	@PostMapping("/place/order")
	public ResponseEntity<?> placeOrder(@RequestParam Long customerId,@RequestParam Long addressId)
	{
		
		return ResponseEntity.ok(customerService.placeOrder(customerId,addressId));
	}
	
	@PostMapping("{customerId}/add/card")
	public ResponseEntity<?> addPaymentCard(@RequestBody PaymentCard paymentCard,@PathVariable Long customerId)
	{
		
		return ResponseEntity.ok(customerService.addPaymentCard(paymentCard,customerId));
	}
	
	@GetMapping("{customerId}/payment/card")
	public ResponseEntity<?> getAllPaymentCard(@PathVariable Long customerId)
	{
		List<PaymentCard> allPaymentCard=customerService.getAllPaymentCard(customerId);
		if(allPaymentCard.size()==0)
			return new ResponseEntity<>(new ApiResponse("no cards added"),HttpStatus.OK);
		return ResponseEntity.ok(allPaymentCard);
	}
	
	@GetMapping("/{customerId}/orders")
	public ResponseEntity<?> getAllOrders(@PathVariable Long customerId)
	{
		List<OrderDto> allOrder=customerService.getAllOrders(customerId);
		if(allOrder.size()==0)
			return ResponseEntity.ok(new ApiResponse("no order found"));
		
		return ResponseEntity.ok(allOrder);
	}

}
