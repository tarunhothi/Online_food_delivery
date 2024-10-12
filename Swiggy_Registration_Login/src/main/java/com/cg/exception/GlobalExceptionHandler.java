package com.cg.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Value(value="${data.exception.msg}")
	private String msg;
	
	@Value(value="${data.exception.pwdValidationMsg}")
	private String pwdValidationMsg;
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<String>UserAlreadyExistsException(UserAlreadyExistsException u){
		return new ResponseEntity<String>(msg, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = PasswordValidationException.class)
	public ResponseEntity<String>PasswordValidationException(PasswordValidationException p){
		return new ResponseEntity<String> (pwdValidationMsg, HttpStatus.CONFLICT);
	}
}
