package com.cg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.model.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {
	
}
