package com.progetto.myweather.service;

import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.filtri.Filtri;
import com.progetto.myweather.model.*;

import java.util.Vector;
import org.springframework.stereotype.Component;


@Component
public class Actual implements RequestApi{
	
	Filtri filtri;
	CallApi ca= new CallApi();
	
	public Vector<Citta> meteoActual(String box){
		 return ca.ApiCall(box);
	}
	
	public Vector<Citta> meteoActualBox(double lon_left, double lat_bottom, double lon_right, double lat_top){
		return ca.ApiCallRectangle(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	public void chiamataOraria() {}
}
