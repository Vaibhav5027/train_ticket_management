package com.ticketapp.SecurityConfig.jwt;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ticketapp.entities.User;

public class CustomUserDetails implements UserDetails {
  
 private final User user;
 CustomUserDetails(User user){
	 this.user=user;
 }
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  String roleString = user.getRole().toUpperCase();
	        if (roleString == null || roleString.isEmpty()) {
	            return Collections.emptyList(); // Return empty list if no roles
	        }
	        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleString.trim()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
