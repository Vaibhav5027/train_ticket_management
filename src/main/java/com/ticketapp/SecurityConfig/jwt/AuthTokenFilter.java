package com.ticketapp.SecurityConfig.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	private String userName = null;
 
	@Autowired
	private CustomUserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.debug("AuthTokenFilter called for URL:{}", request.getServletPath());
		if (request.getServletPath().matches("/api/user/register|/api/user/login|/user/forgotPassword|")) {
			System.out.println(request.getServletPath());
			filterChain.doFilter(request, response);
		}
		else {
		String token = jwtUtils.getJwtTokenFromHeader(request);
		if (token != null && jwtUtils.validateToken(token)) {
			 userName = jwtUtils.getUsernameFromToken(token);

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userDetailsService.loadUserByUsername(userName);
				String password = user.getPassword();
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userName, null,null);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}
		filterChain.doFilter(request, response);
	}
	}
	public String currentUser() {
		return userName;
	}
}
