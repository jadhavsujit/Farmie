package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.SupplierDto;

public interface SupplierService {

	List<SupplierDto> getAllSupplier();
	
	ApiResponse addProduct(ProductDto productDto ,long suplierId,int qty);
	
	List<ProductDto> getAllProductBySupplier(long supplierId);
	
	ApiResponse deleteProductById(Long productId);

	ApiResponse addAddress( AddressDto addressDto, Long supplierId);

	List<AddressDto> getAllAddress(Long supplierId);
	
}
