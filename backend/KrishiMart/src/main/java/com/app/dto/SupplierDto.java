package com.app.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SupplierDto {
 
	@NotBlank(message = "first name should not be blank")
	private String firstName;

	@NotBlank(message = "last name should not be blank")
	private String lastName;
	
	@NotBlank(message = "email should not be blank")
	@Email(message = "Invalid Email Format")
	@Length(min = 5,max=20,message = "Invalid Email length!!!!!!!")
	private String email;
	
	@NotNull(message = "phone no cannot be null")
	
	private Long phoneNo;
	
	@NotBlank(message = "password should not be blank")
	@Length(min = 5,max=20,message = "Invalid password length!!!!!!!")
	private String password;
	
	@NotNull(message = "aadhar no cannot be null")
	private long aadharNo;

	@NotNull(message = "account no cannot be null")
	private long accountNo;
	
}
