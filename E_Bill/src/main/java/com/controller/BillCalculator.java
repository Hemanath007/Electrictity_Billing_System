package com.controller;

public class BillCalculator {

	private int units;
	private String state;
	private String ctype;
	
	public BillCalculator() {
		
	}
	
	public BillCalculator(int units, String state, String ctype) {
		
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
	
	public float billCalci() {
		float cost;		
		if(ctype.equals("house")){
			cost=1;
		}
		else if(ctype.equals("domestic")){
			cost = (float)1.5;
		}
		
		else{
			cost =2;
		}
		if(state.equals("tamilnadu")){
			if(units<=100) {
				cost *=(float) (units*1.1);
			}
			else if(units<=300) {
				cost *= (float)(units * 2);
			}
			else {
				cost *= (float)(units * 2.5);
			}
			cost *=1.5;
		}
		else if(state.equals("kerala")){
			if(units<=100) {
				cost *=(float) (units*1.1);
			}
			else if(units<=300) {
				cost *= (float)(units * 2);
			}
			else {
				cost *= (float)(units * 2.5);
			}
			cost *=1.3;
		}
		else if(state.equals("karnataka")){
			if(units<=100) {
				cost *=(float) (units*1.1);
			}
			else if(units<=300) {
				cost *= (float)(units * 2);
			}
			else {
				cost *= (float)(units * 2.5);
			}
			cost *=1.8;
		}
		else{
			if(units<=100) {
				cost *=(float) (units*1.1);
			}
			else if(units<=300) {
				cost *= (float)(units * 2);
			}
			else {
				cost *= (float)(units * 2.5);
			}
			cost *=1.7;
		}
	
		cost = cost +((units*18)/100);
		return cost;
	}
}
