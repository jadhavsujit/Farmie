package com.app.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class ProductDto {

	@NotNull
	private Long catId;
	@NotBlank
	private String name;
	@NotNull
	private double price;
	
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	


	
}
