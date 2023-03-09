package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Entity.Cart;
import com.app.Entity.Category;
import com.app.Entity.Product;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CategoryDto;
import com.app.dto.ProductDto;
import com.app.repository.CategoryRepo;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = catRepo.findAll();
		return categories.stream().map(category -> mapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Category addCategory(CategoryDto cat) {

		Category category = mapper.map(cat, Category.class);
		return catRepo.save(category);

	}

	@Override
	public List<ProductDto> getAllProductByCategory(Long catId) {

		Category category = catRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid category id"));
		
		if(category==null)
		{
			return null;
		}

		Hibernate.initialize(category.getAllProduct());
		
		return category.getAllProduct().stream().map((p)->mapper.map(p,ProductDto.class)).collect(Collectors.toList());
	}

}
