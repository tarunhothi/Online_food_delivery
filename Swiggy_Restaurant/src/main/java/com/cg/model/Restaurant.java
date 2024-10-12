package com.cg.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Restaurant")
public class Restaurant {

	@Id
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private String city;
	private int zipcode;
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Restaurant(int id, String name, double latitude, double longitude, String city, int zipcode) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zipcode = zipcode;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", city=" + city + ", zipcode=" + zipcode + "]";
	}
	
	
}
