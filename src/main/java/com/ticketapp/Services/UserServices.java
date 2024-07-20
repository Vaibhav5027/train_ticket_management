package com.ticketapp.Services;

import org.springframework.http.ResponseEntity;

import com.ticketapp.entities.User;

public interface UserServices {
 
ResponseEntity<?> registerUser(User user);
}
