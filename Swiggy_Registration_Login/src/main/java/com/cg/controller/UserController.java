package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.PasswordValidationException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.model.User;
import com.cg.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService uServ;

	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/registration")
	public ResponseEntity<User> Registration(@RequestBody User user) throws UserAlreadyExistsException, PasswordValidationException{
		User registerdUser = uServ.Registration(user);
		return new ResponseEntity<>(registerdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> Login(@RequestBody User user){
		String s = uServ.Login(user);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
