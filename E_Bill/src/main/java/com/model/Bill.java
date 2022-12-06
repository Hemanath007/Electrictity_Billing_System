package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerid;
	
	@Column(nullable = false, unique = true, length = 15)
	private String month;
	
	@Column(nullable = false, length = 10)
	private int anount;
	
	@Column(name = "unit", nullable = false, length = 10)
	private int unit;
	
	@Column(name = "status", nullable = false, length = 20)
	private String status;
	
	public Bill() {
		
	}

	public Bill(Long customerid, String month, int anount, int unit, String status) {
		
		this.customerid = customerid;
		this.month = month;
		this.anount = anount;
		this.unit = unit;
		this.status = status;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getAnount() {
		return anount;
	}

	public void setAnount(int anount) {
		this.anount = anount;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
