package com.cg.service;

import java.util.List;

import com.cg.exception.FoodAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Food;

public interface FoodService {
	public Food addFood(Food food) throws FoodAlreadyExistException;
	public List<Food> getFoods() throws NoRecordsFounds;
	public Food updateFood(Food food, int id) throws IdNotFoundException;
	public void deleteFood(int id) throws IdNotFoundException;
	
}
