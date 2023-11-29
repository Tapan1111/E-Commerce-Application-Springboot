package com.ecommerce.project5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.dto.ProductDto;
import com.ecommerce.project5.model.Category;
import com.ecommerce.project5.model.Product;
import com.ecommerce.project5.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setId(productDto.getId());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCategory(category);
		productRepository.save(product);
	}

	public ProductDto getProductDto(Product product) {
		ProductDto productdto = new ProductDto();
		productdto.setId(product.getId());
		productdto.setDescription(product.getDescription());
		productdto.setImageUrl(product.getImageUrl());
		productdto.setName(product.getName());
		productdto.setPrice(product.getPrice());
		productdto.setCategory_id(product.getCategory().getId());
		return productdto;
	}

	public List<ProductDto> getProduct() {

		List<Product> allProduct = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for(Product product : allProduct) {
			productDtos.add(getProductDto(product));
		}
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new Exception("prodduct not present");
		}
		Product prd = product.get();
		prd.setId(productDto.getId());
		prd.setDescription(productDto.getDescription());
		prd.setImageUrl(productDto.getImageUrl());
		prd.setName(productDto.getName());
		prd.setPrice(productDto.getPrice());
		productRepository.save(prd);
	}

}
