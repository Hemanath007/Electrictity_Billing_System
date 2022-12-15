package com.model;

import javax.persistence.*;


@Entity
@Table(name = "admin")
public class Admin {

	
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	     
	    @Column(nullable = false, unique = true, length = 40)  
	    private String email;
	     
	    @Column(nullable = false, length = 64)
	    private String password;
	 
	    public Admin() {}
	     
	    public Admin(String email, String password) {
	        this.email = email;
	        this.password = password;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
}
