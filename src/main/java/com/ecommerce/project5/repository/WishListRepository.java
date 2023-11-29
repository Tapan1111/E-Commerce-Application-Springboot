package com.ecommerce.project5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project5.model.User;
import com.ecommerce.project5.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
