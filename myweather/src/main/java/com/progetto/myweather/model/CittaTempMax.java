package com.progetto.myweather.model;

public class CittaTempMax extends Citta{
	
	public double temp_max;

	public CittaTempMax() {
		super();
		
	}

	public CittaTempMax(String name, double temp_max) {
		super(name);
		this.temp_max=temp_max;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
	
	

}
