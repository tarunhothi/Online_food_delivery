package com.cg.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value(value = "${data.exception.cartExists}")
	private String cartExists;
	
	@Value(value = "${data.exception.idNotFound}")
	private String idNotFound;
	
	@Value(value = "${data.exception.noRecords}")
	private String noRecords;
	
	@ExceptionHandler(value = CartIdAlreadyExistException.class)
	public ResponseEntity<String>cartIdAlreadyExistException(CartIdAlreadyExistException fa) {
		return new ResponseEntity<String>(cartExists, HttpStatus.CONFLICT);
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
