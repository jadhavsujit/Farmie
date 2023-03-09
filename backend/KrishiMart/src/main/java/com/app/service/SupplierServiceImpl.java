package com.app.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.Entity.Category;
import com.app.Entity.Customer;
import com.app.Entity.CustomerAddress;
import com.app.Entity.Product;
import com.app.Entity.Supplier;
import com.app.Entity.SupplierAddress;
import com.app.Entity.SupplierProductInventory;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.AddressDto;
import com.app.dto.ApiResponse;
import com.app.dto.CustomerDto;
import com.app.dto.ProductDto;
import com.app.dto.SupplierDto;

import com.app.repository.CategoryRepo;
import com.app.repository.ProductRepo;
import com.app.repository.SupplierAddressRepo;
import com.app.repository.SupplierProductInventoryRepo;
import com.app.repository.SupplierRepo;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private SupplierProductInventoryRepo spiRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private SupplierAddressRepo supplierAddressRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<SupplierDto> getAllSupplier() {

		List<Supplier> suppliers = supplierRepo.findAll();

		if (suppliers.size() == 0) {
			throw new ResourceNotFoundException("no supplier found");
		}

		return suppliers.stream().map((a) -> mapper.map(a, SupplierDto.class)).collect(Collectors.toList());
	}

	@Override
	public ApiResponse addProduct(ProductDto productDto, long suplierId, int qty) {

		Category category = catRepo.findById(productDto.getCatId())
				.orElseThrow(() -> new ResourceNotFoundException("invalid categoryy!!!!!!!"));

		Product product = mapper.map(productDto, Product.class);

		product.setCategory(category);
		product.setImagePath(null);
		product.setInStock(true);

		Supplier supplier = supplierRepo.findById(suplierId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid supplier!!!!!!!!!!"));

		SupplierProductInventory spi = new SupplierProductInventory(qty);

		Product prodNew = productRepo.save(product);
		spi.setProduct(prodNew);
		spi.setSupplier(supplier);
		SupplierProductInventory spiNew = spiRepo.save(spi);

		category.addProduct(prodNew);

		if (spiNew == null || prodNew == null) {
			return new ApiResponse("add product failed!!!!!!!!!");
		}
		return new ApiResponse("product added successfully!!!!!");
	}

	@Override
	public List<ProductDto> getAllProductBySupplier(long supplierId) {

		Supplier supplier = supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid supplier!!!!!!!!!!!"));

		List<SupplierProductInventory> allSupplierProduct = spiRepo.findBySupplier(supplier);

		List<ProductDto> allProd = allSupplierProduct.stream().map(a -> mapper.map(a.getProduct(), ProductDto.class))
				.collect(Collectors.toList());
		return allProd;
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
	public ApiResponse addAddress(AddressDto addressDto, Long supplierId) {
		Supplier supplier = supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("invalid custoemr"));

		SupplierAddress address = mapper.map(addressDto, SupplierAddress.class);

		address.setSupplier(supplier);

		SupplierAddress supplierAddress = supplierAddressRepo.save(address);

		supplier.getAddress().add(supplierAddress);

		return new ApiResponse("address added successfully!!!!!!");

	}

	@Override
	public List<AddressDto> getAllAddress(Long supplierId) {
		Supplier supplier = supplierRepo.getReferenceById(supplierId);

		if (supplier == null) {
			throw new ResourceNotFoundException("invalid customer");
		}

		return supplier.getAddress().stream().map(a -> mapper.map(a, AddressDto.class)).collect(Collectors.toList());
	}

}
