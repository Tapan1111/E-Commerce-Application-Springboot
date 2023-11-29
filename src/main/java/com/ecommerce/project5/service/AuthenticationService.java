package com.ecommerce.project5.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.exception.AuthenticationFailedException;
import com.ecommerce.project5.model.AuthenticationToken;
import com.ecommerce.project5.model.User;
import com.ecommerce.project5.repository.AuthenticationTokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	AuthenticationTokenRepository authenticationTokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		authenticationTokenRepository.save(authenticationToken);

	}

	public AuthenticationToken getToken(User user) {
		return authenticationTokenRepository.findByUser(user);
	}

	public User getUser(String token) {
		AuthenticationToken authToken = authenticationTokenRepository.findByToken(token);
		if (Objects.isNull(authToken)) {
			return null;
		}
		return authToken.getUser();
	}

	public void authenticate(String token) throws AuthenticationFailedException {
		if (Objects.isNull(token)) {
			throw new AuthenticationFailedException("token is not present");
		}
		if (Objects.isNull(getUser(token))) {
			throw new AuthenticationFailedException("token not valid");
		}
	}

}
