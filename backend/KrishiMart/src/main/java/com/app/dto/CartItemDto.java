package com.app.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonIgnoreProperties(value = {"productDto","totalPrice","id"},allowGetters = true)
public class CartItemDto {

	private Long id;
	
	private int quantity;

    private double totalPrice;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "productId")
    private Long productId;

    private ProductDto productDto;

	public CartItemDto(Long id,int quantity, double totalPrice, ProductDto productDto) {
		super();
		this.id=id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productDto = productDto;
	}
    
    

}
