package com.cg.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.model.Cart;
import com.cg.model.Food;
import com.cg.model.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CartRepositoryTest {
	
	@Autowired 
	private CartRepository fRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private FoodRepository foRepo;
	
	Date d = new Date();

	@Test
	public void shouldReturnCartObject(){
		User u1 = uRepo.findById(7).get();
		Food f = foRepo.findById(2).get();
		Cart f1 = new Cart(4, 7, 2,u1,d,240,f);
		fRepo.save(f1);
		Cart f2 = fRepo.findById(4).get();
		assertNotNull(f2);
		assertEquals(f1.getUser().getName(), f2.getUser().getName());
	}
	
	@Test
	public void shouldReturnAllFoods() {
		User u1 = uRepo.findById(1).get();
		Food f = foRepo.findById(2).get();
		User u2 = uRepo.findById(2).get();
		Food f2 = foRepo.findById(4).get();
		Cart f1 = new Cart(5, 1, 2,u1,d,240,f);
		Cart f3 = new Cart(6, 2, 4,u2,d,240,f2);
		fRepo.save(f1);
		fRepo.save(f3);
		List<Cart> cartList = (List<Cart>)fRepo.findAll();
		assertEquals("klausu", fRepo.findById(8).get().getUser().getName());
	}

}
