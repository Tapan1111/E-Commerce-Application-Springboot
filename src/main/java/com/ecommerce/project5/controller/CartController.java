package com.ecommerce.project5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project5.common.ApiResponse;
import com.ecommerce.project5.dto.AddToCartDto;
import com.ecommerce.project5.dto.CartDto;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.service.AuthenticationService;
import com.ecommerce.project5.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/addcart")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
			@RequestParam("token") String token) {

		authenticationService.authenticate(token);

		User user = authenticationService.getUser(token);

		cartService.addToCart(addToCartDto, user);

		return new ResponseEntity<>(new ApiResponse(true, "added to cart"), HttpStatus.CREATED);

	}

	@GetMapping("/getcart")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {

		authenticationService.authenticate(token);

		User user = authenticationService.getUser(token);

		CartDto cartDto = cartService.listItems(user);

		return new ResponseEntity<>(cartDto, HttpStatus.OK);

	}

}
