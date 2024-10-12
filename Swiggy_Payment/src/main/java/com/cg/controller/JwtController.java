package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.helper.JwtUtil;
import com.cg.model.JwtRequest;
import com.cg.model.JwtResponse;
import com.cg.service.CustomUserDetailService;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/api/v1/token", method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest+" Error");
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT--"+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}

