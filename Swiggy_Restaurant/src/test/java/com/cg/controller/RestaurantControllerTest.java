package com.cg.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.model.Restaurant;
import com.cg.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private RestaurantService frs;
	private Restaurant f;
	private List<Restaurant> resList;
	
	@InjectMocks
	private RestaurantController fc;
	
	@BeforeEach
	public void setUp() {
		f = new Restaurant(20, "mahel hotel", 25.5689, 87.4968, "Banglore", 361220);
		mockMvc = MockMvcBuilders.standaloneSetup(fc).build();
	}
	
	@Test
	public void addRestaurantControllerTest() throws Exception {
		when(frs.addRestaurant(any())).thenReturn(f);
		mockMvc.perform(post("/api/v1/add/restaurant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(f)))
				.andExpect(status().isCreated());
		
		verify(frs, times(1)).addRestaurant(any());
	}
	
	@Test
	public void getAllRestaurantControllerTest() throws Exception {
		when(frs.getRestaurants()).thenReturn(resList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/get/restaurants")
		.contentType(MediaType.APPLICATION_JSON)
		.content(asJSONString(f)))
		.andDo(MockMvcResultHandlers.print());
		verify(frs, times(1)).getRestaurants();
		
	}
	
	public static String asJSONString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
