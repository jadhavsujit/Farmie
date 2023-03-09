package com.app.dto;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@ToString
@JsonIgnoreProperties(value = {"id", "reviewDate"}, allowGetters = true)
public class ReviewDto {

	private Long id;
	private String review;
	private double rating;
	private Date reviewDate;
}
