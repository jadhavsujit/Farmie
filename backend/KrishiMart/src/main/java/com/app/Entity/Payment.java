package com.app.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Payment extends BaseEntity {
	
	@Column(length = 20)
	private String paymentMethod;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentStatus paymentStatus;
	@CreationTimestamp
	private Date paymentDate;
	
	public Payment()
	{
		this.paymentMethod="card";
		this.paymentStatus=paymentStatus.SUCCESS;
		this.paymentDate=new Date();
	}
	

}
