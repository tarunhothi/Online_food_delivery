package com.cg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import com.cg.exception.NoRecordsFounds;
import com.cg.exception.RestaurantAlreadyExistException;
import com.cg.model.Restaurant;
import com.cg.repository.RestaurantRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

	@Mock
	private RestaurantRepository fruRepo;
	
	@InjectMocks
	private RestaurantServiceImpl fruImpl;
	
	@Test
	public void addFruitTest() throws RestaurantAlreadyExistException {
		Restaurant f1 = new Restaurant(11, "emperior hotel", 25.5689, 87.4968, "Banglore", 361220);
		when(fruRepo.save(any())).thenReturn(f1);
		fruImpl.addRestaurant(f1);
		verify(fruRepo,times(1)).save(any());
	}
	
	@Test
	public void getAllFruitsTest() throws NoRecordsFounds {
		Restaurant f1 = new Restaurant(12, "Saffron hotel", 25.5689, 87.4968, "pune", 614278);
		Restaurant f2 = new Restaurant(13, "dwarkadhish hotel", 25.5689, 87.4968, "hyderabad", 868620);
		Restaurant f3 = new Restaurant(14, "pizzeria hotel", 25.5689, 87.4968, "delhi", 541220);
		fruRepo.save(f1);
		fruRepo.save(f2);
		fruRepo.save(f3);
		List<Restaurant> fList = new ArrayList<>();
		fList.add(f1);
		fList.add(f2);
		fList.add(f3);
		
		when(fruRepo.findAll()).thenReturn(fList);
		List<Restaurant> fList1 = fruImpl.getRestaurants();
		assertEquals(fList, fList1);
		verify(fruRepo, times(1)).save(f1);
//		verify(fruRepo, times(1)).findAll();
	}
	

}
