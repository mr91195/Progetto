package com.progetto.myweather.service;
import com.progetto.myweather.model.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import org.json.simple.*;
import org.json.simple.parser.*;

public class CallApi {
	
	public Vector<MeteoCitta> ApiCall() {
		String box1 = "12.584160791301779,43.12726427106371,14,44.141951278387214,20";
		String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
		String site2 = "&appid=e1d1909fbc1e5ac544a707c9140efee5&lang=it";
		JSONParser parser = new JSONParser();
		JSONObject obj ;
		Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
		
		try {
			URLConnection openConnection = new URL(site1+box1+site2).openConnection();
			InputStream input = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader (input));
			obj = (JSONObject) parser.parse(buf); 
			citta=elabora(obj);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return citta;
		
	}
	
	/*
	 * Metodo che elabora la Stringa ricevuta da ApiCall e preleva i parametri
	 */
	
	private Vector<MeteoCitta> elabora(JSONObject obj) {
		
		final Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
		JSONArray  a = (JSONArray) obj.get("list");
		
		 for(int i = 0 ; i<a.size();i++){ 
			 JSONObject jo = (JSONObject) a.get(i);
			 String name = (String) jo.get("name");
			 
			 
			 JSONObject main = (JSONObject) jo.get("main");
			 double temp = Double.parseDouble( main.get("temp").toString());
			 double temp_min = Double.parseDouble(main.get("temp_min").toString());
			 double temp_max = Double.parseDouble (main.get("temp_max").toString());
			 citta.add(new MeteoCitta(name, temp, temp_min,temp_max));
			 }
		 return citta;
	}
	
	
	

}
