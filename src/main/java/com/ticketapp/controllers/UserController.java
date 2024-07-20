package com.ticketapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketapp.Services.UserServices;
import com.ticketapp.entities.User;

@RestController
@RequestMapping("api/user/")
public class UserController {
  
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/register")
	ResponseEntity<?> registerUser(@RequestBody User user){
		return userServices.registerUser(user);
		
	}
}
