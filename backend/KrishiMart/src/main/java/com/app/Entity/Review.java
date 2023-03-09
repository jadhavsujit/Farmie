package com.app.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"customer","product"})
public class Review extends BaseEntity{
	
	
	private String review;
	private double rating;
	
	@CreationTimestamp
	private Date reviewDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_id")
	private Customer customer;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "prod_id")
	private Product product;


	public Review(String review, double rating) {
		super();
		this.review = review;
		this.rating = rating;
		this.reviewDate = new Date();
	}


	
	
	
	
	
	

}
