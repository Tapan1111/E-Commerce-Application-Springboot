package com.ecommerce.project5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project5.model.AuthenticationToken;
import com.ecommerce.project5.model.User;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByUser(User user);

	AuthenticationToken findByToken(String token);
}
