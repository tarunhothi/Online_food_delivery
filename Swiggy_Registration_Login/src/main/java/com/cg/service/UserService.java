package com.cg.service;

import com.cg.exception.PasswordValidationException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.model.User;

public interface UserService {
	public User Registration(User user) throws UserAlreadyExistsException, PasswordValidationException;
	public String Login(User user);
}
