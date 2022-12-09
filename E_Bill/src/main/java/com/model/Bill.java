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
	private int id;
	
	@Column(name ="meterno", nullable = false, unique = true, length = 10)
	private int meterno;
	
	@Column(name="month", nullable = false, length = 15)
	private String month;
	
	@Column(name="amount", nullable = false, length = 10)
	private int amount;
	
	@Column(name = "unit", nullable = false, length = 10)
	private int unit;
	
	@Column(name = "status", nullable = false, length = 20)
	private String status;
	
	public Bill() {
		
	}

	public Bill(int id, int meterno, String month, int amount, int unit, String status) {
		this.id = id;
		this.meterno = meterno;
		this.month = month;
		this.amount = amount;
		this.unit = unit;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getMeterno() {
		return meterno;
	}
	public void setMeterno(int meterno) {
		this.meterno = meterno;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
