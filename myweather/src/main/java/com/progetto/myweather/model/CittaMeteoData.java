package com.progetto.myweather.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CittaMeteoData extends Citta{
	private String data;
	private double temp_media;
	private double temp_max;
	private double temp_min;
	private double escursione;
	
	public CittaMeteoData(String name,long data ,double temp_max, double temp_min) {
		super(name);
		this.data = generaData(data);
		this.temp_max = temp_max;
		this.temp_min = temp_min;
		this.temp_media = (temp_max + temp_min)/2;
		this.escursione=  temp_max - temp_min;
		
	}
	
	public CittaMeteoData(CittaMeteoData o) {
		super(o.getName());
		this.data = o.getData();
		this.temp_max = o.getTemp_max();
		this.temp_min = o.getTemp_min();
		this.temp_media = o.getTemp_media();
		this.escursione= o.getEscursione();
	}
	public CittaMeteoData () {
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
	
	private String generaData(long mil) {
		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendario = Calendar.getInstance();
		calendario.setTimeInMillis(mil*1000L);
		return formato.format(calendario.getTime());
	}
	
}
