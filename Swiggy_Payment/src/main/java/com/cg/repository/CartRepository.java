package com.cg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.model.Cart;

public interface CartRepository extends MongoRepository<Cart, Integer> {
	
}
