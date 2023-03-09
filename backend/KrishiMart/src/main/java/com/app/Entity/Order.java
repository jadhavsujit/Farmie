package com.app.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cust","payment","orderDetails","deliveryAddress"})
public class Order extends BaseEntity{

	@CreationTimestamp
	private Date orderDate;
	private Date deliveryDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
	private OrderStatus orderStatus;
	private double shippingFee;
	private double totalPrice;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private CustomerAddress deliveryAddress;
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer cust;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
	List<OrderDetails> orderDetails=new ArrayList<>();

	
	
	//helper method to add order details
	public void addOrderDetails(OrderDetails od)
	{
		orderDetails.add(od);
		od.setOrders(this);
	}
	
	
	//helper method to remove order details
	public void removeOrderDetails(OrderDetails od)
	{
		orderDetails.remove(od);
		od.setOrders(null);
	}


	public Order( double totalPrice) {
		super();
		this.orderDate=new Date();
		long milisecond=4*24*60*60*1000;
		long ms=orderDate.getTime();
		this.deliveryDate = new Date(milisecond+ms);
		this.shippingFee = 40;
		this.orderStatus=OrderStatus.SUCCESSFULL;
		this.totalPrice = totalPrice;
	}
		
}
