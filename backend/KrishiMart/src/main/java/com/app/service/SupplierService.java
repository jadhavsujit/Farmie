package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.SupplierDto;

public interface SupplierService {

	List<SupplierDto> getAllSupplier();
	
	//ApiResponse addProduct(ProductDto productDto ,long suplierId,int qty);
	
	ApiResponse addProduct(ProductDto productDto ,long suplierId,int qty,MultipartFile file) throws IOException;
	
	List<ProductDto> getAllProductBySupplier(long supplierId);
	
	ApiResponse deleteProductById(Long productId);

	ApiResponse addAddress( AddressDto addressDto, Long supplierId);

	List<AddressDto> getAllAddress(Long supplierId);
	
}
