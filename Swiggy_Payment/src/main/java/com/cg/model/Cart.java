package com.cg.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Cart")
public class Cart {
	
	@Id
	private int id;
	private int user_id;
	private User user;
	private int food_id;
	private Date created_date;
	private double amount;
	private Food food;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int id, int user_id, int food_id, User user, Date created_date, double amount, Food food) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.food_id = food_id;
		this.user = user;
		this.created_date = created_date;
		this.amount = amount;
		this.food = food;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user_id=" + user_id + ", user=" + user + ", food_id=" + food_id + ", created_date="
				+ created_date + ", amount=" + amount + ", food=" + food + "]";
	}
	
	
	
}
