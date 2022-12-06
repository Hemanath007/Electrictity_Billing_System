package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("SELECT u FROM Customer u WHERE u.email = ?1")
	public Customer findByEmail(String email);	
	
	public Customer findByEmailOrPhone(String email, Long phone);
	
	@Query("SELECT u FROM Customer u WHERE u.customerid = ?1")
	public Customer findByCustomerid(int id);
}

