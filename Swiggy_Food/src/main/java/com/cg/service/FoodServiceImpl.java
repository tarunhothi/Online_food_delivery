package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exception.FoodAlreadyExistException;
import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Food;
import com.cg.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository fRepo;
	
	@Override
	public Food addFood(Food food) throws FoodAlreadyExistException {
		if(fRepo.existsById(food.getId())) {
			throw new FoodAlreadyExistException();
		}
		Food f = fRepo.save(food);
		return f;
	}

	@Override
	public List<Food> getFoods() throws NoRecordsFounds {
		List<Food> allFood = fRepo.findAll();
		if(allFood.isEmpty() == true) {
			throw new NoRecordsFounds();
		}
		System.out.println(allFood.isEmpty());
		return allFood;
	}

	@Override
	public Food updateFood(Food food, int id) throws IdNotFoundException {
		if(fRepo.existsById(id) == false) {
			throw new IdNotFoundException();
		}
		else{
		Food f = fRepo.findById(id).get();
		f.setName(food.getName());
		f.setPrice(food.getPrice());
		fRepo.save(f);
		return f;
		}
	}

	@Override
	public void deleteFood(int id) throws IdNotFoundException {
		if(fRepo.existsById(id)) {
			fRepo.deleteById(id);
		}else {
			throw new IdNotFoundException();
		}
	}

}
