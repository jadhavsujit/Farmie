package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.app.Entity.Product;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;

import com.app.repository.ProductRepo;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {

	@Value("${content.upload.folder}")
	private String folderName;
	// dep : dao layer i/f :
	@Autowired
	private ProductRepo productRepo;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			System.out.println("creating images folder...");
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public ApiResponse uploadImage(Long productId, MultipartFile imageFile) throws IOException {
		// chk if product exists by the id ?
		Product product = productRepo.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("Invalid Product Id : Image Uploading failed!!!!!!!!"));
		// valid product : PERSISTENT --create complete path to the image
		String targetPath = folderName +  File.separator +  imageFile.getOriginalFilename();
		System.out.println(targetPath);
		// copy multipart image file contents to the specified path
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		// OR for DB
		/*
		 * Product entity :Add @Lob private byte[] image;
		 * product.setImage(imageFile.getBytes());
		 */
		
				//Alternative approach : to store complete img URL in DB : not using it currently !
//		    String uriString = ServletUriComponentsBuilder.
//		    		fromCurrentContextPath().path(folderName).path(imageFile.getOriginalFilename()).toUriString();
		 //   String uri = uriComponents.toUriString();
		 //   System.out.println("img url "+uriString);
		// save image path in DB
		product.setImagePath(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}

	@Override
	public byte[] serveImage(Long productId) throws IOException {
		// chk if product exists by the id ?
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product Id : Image Download failed!!!!!!!!"));
		// valid product : PERSISTENT --create complete path to the image
		String path = product.getImagePath();
		if (path == null)
			throw new ResourceNotFoundException("Image does not exist !!!!!");
		// OR to lift it from DB product.getContents() --> byte[]
		return Files.readAllBytes(Paths.get(path));

	}


}
