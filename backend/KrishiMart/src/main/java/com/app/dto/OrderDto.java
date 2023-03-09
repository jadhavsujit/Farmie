package com.app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.app.Entity.CustomerAddress;
import com.app.Entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

	private Long id;
	private Date orderDate;
	private Date deliveryDate;
	private double shippingFee;
	private double totalPrice;
	private AddressDto deliveryAddress;
	private String delivaryStatus;
	public OrderDto(Long id, Date orderDate, Date deliveryDate, double shippingFee, double totalPrice,
			AddressDto deliveryAddress) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.shippingFee = shippingFee;
		this.totalPrice = totalPrice;
		this.deliveryAddress = deliveryAddress;
	}
	
	
	
}
