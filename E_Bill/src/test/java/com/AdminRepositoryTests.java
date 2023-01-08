package com;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.model.Admin;
import com.repository.AdminRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AdminRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
	     
	    @Autowired
	    private AdminRepository repo;
	     
	    @Test
	    public void testCreateUser() {
	        Admin user = new Admin();
	        user.setEmail("admin1@gmail.com");
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode("abcdef");
	        user.setPassword(encodedPassword);
	        
	        Admin savedUser = repo.save(user);
	         
	        Admin existUser = entityManager.find(Admin.class, savedUser.getId());
	         
	        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	         
	    }
}