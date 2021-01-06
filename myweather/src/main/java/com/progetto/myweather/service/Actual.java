package com.progetto.myweather.service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.Vector;
import com.progetto.myweather.model.MeteoCitta;

public class Actual implements RequestApi{
	
	
	
	public Vector<MeteoCitta> meteoActual(){
		Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
		CallApi ca= new CallApi();
		citta = ca.ApiCall();
		return citta;
		
	}
	
	
	public void meteoStatistiche() {
		
	}
}
