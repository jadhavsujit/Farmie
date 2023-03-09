package com.app.service;

import java.util.List;

import com.app.Entity.PaymentCard;
import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDto;
import com.app.dto.CustomerDto;
import com.app.dto.OrderDto;

public interface CustomerService {

	List<CustomerDto> getAllCustomer();
	
	ApiResponse addAddress(AddressDto addressDto,Long customerId);
	
	List<AddressDto> getAllAddress(Long customerId);
	
	ApiResponse deleteCustomer(Long customerId);

	ApiResponse addCartItem(Long customerId, CartItemDto cartItemDto);

	List<CartItemDto> getAllCartItems(Long customerId);

	ApiResponse deleteCartItem(Long cartItemId);

	ApiResponse updateCartItemQuantity(Long cartItemId, int quantity);

	ApiResponse placeOrder(Long customerId,Long addressId);

	ApiResponse addPaymentCard(PaymentCard paymentCard,Long customerId);

	List<PaymentCard> getAllPaymentCard(Long customerId);

	List<OrderDto> getAllOrders(Long customerId);
}
