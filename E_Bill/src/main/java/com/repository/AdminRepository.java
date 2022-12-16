package com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	@Query("SELECT u FROM Admin u WHERE u.email = ?1")
	public Admin findByEmail(String email);
}
