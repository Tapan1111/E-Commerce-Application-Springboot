package com.ecommerce.project5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project5.common.ApiResponse;
import com.ecommerce.project5.dto.ProductDto;
import com.ecommerce.project5.model.Category;
import com.ecommerce.project5.model.Product;
import com.ecommerce.project5.repository.CategoryRepository;
import com.ecommerce.project5.repository.ProductRepository;
import com.ecommerce.project5.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	// adding product api
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
		Optional<Category> checkCategory = categoryRepository.findById(productDto.getCategory_id());
		if (!checkCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "category id is not present"), HttpStatus.NOT_FOUND);
		}
		productService.createProduct(productDto, checkCategory.get());
		return new ResponseEntity<>(new ApiResponse(true, "product added successfully"), HttpStatus.CREATED);
	}

	// fetching products api
	@GetMapping("/productlist")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> product = productService.getProduct();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	// editing product api
	@PostMapping("/edit/{productId}")
	public ResponseEntity<ApiResponse> editProducts(@PathVariable("productId") Integer productId,
			@RequestBody ProductDto productDto) throws Exception {
		Optional<Product> checkProduct = productRepository.findById(productDto.getId());
		if (!checkProduct.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "product id is not present"), HttpStatus.NOT_FOUND);
		}
		productService.updateProduct(productDto, productId);
		return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
	}

}
