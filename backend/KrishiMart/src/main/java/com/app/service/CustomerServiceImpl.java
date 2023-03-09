package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Entity.Cart;
import com.app.Entity.CartItems;
import com.app.Entity.Customer;
import com.app.Entity.CustomerAddress;
import com.app.Entity.Order;
import com.app.Entity.OrderDetails;
import com.app.Entity.Payment;
import com.app.Entity.PaymentCard;
import com.app.Entity.Product;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDto;
import com.app.dto.CustomerDto;
import com.app.dto.OrderDto;
import com.app.dto.ProductDto;
import com.app.repository.CartItemRepo;
import com.app.repository.CustomerAddressRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.OrderDetailRepo;
import com.app.repository.OrderRepo;

import com.app.repository.PaymentRepo;
import com.app.repository.ProductRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CustomerAddressRepo customerAddressRepo;

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	

	@Override
	public List<CustomerDto> getAllCustomer() {

		return customerRepo.findAll().stream().map((a) -> mapper.map(a, CustomerDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addAddress(AddressDto addressDto, Long customerId) {

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid custoemr"));

		CustomerAddress address = mapper.map(addressDto, CustomerAddress.class);

		System.out.println(address);

		address.setCustomer(customer);

		CustomerAddress customerAddress = customerAddressRepo.save(address);

		customer.getAddress().add(customerAddress);

		return new ApiResponse("address added successfully!!!!!!");
	}

	@Override
	public List<AddressDto> getAllAddress(Long customerId) {

		Customer customer = customerRepo.getReferenceById(customerId);

		if (customer == null) {
			throw new ResourceNotFoundException("invalid customer");
		}

		return customer.getAddress().stream().map(a -> mapper.map(a, AddressDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteCustomer(Long customerId) {

		if (customerRepo.existsById(customerId) == false)
			throw new ResourceNotFoundException("invalid customer");

		customerRepo.deleteById(customerId);

		return new ApiResponse("account removed successfully!!!!!!!!!");
	}

	@Override
	public ApiResponse addCartItem(Long customerId, CartItemDto cartItemDto) {
		Customer customer = customerRepo.getReferenceById(customerId);
		Product product = productRepo.getReferenceById(cartItemDto.getProductId());
		Cart cart = customer.getCart();

		CartItems cartItem = new CartItems(cartItemDto.getQuantity(), (cartItemDto.getQuantity() * product.getPrice()));

		cartItem.setProduct(product);
		cartItem.setCart(customer.getCart());

		cart.setTotalItems(cart.getTotalItems() + cartItemDto.getQuantity());
		cart.setTotalCartPrice(cart.getTotalCartPrice() + cartItem.getTotalPrice());
		cart.setUpdatedOn(new Date());

		CartItems savedCartItem = cartItemRepo.save(cartItem);

		cart.addCartItem(savedCartItem);

		return new ApiResponse("item added successfully");
	}

	@Override
	public List<CartItemDto> getAllCartItems(Long customerId) {

		Customer customer = customerRepo.getReferenceById(customerId);

		List<CartItems> cartItems = customer.getCart().getCartItems();

		List<CartItemDto> allCartItemDto = new ArrayList<CartItemDto>();

		cartItems.forEach(a -> {
			allCartItemDto.add(
					new CartItemDto(a.getId(),a.getQuantity(), a.getTotalPrice(), mapper.map(a.getProduct(), ProductDto.class)));
		});

		return allCartItemDto;
		// return cartItems.stream().map(a -> mapper.map(a,
		// CartItemDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteCartItem(Long cartItemId) {
		
		CartItems cartItem=cartItemRepo.getReferenceById(cartItemId);
		Cart cart=cartItem.getCart();
		cart.setTotalItems(cart.getTotalItems()-cartItem.getQuantity());
		cart.setTotalCartPrice(cart.getTotalCartPrice()-cartItem.getTotalPrice());
		cart.removeCartItems(cartItem);
				
		return new ApiResponse("cart item removed successfully");
	}

	@Override
	public ApiResponse updateCartItemQuantity(Long cartItemId, int quantity) {
		CartItems cartItem=cartItemRepo.getReferenceById(cartItemId);
		Cart cart=cartItem.getCart();
		
		cart.setTotalItems(cart.getTotalItems()-cartItem.getQuantity());
		cart.setTotalCartPrice(cart.getTotalCartPrice()-cartItem.getTotalPrice());
		
		cartItem.setTotalPrice((cartItem.getTotalPrice()/cartItem.getQuantity())*quantity);
		cartItem.setQuantity(quantity);
		
		cart.setTotalItems(cart.getTotalItems()+cartItem.getQuantity());
		cart.setTotalCartPrice(cart.getTotalCartPrice()+cartItem.getTotalPrice());
		
		
		return new ApiResponse("cart updated successfully");
	}

	@Override
	public ApiResponse placeOrder(Long customerId, Long addressId) {
		Customer customer=customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("invallid customer"));;
		Cart cart=customer.getCart();
		Order order=new Order(cart.getTotalCartPrice()+40);
		order.setCust(customer);
		order.setDeliveryAddress(customerAddressRepo.findById(addressId).get());
		Payment payment=new Payment();
		Payment savedPayment=paymentRepo.save(payment);
		order.setPayment(savedPayment);
		
		Order savedOrder= orderRepo.save(order);
		
		List<OrderDetails> orderDetails=new ArrayList<>();
		cart.getCartItems().forEach(a->{
		OrderDetails od= new OrderDetails(a.getQuantity(), a.getTotalPrice());
		od.setOrders(savedOrder);
		od.setProduct(a.getProduct());
		OrderDetails newOd= orderDetailRepo.save(od);
		orderDetails.add(newOd);
		});
		
		savedOrder.setOrderDetails(orderDetails);
		
		customer.getOrder().add(savedOrder);
		
		return new ApiResponse("ordered successfully");
	}

	@Override
	public ApiResponse addPaymentCard(PaymentCard paymentCard,Long customerId) {
		Customer customer=customerRepo.getReferenceById(customerId);
		customer.getPaymentCards().add(paymentCard);
		
		return new ApiResponse("card added succeessfully");
	}

	@Override
	public List<PaymentCard> getAllPaymentCard(Long customerId) {
		Customer customer=customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("invallid customer"));
		Hibernate.initialize(customer.getPaymentCards());
		//The Hibernate.initialize() method forces the collection
		//to be loaded eagerly, even if it was loaded lazily before.
		return customer.getPaymentCards();
	}

	@Override
	public List<OrderDto> getAllOrders(Long customerId) {
		Customer customer=customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("invalid customer id"));
		
		List<OrderDto> orders=new ArrayList<OrderDto>();
		
		return orders;
	}

	
	
}
