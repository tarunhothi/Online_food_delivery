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

import com.cg.exception.CartIdAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Cart;
import com.cg.service.CartService;

@RestController
@RequestMapping("/api/v1")
public class CartController {
	
	@Autowired
	private CartService cServ;
	
	@GetMapping("/cart/getitems")
	public ResponseEntity<List<Cart>> getItems() throws NoRecordsFounds{
		return new ResponseEntity<List<Cart>> (cServ.getItems(), HttpStatus.OK);
	}
	
	@PostMapping("/cart/additem")
	public ResponseEntity<Cart> addItem(@RequestBody Cart cart) throws CartIdAlreadyExistException{
		return new ResponseEntity<Cart> (cServ.addItem(cart), HttpStatus.CREATED);
	}
	
	@PutMapping("/cart/updateitem/{id}")
	public ResponseEntity<Cart> updateItem(@RequestBody Cart cart, @PathVariable int id) throws IdNotFoundException{
		return new ResponseEntity<Cart> (cServ.updateItem(cart, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/cart/deleteitem/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable int id) throws IdNotFoundException{
		cServ.deleteItem(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getItemById(@PathVariable int id) throws IdNotFoundException{
		return new ResponseEntity<Cart> (cServ.getItemById(id), HttpStatus.OK);
	}
	
}
