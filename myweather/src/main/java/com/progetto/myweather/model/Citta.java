package com.progetto.myweather.model;

public abstract class Citta {
	public String name;
	
	public Citta (String name) {
		this.name=name;
	}
	public Citta() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return getName();
	}
}
