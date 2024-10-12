package com.cg.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.model.Food;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FoodRepositoryTest {
	
	@Autowired 
	private FoodRepository fRepo;

	@Test
	public void shouldReturnFoodObject(){
		Food f1 = new Food(4, "pani puri", 25);
		fRepo.save(f1);
		Food f2 = fRepo.findById(f1.getId()).get();
		assertNotNull(f2);
		assertEquals(f1.getName(), f2.getName());
	}
	
	@Test
	public void shouldReturnAllFoods() {
		Food f3 = new Food(5, "Pizza", 250);
		Food f4 = new Food(7, "Dosa", 100);
		fRepo.save(f3);
		fRepo.save(f4);
		List<Food> foodList = (List<Food>)fRepo.findAll();
		assertEquals("Dosa", foodList.get(4).getName());
	}

}
