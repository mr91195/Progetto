package com.progetto.myweather.model;

public class CittaFiltro {
	
	private String name;
	private int giorni;
	//costruttore
	public CittaFiltro(String name, int giorni) {
		this.name=name;
		this.giorni=giorni;
	}
	//costruttore
	public CittaFiltro() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGiorni() {
		return giorni;
	}
	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}
	
}
