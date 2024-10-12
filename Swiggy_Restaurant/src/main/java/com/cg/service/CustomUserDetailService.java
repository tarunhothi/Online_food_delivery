package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.model.CustomUserDetails;
import com.cg.model.User;
import com.cg.repository.UserRepository;

@Service 
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("No User Found");
		}
		return new CustomUserDetails(user);
	}

}
