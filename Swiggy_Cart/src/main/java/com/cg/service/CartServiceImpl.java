package com.cg.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exception.CartIdAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Cart;
import com.cg.model.Food;
import com.cg.repository.CartRepository;
import com.cg.repository.FoodRepository;
import com.cg.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

	
	@Autowired
	private CartRepository cRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private FoodRepository fRepo;
	
	Date date = new Date();
	
	@Override
	public List<Cart> getItems() throws NoRecordsFounds {
		List<Cart> allItem = cRepo.findAll();
		if(allItem.isEmpty()) {
			throw new NoRecordsFounds();
		}
		
		return allItem;
	}

	@Override
	public Cart addItem(Cart cart) throws CartIdAlreadyExistException {
		
		if(cRepo.existsById(cart.getId())) {
			throw new CartIdAlreadyExistException();
		}else {
			cart.setCreated_date(date);
			cart.setUser(uRepo.findById(cart.getUser_id()).get());
			cart.setFood(fRepo.findById(cart.getFood_id()).get());
			Food f = fRepo.findById(cart.getFood_id()).get();
			cart.setAmount(f.getPrice());
			Cart savedItem = cRepo.save(cart);
			return savedItem;
		}
	}

	@Override
	public Cart updateItem(Cart cart, int id) throws IdNotFoundException {
		
		if(cRepo.existsById(id) == false) {
			throw new IdNotFoundException();
		}else {
			
		Cart c = cRepo.findById(id).get();
		
		c.setUser_id(cart.getUser_id());
		c.setFood_id(cart.getFood_id());
		
		Food f = fRepo.findById(cart.getFood_id()).get();
		c.setAmount(f.getPrice());
		
		c.setUser(uRepo.findById(cart.getUser_id()).get());
		c.setFood(fRepo.findById(cart.getFood_id()).get());
		c.setCreated_date(c.getCreated_date());
		cRepo.save(c);
		return c;
		}
	}

	@Override
	public void deleteItem(int id) throws IdNotFoundException {
		if(cRepo.existsById(id)) {
			cRepo.deleteById(id);
		}else {
			throw new IdNotFoundException();
		}

	}

	@Override
	public Cart getItemById(int id) throws IdNotFoundException{
		if(cRepo.existsById(id)) {
			Cart c = cRepo.findById(id).get();
			return c;
		}else {
			throw new IdNotFoundException();
		}
		
	}

}
