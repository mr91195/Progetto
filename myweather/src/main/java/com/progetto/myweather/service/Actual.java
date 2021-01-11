package com.progetto.myweather.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Vector;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.Vector;
import org.json.simple.parser.ParseException;

import com.progetto.myweather.model.Filtri;
import com.progetto.myweather.model.MeteoCitta;
//import com.sun.el.parser.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Actual implements RequestApi{
	
	Filtri filtri;
	CallApi ca= new CallApi();
	
	public Vector<MeteoCitta> meteoActual(String box){
		 return ca.ApiCall(box);
	}
	
	public Vector<MeteoCitta> meteoActualBox(double lon_left, double lat_bottom, double lon_right, double lat_top){
		return ca.ApiCallRectangle(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	
	public Vector<MeteoCitta> meteoStatistiche(String box, int periodo){
		Vector<MeteoCitta> statistiche=new Vector<MeteoCitta>();
		
			statistiche = filtri.calcoloPeriodo(periodo);
		
		return filtri.filtraggio(statistiche, box);
	}
}
