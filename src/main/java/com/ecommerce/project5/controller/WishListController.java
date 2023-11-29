package com.ecommerce.project5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project5.common.ApiResponse;
import com.ecommerce.project5.dto.ProductDto;
import com.ecommerce.project5.model.Product;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.model.WishList;
import com.ecommerce.project5.service.AuthenticationService;
import com.ecommerce.project5.service.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	// save products as whishlist items
	@Autowired
	WishListService wishListService;

	@Autowired
	AuthenticationService authenticationService;

	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
			@RequestParam("token") String token) {

		// authenticate the token
		authenticationService.authenticate(token);

		// authenticate the user
		User user = authenticationService.getUser(token);

		WishList wishList = new WishList(user, product);

		wishListService.createWishlist(wishList);

		return new ResponseEntity<>(new ApiResponse(true, "added to wishlist"), HttpStatus.CREATED);

	}
	// get all wishlist items for a user
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
		// authenticate the token
		authenticationService.authenticate(token);

		// authenticate the user
		User user = authenticationService.getUser(token);

		List<ProductDto> productdtos = wishListService.getWishListForUser(user);

		return new ResponseEntity<List<ProductDto>>(productdtos, HttpStatus.OK);
	}
}
