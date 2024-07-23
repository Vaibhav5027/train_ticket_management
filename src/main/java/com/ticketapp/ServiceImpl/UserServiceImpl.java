package com.ticketapp.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ticketapp.SecurityConfig.jwt.AuthTokenFilter;
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
	
	@Autowired
	AuthTokenFilter authFilter;

	private static final Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public ResponseEntity<String> signUp(User user) {
		logger.info("inside signUp ");
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
		logger.info("inside loginUp ");
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
		  
		


	@Override
	public ResponseEntity<?> forgotPassword() {
		
		return null;
	}

	@Override
	public ResponseEntity<?> changePassword(Map<String,Object> requestMap) {
		String user = authFilter.currentUser();
		User savedUser = userRepositiry.findByUsername(user);
		if(savedUser!=null) {
			String oldPassword = (String) requestMap.get("oldPassword");
			String password = savedUser.getPassword();
			boolean verifyPassword = verifyPassword(oldPassword, password);
			if(verifyPassword) {
				String newPassword = (String) requestMap.get("newPassword");
				savedUser.setPassword(newPassword);
				User save = userRepositiry.save(savedUser);
				if(save.getUserId()>0) {
					return new ResponseEntity<String>("{ \"message\": \"" + "password changed succesfully" + "\" }",
							HttpStatus.CREATED);
				}
			}
		}
		return new ResponseEntity<String>("{\"error\":\"" + "something went wrong" + "\"}", HttpStatus.BAD_REQUEST);
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
