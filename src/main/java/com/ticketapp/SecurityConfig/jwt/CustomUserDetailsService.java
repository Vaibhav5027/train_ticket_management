package com.ticketapp.SecurityConfig.jwt;

import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ticketapp.Repository.UserRepository;
import com.ticketapp.entities.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	public User userDetails;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("inside loadUserByUsername {} ",username);
	   userDetails = userRepository.findByUsername(username);
		 
		 if(!Objects.isNull(userDetails)) {
			 return new org.springframework.security.core.userdetails.User(userDetails.getUsername(), userDetails.getPassword(),new ArrayList<>() );
		 }
		 else {
			 throw new UsernameNotFoundException("User Not Found");
		 }

	}
	public User getUserDetails() {
		return userDetails;
	}

}
