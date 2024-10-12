package com.cg.service;

import java.util.List;

import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.exception.RestaurantAlreadyExistException;
import com.cg.model.Restaurant;

public interface RestaurantService {
	public Restaurant addRestaurant(Restaurant res) throws RestaurantAlreadyExistException;
	public List<Restaurant> getRestaurants() throws NoRecordsFounds;
	public Restaurant updateRestaurant(Restaurant res, int id) throws IdNotFoundException;
	public void deleteRestaurant(int id) throws IdNotFoundException;
}
