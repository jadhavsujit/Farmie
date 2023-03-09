package com.app.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"address","cart","order","reviews","paymentCards"})
public class Customer extends BaseEntity {
    
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 30,unique = true)
	private String email;
	private Long phoneNo;
	@Column(length = 20)
	private String password;
	
	// User 1----->* PaymentCard => uni dir asso between Entity n collection of
    // Composite value type
	@ElementCollection
	@CollectionTable(name = "payment_cards", 
	joinColumns = @JoinColumn(name = "user_id"))
	private List<PaymentCard> paymentCards = new ArrayList<>();
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
	List<CustomerAddress> address=new ArrayList<CustomerAddress>();
	
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private Cart cart;
	
	@OneToMany(mappedBy = "cust",cascade = CascadeType.ALL,orphanRemoval = true)
	List<Order> order=new ArrayList<Order>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE,orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();

	public Customer(String firstName, String lastName, String email, Long phoneNo, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
	}
	
	//helper method to add customer address
	public void addAddress(CustomerAddress c)
	{
		address.add(c);
		c.setCustomer(this);
	}
	
	//helper method to remove customer addresss
	public void removeAddress(CustomerAddress c)
	{
		address.remove(c);
		c.setCustomer(null);
	}
	
	
	//helper method to add cart
	//we don't remove cart so we don't add remove method
	public void addCart(Cart c)
	{
		this.setCart(c);
		c.setCustomer(this);
	}
	
	
	//helper method to add order
	public void addOrder(Order o)
	{
		order.add(o);
		o.setCust(this);
	}
	
	//helper method to remove order
		public void removeOrder(Order o)
		{
			order.remove(o);
			o.setCust(null);
		}
	
	
}
