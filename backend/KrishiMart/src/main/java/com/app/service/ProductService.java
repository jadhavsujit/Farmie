package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ReviewDto;

public interface ProductService {

	List<ProductDto> getAllProduct();
	
	ApiResponse deleteProductById(Long productId);

	ApiResponse addReview(String description,double rating,Long customerId,Long productId);

	List<ReviewDto> getProductReviews(Long productId);
}
