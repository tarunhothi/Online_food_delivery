package com.cg.service;

import java.util.List;

import com.cg.exception.CartIdAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Cart;

public interface CartService {
	public List<Cart> getItems() throws NoRecordsFounds;
	public Cart addItem(Cart cart) throws CartIdAlreadyExistException;
	public Cart updateItem(Cart cart, int id) throws IdNotFoundException;
	public void deleteItem(int id) throws IdNotFoundException;
	public Cart getItemById(int id) throws IdNotFoundException;
}
