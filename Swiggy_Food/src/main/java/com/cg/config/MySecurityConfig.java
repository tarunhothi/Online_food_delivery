package com.cg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			 .csrf()	
			 .disable()
			 .cors()
			 .disable()
			 .authorizeRequests()
			 .antMatchers("/api/v1/token","/api/v1/registration", "/api/v1/login").permitAll()
			 .antMatchers("/api/v1/test").hasRole("ADMIN")
			 .anyRequest()
			 .authenticated()
			 .and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(entryPoint);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
