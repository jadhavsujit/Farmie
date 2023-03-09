package com.app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.service.ImageHandlingService;
import com.app.service.SupplierService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/supplier")
@Validated
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ImageHandlingService imageService;

	@GetMapping
	public ResponseEntity<?> getAllSupplier() {
		return ResponseEntity.ok(supplierService.getAllSupplier());
	}

	@PostMapping(value = "/add/product")
	public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, @RequestParam Long supplierId,
			@RequestParam int quantity) throws IOException {

		
		
		ApiResponse resp = supplierService.addProduct(productDto, supplierId, quantity);

		if (resp.getMessage() == "add product failed!!!!!!!!!") {
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(resp);

	}

	
	
	@GetMapping("/{supplierId}/products")
	public ResponseEntity<?> getAllProductBySupplier(@PathVariable Long supplierId)
	{
		 List<ProductDto> allProd= supplierService.getAllProductBySupplier(supplierId);
		 if(allProd.size()==0)
		 {
			 return new ResponseEntity<>(new ApiResponse("no Products!!!!!!"),HttpStatus.BAD_REQUEST);
		 }
		 return new ResponseEntity<>(allProd,HttpStatus.OK);
	}
	
//	@GetMapping("/delete/{productId}")
//	public ResponseEntity<?> deleteProductById(@RequestParam Long productId)
//	{
//		return ResponseEntity.ok(productService.deleteProductById(productId));
//	}
	
	@PostMapping("/{supplierId}/address")
	public ResponseEntity<?> addAddress(@RequestBody @Valid AddressDto addressDto,@PathVariable Long supplierId)
	{
		return ResponseEntity.ok(supplierService.addAddress(addressDto, supplierId));
	}
	
	
	@GetMapping("/{supplierId}/address")
	public ResponseEntity<?> getAllAddress(@PathVariable Long supplierId)
	{
		List<AddressDto> allAddressDto=supplierService.getAllAddress(supplierId);
		if(allAddressDto.size()==0)
			return new ResponseEntity<>(new ApiResponse("no address found"),HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.ok(allAddressDto);
	}
	
	
	
	


	// Add REST end point to upload image
	 	// URL : http://host:port/products/{productId}/image , Method=POST
	 	@PostMapping(value="/{productId}/image",consumes = "multipart/form-data")
	 	public ResponseEntity<?> uploadImageToServerSideFolder(@RequestParam MultipartFile imageFile,
	 			@PathVariable Long productId
	 			) throws IOException {
	 		System.out.println("in upload img " + productId + " " + imageFile.getOriginalFilename());
	 		return new ResponseEntity<>(imageService.uploadImage(productId, imageFile), HttpStatus.CREATED);
	 	}

	 	// Add REST end point to download/serve image , method=GET
	 	// URL : http://host:port/products/{productId}/image
	 	@GetMapping(value = "/{productId}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
	 			MediaType.IMAGE_JPEG_VALUE,
	 			MediaType.IMAGE_PNG_VALUE })
	 	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long productId) throws IOException {
	 		System.out.println("in serve img " + productId);
	 		return new ResponseEntity<>(imageService.serveImage(productId), HttpStatus.OK);
	 	}


}
