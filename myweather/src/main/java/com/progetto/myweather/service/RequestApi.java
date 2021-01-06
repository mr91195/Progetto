package com.progetto.myweather.service;
import com.progetto.myweather.model.*;
import java.util.Vector;

public interface RequestApi {
	
	public void meteoStatistiche();
	
	public Vector<MeteoCitta> meteoActual();

}
