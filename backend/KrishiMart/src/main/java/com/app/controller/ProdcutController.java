package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ReviewDto;
import com.app.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
@Validated
public class ProdcutController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<?> getAllProduct() {
		List<ProductDto> productDto = productService.getAllProduct();

		if (productDto.size() == 0)
			return new ResponseEntity<>(new ApiResponse("no product available!!!!!!"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}

//	@GetMapping("/delete/{productId}")
//	public ResponseEntity<?> deleteProductById(@RequestParam Long productId)
//	{
//		return ResponseEntity.ok(productService.deleteProductById(productId));
//	}

	@PostMapping("{productId}/add/review")
	public ResponseEntity<?> addProductReview(@RequestBody ReviewDto reviewDto, @RequestParam Long customerID,
			@PathVariable Long productId) {
		return ResponseEntity
				.ok(productService.addReview(reviewDto.getReview(), reviewDto.getRating(), customerID, productId));
	}

	@GetMapping("{productId}/reviews")
	public ResponseEntity<?> getProductReviews(@PathVariable Long productId) {
		
		List<ReviewDto> allReview=productService.getProductReviews(productId);
		
		if(allReview.size()==0)
			return new ResponseEntity<>(new ApiResponse("no reviews"),HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.ok(allReview);
	}
	
	
}
