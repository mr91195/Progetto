package com.progetto.myweather.model;

public class MeteoCitta {

	String name;
	double temp;
	double temp_min;
	double temp_max;
	double varianza;
	
	public MeteoCitta(String name, double temp, double temp_min, double temp_max) {
		this.name=name;
		this.temp=temp;
		this.temp_min=temp_min;
		this.temp_max=temp_max;
		varianza = 0;
	}
	
}
