package com.ticketapp.SecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ticketapp.SecurityConfig.jwt.AuthEntryPointJwt;
import com.ticketapp.SecurityConfig.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
	
	@Autowired
	DataSource dataSource;
	@Autowired
	AuthEntryPointJwt authEntryPointJwt;
	@Autowired
	AuthTokenFilter authTokenFilter;
	
//	@Bean
//	JdbcUserDetailsManager users() {
//		JdbcUserDetailsManager jdbcUsersDetailsManger= new JdbcUserDetailsManager(dataSource);
//		return jdbcUsersDetailsManger;
//	}
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
 	http.csrf(csrf -> csrf.disable())
	.authorizeHttpRequests(req -> req.requestMatchers("api/user/register","api/user/login","api/user/forgotPassword")
		.permitAll())
		.authorizeHttpRequests(req->req.anyRequest().authenticated());
		http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
		addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	UserDetailsService userDetailsService(DataSource datasource) {
	return  new JdbcUserDetailsManager(datasource);
	}
	 @Bean
	 public CommandLineRunner initData(UserDetailsService userDetailsService) {
		 return args->{
			 JdbcUserDetailsManager jdbcUserDetailsManager= (JdbcUserDetailsManager) userDetailsService;
				UserDetails user1=User.withUsername("user").password("userpass").roles("USER").build();
				UserDetails admin=User.withUsername("admin").password("adminpass").roles("Admin").build();
				JdbcUserDetailsManager manage= new JdbcUserDetailsManager(dataSource);
				manage.createUser(user1);
				manage.createUser(admin);
		 };
	 	
		
}}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

