package com.progetto.myweather.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties(ignoreUnknown = true)

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
		this.varianza = temp_max-temp_min;
	}
	public MeteoCitta() {
		
	}
	
	public String toString() {
		return String.format(
				"Student [name=%s, temperatura=%s, temperatura minima=%s, temperatura massima=%s]",
				name, temp, temp_min,temp_max);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	public double getVarianza() {
		return varianza;
	}
	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}
}
