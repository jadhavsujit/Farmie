package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.app.service.ImageHandlingService;
import com.app.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product")
@Validated
public class ProdcutController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ImageHandlingService imageService;

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

		List<ReviewDto> allReview = productService.getProductReviews(productId);

		if (allReview.size() == 0)
			return new ResponseEntity<>(new ApiResponse("no reviews"), HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(allReview);
	}

	// Add REST end point to download/serve image , method=GET
	// URL : http://host:port/products/{productId}/image
	@GetMapping(value = "/{productId}/image", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long productId) throws IOException {
		System.out.println("in serve img " + productId);
		return new ResponseEntity<>(imageService.serveImage(productId), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductDetailById(@PathVariable Long productId)
	{
		ProductDto productDto= productService.getProductDetailById(productId);
		if(productDto==null)
			return new ResponseEntity<>(new ApiResponse("invalid product"),HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(productDto);
	}

}
