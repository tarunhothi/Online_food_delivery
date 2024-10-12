package com.cg.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.model.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RestaurantRepositoryTest {
	
	@Autowired
	private RestaurantRepository resRepo;

	@Test
	public void shouldReturnRestaurantObject() {
		Restaurant r1 = new Restaurant(20, "Alexa Hotel", 24.5685, -12.5689, "ahmedabad", 360001);
		resRepo.save(r1);
		Restaurant r2 = resRepo.findById(r1.getId()).get();
		assertNotNull(r2);
		assertEquals(r2.getZipcode(), r1.getZipcode());
	}
	
	@Test
	public void shouldReturnAllFoods() {
		Restaurant f3 = new Restaurant(21, "mahel hotel", 25.5689, 87.4568, "Banglore", 564895);
		Restaurant f4 = new Restaurant(22, "Food Plaza", 56.1564, -87.2587, "Hyderabad", 658945);
		resRepo.save(f3);
		resRepo.save(f4);
		List<Restaurant> restaurantList = (List<Restaurant>)resRepo.findAll();
		assertEquals("mahel hotel", restaurantList.get(3).getName());
	}

}
