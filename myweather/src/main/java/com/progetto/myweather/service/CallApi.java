package com.progetto.myweather.service;
import com.progetto.myweather.model.*;
import com.progetto.myweather.utilities.*;
import com.progettomyweather.filtri.FiltraMaxMin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.Service;
@Service
public class CallApi {
		
		UtilitiesHttp utilitiesHttp = new UtilitiesHttp(); 
		Utilities	utilities = new Utilities ();
		public Vector<Citta> ApiCall(String box) {
			
			String FileBox ="C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\FileBox.txt";
			Vector<String> boxStringhe = utilitiesHttp.elaboraStringheBox(FileBox);
		    String box_selezionato = utilitiesHttp.selezionaBox(boxStringhe,box);
			String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
			String site2 = "&appid=";
			JSONParser parser = new JSONParser();
			JSONObject obj ;
			Vector<Citta> citta = new Vector<Citta>();
			
			try { 
				URLConnection openConnection = new URL(site1+box_selezionato+site2+utilitiesHttp.getKey()).openConnection();
				InputStream input = openConnection.getInputStream();
				BufferedReader buf = new BufferedReader(new InputStreamReader (input));
				obj = (JSONObject) parser.parse(buf); 
				citta=utilities.parser(obj);
				evidenziaStatistiche(citta);
				buf.close();
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

	
	public Vector <Citta> ApiCallRectangle (double lon_left, double lat_bottom, double lon_right, double lat_top){
		String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
		String site2 = "&appid=";
		JSONParser parser = new JSONParser();
		JSONObject obj ;
		Vector<Citta> citta = new Vector<Citta>();
		
		try {
			
			URLConnection openConnection = new URL(site1+lon_left+","+lat_bottom+","+lon_right+
									","+lat_top+",20"+site2+utilitiesHttp.getKey()).openConnection();
			InputStream input = openConnection.getInputStream();
			BufferedReader buf = new BufferedReader(new InputStreamReader (input));
			obj = (JSONObject) parser.parse(buf); 
			citta=utilities.parser(obj);
			buf.close();
			
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
	

	
	
	
	
	
	private Vector<Citta> evidenziaStatistiche(Vector <Citta> citta) {
		
		FiltraMaxMin aggiungi = new FiltraMaxMin();
		return aggiungi.aggiungiMaxMin(citta);
		
	}
	
	
	

}

