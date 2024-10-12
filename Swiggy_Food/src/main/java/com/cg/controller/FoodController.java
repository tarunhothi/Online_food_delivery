package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.FoodAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Food;
import com.cg.service.FoodService;

@RestController
@RequestMapping("/api/v1")
public class FoodController {
	
	@Autowired
	private FoodService fServ;

	public FoodController() {
		super();
	}

	public FoodController(FoodService fServ) {
		super();
		this.fServ = fServ;
	}
	
	@PostMapping("/addfood")
	public ResponseEntity<Food> addFood(@RequestBody Food food) throws FoodAlreadyExistException{
		Food addFood = fServ.addFood(food);
		return new ResponseEntity<> (addFood, HttpStatus.CREATED);
	}
	
	@GetMapping("/getfoods")
	public ResponseEntity<List<Food>> getFood() throws NoRecordsFounds{
		return new ResponseEntity<List<Food>>(fServ.getFoods(), HttpStatus.OK);
	}
	
	@PutMapping("/updatefood/{id}")
	public ResponseEntity<Food> updateFood(@RequestBody Food food,@PathVariable int id ) throws IdNotFoundException{
		return new ResponseEntity<> (fServ.updateFood(food, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("deletefood/{id}")
	public ResponseEntity<Void> deleteFood(@PathVariable int id) throws IdNotFoundException{
		fServ.deleteFood(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
