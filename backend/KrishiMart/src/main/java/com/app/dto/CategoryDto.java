package com.app.dto;



import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class CategoryDto {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@NotBlank(message = "name can't be blank")
	private String name;
	
	private String description;
	
	
}
