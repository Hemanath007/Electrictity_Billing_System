package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customera")
public class Customer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
    @Column(name = "name")
	private String name;
    @Column(name = "address")
	private String address;
    @Column(name = "state")
	private String state;
    @Column(name = "city")
	private String city;
    @Column(name = "email" , nullable = false, unique = true, length = 45)
    private String email;
    @Column(name="password")
    private String password;	
    @Column(name = "phone", nullable = false, unique = true, length = 10)
	private long phone;

	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
}