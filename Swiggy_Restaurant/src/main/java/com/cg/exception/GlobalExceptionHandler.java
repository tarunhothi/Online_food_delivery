package com.cg.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value(value = "${data.exception.restaurantExists}")
	private String foodExists;
	
	@Value(value = "${data.exception.idNotFound}")
	private String idNotFound;
	
	@Value(value = "${data.exception.noRecords}")
	private String noRecords;
	
	@ExceptionHandler(value = RestaurantAlreadyExistException.class)
	public ResponseEntity<String>RestaurantAlreadyExistException(RestaurantAlreadyExistException fa) {
		return new ResponseEntity<String>(foodExists, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<String>IdNotFoundException(IdNotFoundException fa) {
		return new ResponseEntity<String>(idNotFound, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = NoRecordsFounds.class)
	public ResponseEntity<String>NoRecordsFounds(IdNotFoundException fa) {
		return new ResponseEntity<String>(noRecords, HttpStatus.CONFLICT);
	}
	
	
	
	
	
	
}
