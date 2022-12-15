package com.security;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.service.CustomerUserDetailsService;

@Configuration
@Order(2)
public class CustomerSecurityConfiguration{
	 
	@Bean
	public UserDetailsService customerUserDetailsService() {
		return new CustomerUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder2() {
		return new BCryptPasswordEncoder();
	}
	  @Bean
	    public DaoAuthenticationProvider authenticationProvider2() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(customerUserDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder2());
	 
	        return authProvider;
	    }
	
	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception{
		 http.authenticationProvider(authenticationProvider2());
		 
		 http.authorizeRequests()
			.antMatchers("/users/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
				.usernameParameter("email").passwordParameter("pass")
				.defaultSuccessUrl("/users")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		
		return http.build();
	}
}
