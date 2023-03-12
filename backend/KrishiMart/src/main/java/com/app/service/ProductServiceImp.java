package com.app.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Entity.Customer;
import com.app.Entity.Product;
import com.app.Entity.Review;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ReviewDto;
import com.app.repository.CustomerRepo;
import com.app.repository.ProductRepo;
import com.app.repository.ReviewRepo;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ReviewRepo reviewRepo;

	@Override
	public List<ProductDto> getAllProduct() {
		return productRepo.findAll().stream().map(p -> mapper.map(p, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteProductById(Long productId) {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("invlalid product id!!!!"));
		if (product == null) {
			return new ApiResponse("invalid product!!!!!");
		}

		productRepo.deleteById(productId);
		return new ApiResponse("product deleted successfully!!!!!!!");
	}

	@Override
	public ApiResponse addReview(String description, double rating, Long customerId, Long productId) {

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid customer!!!!!"));
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid product!!!!!"));

		Review review = new Review(description, rating);
		review.setCustomer(customer);
		review.setProduct(product);

		Review newReview = reviewRepo.save(review);
		product.getReview().add(newReview);
		customer.getReviews().add(newReview);
		return new ApiResponse("review added successfully");
	}

	@Override
	public List<ReviewDto> getProductReviews(Long productId) {

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid product!!!!!"));

		List<Review> allReview = reviewRepo.findByProduct(product);

		List<ReviewDto> allReviewDto = new ArrayList<>();

		allReview.forEach((r) -> {
			allReviewDto.add(new ReviewDto(r.getId(), r.getReview(), r.getRating(), r.getReviewDate()));
		});


		// return allReview.stream().map(r -> mapper.map(r,
		// ReviewDto.class)).collect(Collectors.toList());
		return allReviewDto;

	}

	@Override
	public ProductDto getProductDetailById(Long productId) {
		Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("invalid product id"));
		return mapper.map(product, ProductDto.class);
	}

}
