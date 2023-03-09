package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cart","product"})
public class CartItems extends BaseEntity {

	private int quantity;
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public CartItems(int quantity, double totalPrice) {
		super();
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	
	
}
