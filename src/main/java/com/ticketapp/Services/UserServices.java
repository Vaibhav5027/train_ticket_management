package com.ticketapp.Services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ticketapp.entities.User;

public interface UserServices {

	
ResponseEntity<?> signUp( User user );
ResponseEntity<?> login( Map<String,Object> requestMap);
ResponseEntity<?> forgotPassword();
ResponseEntity<?> changePassword(Map<String,Object> requestMap);
}
