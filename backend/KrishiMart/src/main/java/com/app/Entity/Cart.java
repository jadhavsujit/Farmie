package com.app.Entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"customer","cartItems"})
public class Cart extends BaseEntity{

	@CreationTimestamp
	private Date createdOn;
	private double totalCartPrice;
	private int totalItems;
	@UpdateTimestamp
	private Date updatedOn;
	
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;
	
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
	List<CartItems> cartItems=new ArrayList<CartItems>();
	
	public Cart(Date createdOn, double totalCartPrice, int totalItems, Date updatedOn) {
		super();
		this.createdOn = createdOn;
		this.totalCartPrice = totalCartPrice;
		this.totalItems = totalItems;
		this.updatedOn = updatedOn;
	}
	
	//helper method to add cart itmes
	public void addCartItem(CartItems ci)
	{
		cartItems.add(ci);
		ci.setCart(this);
	}
	
	//helper method to remove cart items
	public void removeCartItems(CartItems ci)
	{
		cartItems.remove(ci);
		ci.setCart(null);
	}
	
	
	
	
	
}
