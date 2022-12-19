package com.security;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.detailservice.CustomerUserDetailsService;

@Configuration
@Order(1)
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
		 http.antMatcher("/customer/**")
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .formLogin()
             .loginPage("/customer/login")
             .usernameParameter("email")
             .loginProcessingUrl("/customer/login")
             .defaultSuccessUrl("/customer/home")
             .permitAll()
         .and()
             .logout()
                 .logoutUrl("/customer/logout")
                 .logoutSuccessUrl("/");

     return http.build();
 }
}
