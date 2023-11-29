package com.ecommerce.project5.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.dto.ProductDto;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.model.WishList;
import com.ecommerce.project5.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	ProductService productService;

	public void createWishlist(WishList wishList) {
		wishListRepository.save(wishList);
	}

	public List<ProductDto> getWishListForUser(User user) {
		List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> productsdto = new ArrayList<ProductDto>();
		for (WishList wishList : wishLists) {
			productsdto.add(productService.getProductDto(wishList.getProduct()));
		}
		return productsdto;
	}


}
