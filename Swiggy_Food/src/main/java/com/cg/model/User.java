package com.cg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {
	
	@Id
	private int id;
	private String name;
	private String email;
	private long mobileNo;
	private String role;

	private String password;
	public User(int id, String name, String email, long mobileNo, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", mobileNo=" + mobileNo + ", role=" + role
				+ ", password=" + password + "]";
	}
	
	
}
