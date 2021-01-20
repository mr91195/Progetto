package com.progetto.myweather.service;
import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.model.*;
import java.util.Vector;

public interface RequestApi {
	
	public Vector<String> istruction();
	
	public Vector<Citta> meteoActual(String box)throws CustomException;
	
	public Vector<Citta> meteoActual(double lon_left, double lat_bottom, double lon_right, double lat_top)throws CustomException;
	
	
}
