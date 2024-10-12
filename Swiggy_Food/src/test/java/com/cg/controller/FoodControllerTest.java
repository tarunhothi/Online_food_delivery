package com.cg.controller;

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

import com.cg.model.Food;
import com.cg.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class FoodControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private FoodService frs;
	private Food f;
	private List<Food> fruList;
	
	@InjectMocks
	private FoodController fc;
	
	@BeforeEach
	public void setUp() {
		f = new Food(1, "guavava", 400);
		mockMvc = MockMvcBuilders.standaloneSetup(fc).build();
	}
	
	@Test
	public void addFoodControllerTest() throws Exception {
		when(frs.addFood(any())).thenReturn(f);
		mockMvc.perform(post("/api/v1/addfood")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJSONString(f)))
				.andExpect(status().isCreated());
		
		verify(frs, times(1)).addFood(any());
	}
	
	@Test
	public void getAllFoodControllerTest() throws Exception {
		when(frs.getFoods()).thenReturn(fruList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getfoods")
		.contentType(MediaType.APPLICATION_JSON)
		.content(asJSONString(f)))
		.andDo(MockMvcResultHandlers.print());
		verify(frs, times(1)).getFoods();
		
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
