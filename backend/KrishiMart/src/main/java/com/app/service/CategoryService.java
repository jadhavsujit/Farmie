package com.app.service;

import java.util.List;

import com.app.Entity.Category;
import com.app.Entity.Product;
import com.app.dto.CategoryDto;
import com.app.dto.ProductDto;

public interface CategoryService{

	
	List<ProductDto> getAllProductByCategory(Long catId);
	
	List<CategoryDto> getAllCategory();

	Category addCategory(CategoryDto cat);

}
