package com.controller;

public class BillCalculator {

	private int units;
	private String state;
	private String ctype;
	
	public BillCalculator() {
		
	}
	
	public BillCalculator(int units, String state, String ctype) {
		super();
		this.units = units;
		this.state = state;
		this.ctype = ctype;
	}
	
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	
}
