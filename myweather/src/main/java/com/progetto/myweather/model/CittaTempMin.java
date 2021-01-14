package com.progetto.myweather.model;

public class CittaTempMin extends Citta{
	public double temp_min;

	public CittaTempMin(String name, double temp_min) {
		super(name);
		this.temp_min = temp_min;
	}

	public CittaTempMin() {
		super();
	}

	protected double getTemp_min() {
		return temp_min;
	}

	protected void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	
}
