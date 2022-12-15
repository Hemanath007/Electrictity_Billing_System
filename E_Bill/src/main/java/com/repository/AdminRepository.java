package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	public Admin findByEmail(String email);
}
