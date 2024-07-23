package com.ticketapp.controllers;

import java.util.Map;

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
	ResponseEntity<?> signUp(@RequestBody User user){
		return userServices.signUp(user);
	}

	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody(required = true) Map<String,Object> requestMap){
		return userServices.login(requestMap);
	}
	@PostMapping("/changePassword")
	ResponseEntity<?> changePassword(@RequestBody(required = true) Map<String,Object> requestMap){
		return userServices.changePassword(requestMap);
	}
}
