package com.cg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.model.Food;

public interface FoodRepository extends MongoRepository<Food, Integer> {

}
