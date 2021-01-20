package com.progetto.myweather.service;
import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.filtri.FiltraMaxMin;
import com.progetto.myweather.model.*;
import com.progetto.myweather.utilities.*;
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

/*
 * classe che si occupa di fare la chiamata all'API CurrentWeather
 */
public class CallApi {
	
	
		Varianza varianza = new Varianza();
		UtilitiesHttp utilitiesHttp = new UtilitiesHttp(); 
		Utilities	utilities = new Utilities ();
		
		
		/*
		 * Metodo chiamato da SimpleRestController
		 * mediante una String box passata dall'utente (box1-box2-box3)
		 * restituisce il Vector di citta presenti nel box con temperature attuali
		 * 
		 * @param box
		 * @throws CustomException
		 * @return vector con le temperature attuali delle citta relative al box
		 */
		public Vector<Citta> ApiCall(String box) throws CustomException{
			String FileBox ="C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\FileBox.txt";
			Vector<String> boxStringhe = utilitiesHttp.elaboraStringheBox(FileBox);
			switch(box) {
			case "box1" :  break;
			case "box2" :  break;
			case "box3" :  break;
			default : throw new CustomException("Box selezionato errato");
			}
				
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
				citta = utilities.parser(obj,false);
				citta = evidenziaStatistiche(citta);
				citta = varianza.aggiungiVarianza(citta,false);
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

		/*
		 * metodo simile che resituisce le citta presenti nel box con le temperature attuali
		 * il metodo ha come input i parametri del box passati manualmente dall'utente
		 * @param lon-lef
		 * @param lat-bottom
		 * @param lon-right
		 * @param lat-top
		 * @throws CustomException
		 * @return meteo attuale presenti nel box scelto
		 */
		public Vector <Citta> ApiCallRectangle (double lon_left, double lat_bottom, double lon_right, double lat_top) throws CustomException{
			String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
			String site2 = "&appid=";
			JSONParser parser = new JSONParser();
			JSONObject obj ;
			Vector<Citta> citta = new Vector<Citta>();
			
			if(lon_left>lon_right)
				throw new CustomException("lon-left maggiore di lon-right, parametro errato");
			else if (lat_bottom > lat_top)
				throw  new CustomException("lat-bottom maggiore di lat-top, parametro errato");
			
			try {
				
				URLConnection openConnection = new URL(site1+lon_left+","+lat_bottom+","+lon_right+
										","+lat_top+",20"+site2+utilitiesHttp.getKey()).openConnection();
				InputStream input = openConnection.getInputStream();
				BufferedReader buf = new BufferedReader(new InputStreamReader (input));
				obj = (JSONObject) parser.parse(buf); 
				citta = utilities.parser(obj,false);
				citta = varianza.aggiungiVarianza(citta,false);
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
		
		/*
		 * metodo privato che richiama aggiungiMaxMin di FiltraMaxMin
		 * aggiunge al vector due oggetti, un oggetto  CittaTempMax
		 * ed un ogetto CittaTempMin
		 * 
		 * @param citta : vettore sul quale calcola la citta con temp_max e temp_min
		 * @return statistiche sulle citta ricevute
		 */
		
		private Vector<Citta> evidenziaStatistiche(Vector <Citta> citta) {
			
			FiltraMaxMin aggiungi = new FiltraMaxMin();
			return aggiungi.aggiungiMaxMin(citta,false);
			
		}
		
		
	

}

