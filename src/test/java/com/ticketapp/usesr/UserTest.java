package com.ticketapp.usesr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ticketapp.ServiceImpl.UserServiceImpl;
import com.ticketapp.entities.Address;
import com.ticketapp.entities.User;

public class UserTest {
	@Autowired
    private UserServiceImpl userService;
	
	@Test
	public void addUser() {
	User user=new User();
	Address address=new Address();
	user.setFirstName("john");
	user.setLastName("Doe");
	user.setMobileNumber(8975358487d);
	address.setCityName("jorden");
	address.setCountry("eng");
	address.setPincode(254587);
	address.setState("xyz");
	user.setAddress(address);
	
	userService.signUp(user);
	}
}
