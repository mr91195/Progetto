package com.progetto.myweather.service;
import com.progetto.myweather.model.*;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
public class CallApi {
	
	String key ="e1d1909fbc1e5ac544a707c9140efee5";
	
	private Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
	
	public Vector<MeteoCitta> ApiCall(String box) {
		
		// valuto quale box devo passare all'API
		String box_selezionato="";
		String box_min ="12.984160791301779,43.32726427106371,13.9,43.841951278387214,20";
		String box_medium="12.784160791301779,43.21726427106371,14.1,43.951951278387214,20";
		String box_max= "12.584160791301779,43.12726427106371,14,44.141951278387214,20";
		
		if(box.equals("box1")) {
			box_selezionato=box_min;
		}
		else if (box.equals("box2")) {
			box_selezionato=box_medium;
		}
		else if (box.equals("box3")) {
			box_selezionato=box_max;
		}
		else throw new IllegalArgumentException("ERROR: Box non valido");
		
		String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
		String site2 = "&appid=";
		JSONParser parser = new JSONParser();
		JSONObject obj ;
		Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
		
		try {
			URLConnection openConnection = new URL(site1+box_selezionato+site2+key).openConnection();
			InputStream input = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader (input));
			obj = (JSONObject) parser.parse(buf); 
			citta=elabora(obj);
			citta=evidenziaStatistiche(citta);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e)
		{e.printStackTrace();}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return citta;
		
	}
	
	public Vector <MeteoCitta> ApiCallRectangle (double lon_left, double lat_bottom, double lon_right, double lat_top){
		String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
		String site2 = "&appid=";
		JSONParser parser = new JSONParser();
		JSONObject obj ;
		
		
		try {
			
			URLConnection openConnection = new URL(site1+lon_left+","+lat_bottom+","+lon_right+
									","+lat_top+",20"+site2+key).openConnection();
			InputStream input = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader (input));
			obj = (JSONObject) parser.parse(buf); 
			citta=elabora(obj);
			
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e)
		{e.printStackTrace();}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return citta;
	}
	
	/*
	 * Metodo che elabora la Stringa ricevuta da ApiCall e preleva i parametri
	 */
	
	public Vector<MeteoCitta> elabora(JSONObject obj) {
		
		Vector<MeteoCitta> citta = new Vector<MeteoCitta>();
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
	
	private Vector<MeteoCitta> evidenziaStatistiche(Vector <MeteoCitta> citta) {
		
		Statistiche stat = new Statistiche();
		return stat.calcolaStatistiche(citta);
		
	}
	
	
	

}
