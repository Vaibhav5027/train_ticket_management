package com.ticketapp.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ticketapp.Repository.UserRepository;
import com.ticketapp.Services.UserServices;
import com.ticketapp.entities.User;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private UserRepository userRepositiry;

	@Override
	public ResponseEntity<String> registerUser(User user) {
          User save = userRepositiry.save(user);
          if(save.getUserId()>1) {
        	 return new ResponseEntity<String>("{ \"message\": \"" + "registered succesfully" + "\" }",HttpStatus.CREATED);
          }
		return new ResponseEntity<String>("{\"error\":\""+"user not registered"+"\"}",HttpStatus.BAD_REQUEST);
	}

}
