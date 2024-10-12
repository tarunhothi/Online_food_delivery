package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exception.IdNotFoundException;
import com.cg.exception.NoRecordsFounds;
import com.cg.exception.RestaurantAlreadyExistException;
import com.cg.model.Restaurant;
import com.cg.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	
	@Autowired
	private RestaurantRepository resRepo;
	
	public RestaurantServiceImpl(RestaurantRepository resRepo) {
		super();
		this.resRepo = resRepo;
	}

	public RestaurantServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Restaurant addRestaurant(Restaurant res) throws RestaurantAlreadyExistException {
		
		if(resRepo.existsById(res.getId())) {
			throw new RestaurantAlreadyExistException();
		}
		Restaurant r = resRepo.save(res);
		return r;
	}

	@Override
	public List<Restaurant> getRestaurants() throws NoRecordsFounds {
		List<Restaurant> rest = resRepo.findAll();
		if(rest.isEmpty() == true) {
			throw new NoRecordsFounds();
		}
		List<Restaurant> r = resRepo.findAll();
		return r;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res, int id) throws IdNotFoundException {
		
		if(resRepo.existsById(id)) {
		
			Restaurant r = resRepo.findById(id).get();
			r.setName(res.getName());
			r.setLatitude(res.getLatitude());
			r.setLongitude(res.getLongitude());
			r.setCity(res.getCity());
			r.setZipcode(res.getZipcode());
			resRepo.save(r);
			return r;
		}
		else {
			throw new IdNotFoundException();
		}
	}

	@Override
	public void deleteRestaurant(int id) throws IdNotFoundException {
		if(resRepo.existsById(id)) {
			resRepo.deleteById(id);
		}else {
			throw new IdNotFoundException();
		}

	}

}
