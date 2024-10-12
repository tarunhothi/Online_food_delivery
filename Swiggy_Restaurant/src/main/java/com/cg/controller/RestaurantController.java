package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.exception.RestaurantAlreadyExistException;
import com.cg.model.Restaurant;
import com.cg.service.RestaurantService;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController {
	
	@Autowired
	private RestaurantService resServ;
	
	
	@PostMapping("/add/restaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant res) throws RestaurantAlreadyExistException{
		return new ResponseEntity<> (resServ.addRestaurant(res), HttpStatus.CREATED);
	}
	
	@GetMapping("/get/restaurants")
	public ResponseEntity<List<Restaurant>> getRestaurant() throws NoRecordsFounds{
		return new ResponseEntity<List<Restaurant>> (resServ.getRestaurants(), HttpStatus.OK);
	}
	
	@PutMapping("/update/restaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant res, @PathVariable int id) throws IdNotFoundException{
		return new ResponseEntity<Restaurant> (resServ.updateRestaurant(res, id), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/restaurant/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable int id) throws IdNotFoundException{
		resServ.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}
}
