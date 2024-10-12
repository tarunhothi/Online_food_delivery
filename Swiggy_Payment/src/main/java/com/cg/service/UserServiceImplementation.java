package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.exception.PasswordValidationException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.model.User;
import com.cg.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImplementation(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public UserServiceImplementation() {
		super();
	}
	
	public User Registration(User user) throws UserAlreadyExistsException, PasswordValidationException {
		if(userRepo.existsById(user.getId())) {
			throw new UserAlreadyExistsException();
		}
		if(user.getPassword().length() < 8) {
			throw new PasswordValidationException();
		}
		System.out.println(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		User res = userRepo.save(user);
		return res;
	}
	
	public String Login(User user) {
		String msg;
		if(userRepo.findByEmail(user.getEmail()) != null ) {
			User ema = userRepo.findByEmail(user.getEmail());
			String s1 = ema.getPassword();
			String s2 = user.getPassword();
			if(bCryptPasswordEncoder.matches(s2, s1)) {
				msg = "Login Successfully";
			}
			else {
				msg = "Wrong password! Please enter correct password.";
			}
		}else {
			msg = "User not found.";
		}
		return msg;
	}
}
