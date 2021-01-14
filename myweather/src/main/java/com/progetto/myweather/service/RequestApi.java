package com.progetto.myweather.service;
import com.progetto.myweather.model.*;
import java.util.Vector;

public interface RequestApi {
	
	//public Vector<MeteoCitta> meteoStatistiche(String box, int periodo);
	
	public Vector<Citta> meteoActual(String box);
	
	public Vector<Citta> meteoActualBox(double lon_left, double lat_bottom, double lon_right, double lat_top);
	

}
