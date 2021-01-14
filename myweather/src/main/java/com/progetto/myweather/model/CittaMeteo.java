package com.progetto.myweather.model;

public class CittaMeteo extends Citta{
	 public double temp_media;
	 public double temp_max;
	 public double temp_min;
	 public double escursione;
	
	public CittaMeteo(String name, double temp_max, double temp_min) {
		super(name);
		this.temp_max=temp_max;
		this.temp_min=temp_min;
		this.temp_media=(temp_min+temp_max)/2;
		this.escursione= temp_max-temp_min;
	}
	
	public CittaMeteo() {
		
	}
	public CittaMeteo(CittaMeteo o) {
		super(o.name);
		this.temp_max=o.temp_max;
		this.temp_min=o.temp_min;
		this.temp_media=o.temp_media;
		this.escursione=o.escursione;
	}

	public double getTemp_media() {
		return temp_media;
	}

	public void setTemp_media(double temp_media) {
		this.temp_media = temp_media;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public double getEscursione() {
		return escursione;
	}

	public void setEscursione(double escursione) {
		this.escursione = escursione;
	}
	public String getName() {
		return super.getName();
	}
}
