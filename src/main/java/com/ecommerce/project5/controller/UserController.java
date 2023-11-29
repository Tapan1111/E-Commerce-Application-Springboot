package com.ecommerce.project5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project5.dto.ResponseDto;
import com.ecommerce.project5.dto.SignInDto;
import com.ecommerce.project5.dto.SignInResponse;
import com.ecommerce.project5.dto.SignUpDto;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseDto signUp(@RequestBody SignUpDto signUpDto) {

		return userService.signUp(signUpDto);
	}

	@PostMapping("/signIn")
	public SignInResponse signIn(@RequestBody SignInDto signInDto) {
		return userService.signIn(signInDto);
	}

	@GetMapping("/lists")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

}
