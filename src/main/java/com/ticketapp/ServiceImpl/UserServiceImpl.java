package com.ticketapp.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticketapp.Repository.UserRepository;
import com.ticketapp.SecurityConfig.jwt.JwtUtils;
import com.ticketapp.Services.UserServices;
import com.ticketapp.entities.User;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepositiry;
	
@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<String> signUp(User user) {
		String username = user.getUsername();
		User registeredUser = userRepositiry.findByUsername(username);
		if (registeredUser == null) {
			user.setRole("User");
			user.setPassword(encodedPassword(user.getPassword()));
			userRepositiry.save(user);
			return new ResponseEntity<String>("{ \"message\": \"" + "registered succesfully" + "\" }",
					HttpStatus.CREATED);
		} else
			return new ResponseEntity<String>("{\"error\":\"" + "user not registered" + "\"}", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> login(Map<String, Object> requestMap) {
//		  Authentication authenticate;
		  String userName=(String) requestMap.get("username");
		  String password=(String) requestMap.get("password");
		  
		  try {
			  Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			  SecurityContextHolder.getContext().setAuthentication(authenticate);
			  UserDetails principal = (UserDetails) authenticate.getPrincipal();
			  String jwtTokenFromUsername = jwtUtils.generateJwtTokenFromUsername(principal);
				 Map<String,Object> body=new HashMap<String, Object>();
					body.put("token", jwtTokenFromUsername);
					body.put("username", principal.getUsername());
					return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);	
			  }
		  
		  catch(AuthenticationException e) {
			 Map<String,Object> body=new HashMap<String, Object>();
				body.put("message", "Bad Credentials");
				body.put("status", false);
				return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
				
		  }
	}	
		  
		
	private String encodedPassword(String rawPasseord) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPasseord);
	}
	 public static boolean verifyPassword(String rawPassword, String encodedPassword) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.matches(rawPassword, encodedPassword);
	  }


}
