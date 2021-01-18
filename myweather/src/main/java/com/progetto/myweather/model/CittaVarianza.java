package com.progetto.myweather.model;

public class CittaVarianza extends Citta{
	public double varianza;

	public CittaVarianza(String name,double varianza) {
		super(name);
		this.varianza = varianza;
	}
	public CittaVarianza () {
		
	}
	protected double getVarianza() {
		return varianza;
	}
	protected void setVarianza(double varianza) {
		this.varianza = varianza;
	}
	
}
