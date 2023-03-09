package com.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.Category;
import com.app.Entity.Product;
import com.app.dto.CategoryDto;
import com.app.dto.ProductDto;
import com.app.service.CategoryService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/category")
@Validated
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	
	//http://localhost:8080/category method=get
	@GetMapping
	public ResponseEntity<?> getAllCategory()
	{
		System.out.println("in get all category controller");
		return ResponseEntity.ok(catService.getAllCategory());
	}
	
	
	
	
	//http://localhost:8080/category method=post
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryDto cDto)
	{
		System.out.println("in add category");
		System.out.println(cDto);
		
		Category category=catService.addCategory(cDto);
		if(category==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<?> getAllProductByCategory(@PathVariable Long catId)
	{
		System.out.println(catId);
		
		List<ProductDto> allProd=catService.getAllProductByCategory(catId);
		
		if(allProd==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	   return new ResponseEntity<>(allProd, HttpStatus.OK);	
	}
	
}
