package com.app.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "customer")
public class CustomerAddress extends BaseEntity {

	@Column(length = 50)
	private String address;
	@Column(length = 20)
	private String landmark;
	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 20)
	private String country;
	private int pincode;
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;
	
	public CustomerAddress(String address, String landmark, String city, String state, String contry, int pincode) {
		super();
		this.address = address;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = contry;
		this.pincode = pincode;
	}
	
	
	
	
	
	
}
