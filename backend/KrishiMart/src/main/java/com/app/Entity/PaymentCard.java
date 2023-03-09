package com.app.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentCard {
	@Column(length = 20,unique = true)
	private String cardNo;
	private LocalDate expDate;
	@Column(length = 5)
	private String cvv;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private CardType cardType;
	
}
