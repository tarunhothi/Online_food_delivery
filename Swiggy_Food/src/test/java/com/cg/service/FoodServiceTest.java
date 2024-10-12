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

import com.cg.exception.FoodAlreadyExistException;
import com.cg.exception.NoRecordsFounds;
import com.cg.model.Food;
import com.cg.repository.FoodRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

	@Mock
	private FoodRepository fruRepo;
	
	@InjectMocks
	private FoodServiceImpl fruImpl;
	
	@Test
	public void addFruitTest() throws FoodAlreadyExistException {
		Food f1 = new Food(52, "Orange", 60);
		when(fruRepo.save(any())).thenReturn(f1);
		fruImpl.addFood(f1);
		verify(fruRepo,times(1)).save(any());
	}
	
	@Test
	public void getAllFruitsTest() throws NoRecordsFounds {
		Food f1 = new Food(52, "Orange", 60);
		Food f2 = new Food(53, "Grapes", 150);
		Food f3 = new Food(54, "Mango", 200);
		fruRepo.save(f1);
		fruRepo.save(f2);
		fruRepo.save(f3);
		List<Food> fList = new ArrayList<>();
		fList.add(f1);
		fList.add(f2);
		fList.add(f3);
		
		when(fruRepo.findAll()).thenReturn(fList);
		List<Food> fList1 = fruImpl.getFoods();
		assertEquals(fList, fList1);
		verify(fruRepo, times(1)).save(f1);
//		verify(fruRepo, times(1)).findAll();
	}
	

}
