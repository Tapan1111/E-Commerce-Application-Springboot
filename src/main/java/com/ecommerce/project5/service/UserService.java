package com.ecommerce.project5.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.dto.ResponseDto;
import com.ecommerce.project5.dto.SignInDto;
import com.ecommerce.project5.dto.SignInResponse;
import com.ecommerce.project5.dto.SignUpDto;
import com.ecommerce.project5.exception.AuthenticationFailedException;
import com.ecommerce.project5.exception.CustomException;
import com.ecommerce.project5.model.AuthenticationToken;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;

	@Transactional
	public ResponseDto signUp(SignUpDto signUpDto) {
		// check if user already presents
		if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))) {
			throw new CustomException("User already exists");
		}

		// hash the passsword
		String encryptedpassword = signUpDto.getPassword();
		try {
			encryptedpassword = hashPassword(signUpDto.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		// save the user
		User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(),
				encryptedpassword);
		userRepository.save(user);

		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);

		ResponseDto responseDto = new ResponseDto("success", "user created successfully");
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SignInResponse signIn(SignInDto signInDto) {
		// find user by email
		User user = userRepository.findByEmail(signInDto.getEmail());

		if (Objects.isNull(user)) {
			throw new AuthenticationFailedException("user is not valid");
		}
		// hash the password
		try {
			if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
				throw new AuthenticationFailedException("wrong password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		AuthenticationToken token = authenticationService.getToken(user);
		// retrieve the token
		if (Objects.isNull(token)) {
			throw new CustomException("token is not present");
		}

		// return response
		return new SignInResponse("success", token.getToken());
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
