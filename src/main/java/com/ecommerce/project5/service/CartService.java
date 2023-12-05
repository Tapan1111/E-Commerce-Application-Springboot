package com.ecommerce.project5.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.dto.AddToCartDto;
import com.ecommerce.project5.dto.CartDto;
import com.ecommerce.project5.dto.CartItemDto;
import com.ecommerce.project5.model.Cart;
import com.ecommerce.project5.model.Product;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductService productService;

	public void addToCart(AddToCartDto addToCartDto, User user) {

		// validate the product_id is valid
		Product product = productService.findById(addToCartDto.getProduct_id());

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setUser(user);
		cart.setCreatedDate(new Date());
		cartRepository.save(cart);


		// save the product

	}

	public CartDto listItems(User user) {
		List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

		List<CartItemDto> cartItems = new ArrayList<CartItemDto>();
		double totalcost = 0;

		for (Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalcost = cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalcost);
		cartDto.setCartItem(cartItems);
		return cartDto;
	}

}
