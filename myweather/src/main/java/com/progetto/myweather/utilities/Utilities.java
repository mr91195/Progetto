package com.progetto.myweather.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.progetto.myweather.model.Citta;
import com.progetto.myweather.model.CittaMeteoData;
import com.progetto.myweather.model.CittaMeteo;


public class Utilities {
	
	String docuMax = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMax.txt";
	String docuMid = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMedium.txt";
	String docuMin = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMin.txt";
	
	/*
	 * metodo che restituisce i nomi delle citta corrispondenti al box richiesto dall'utente
	 * 
	 * @param doc, contiene l'indirizzo del file che puo essere 'cittaBoxMax','cittaBoxMedium','cittaBoxMin'
	 * @return restituisce i nomi appartenti al box richiesto
	 */
	
	public Vector <String> elaboraFile(String doc) {
			
	        Vector <String> nomiCittaBox = new Vector <String>();
			BufferedReader fileReader;
			String element="";
			
			try {
				fileReader = new BufferedReader(new FileReader(doc));
			     element = fileReader.readLine();
				
				do {
					String[] f = element.split(",");
					for (int i=0; i < f.length;i++) {
						nomiCittaBox.add(f[i].trim());
						}
					}while(( fileReader.readLine()) != null);
				fileReader.close();
				}
				  catch (IOException e) {
					System.out.println("ERRORE di I/O");
				}
			return nomiCittaBox;
		}
		/*
		 * metodo che in base ad un periodo, popola un Vector con tutte le citta salvate in locale in base
		 * al periodo che corrisponde al numero di giorni a ritroso da considerare.
		 * 
		 * @param periodo: indica il numero di giorni richiesti, dunque i file in locale da lavorare
		 * @param singolaCitta: viene usata quando richiama parser()
		 * @return vettore popolato dalle citta dei giorni richiesti.
		 */
	public Vector <Citta> calcoloPeriodo(int periodo, boolean singolaCitta) throws IOException, ParseException {
		JSONParser parserjson = new JSONParser();
		JSONObject obj = new JSONObject();
		File path = new File ("C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio");
		String[] files = path.list();
	    Vector <Citta> citta = new Vector <Citta>();
		
       for(int i =0;i<periodo;i++) {
       
    	   		BufferedReader buf = new BufferedReader (new FileReader(
    	   												path + "\\" + files[i]));
    	   		String elem = buf.readLine();
    	   		obj = (JSONObject) parserjson.parse(elem);
    	   		Vector <Citta> tmp =new Vector<Citta> ();
    	   		tmp = parser(obj, singolaCitta);
    	   		for ( Citta o : tmp) {
    	   			citta.add(o);
    	   		}
    	   		buf.close();
        }
       return citta;
   	}
	/*
	 * Metodo che dopo aver ricevuto da 'calcoloPeriodo' tutte le citta corrispondenti ai giorni di storico scelti,
	 * si occupa di selezionare solamente le citta corrispondenti al box richiesto.
	 * 
	 * @param periodo : numero giorni degli storici
	 * @param box: box scelto dall'utente
	 * @return vector popolato dalle citta corrispondenti al box dell'utente per il numero di giorni.
	 */
	public Vector <Citta> filtraggio(int periodo,String box) throws IOException, ParseException{
		Vector <Citta> cittaPeriodo = null;
		cittaPeriodo = calcoloPeriodo(periodo, false);
		
		Vector<String> nomiCitta = new Vector<String> ();
		switch(box) {
		case "box1":	nomiCitta=elaboraFile(docuMin);	break;
		case "box2":	nomiCitta=elaboraFile(docuMid);	break;
		case "box3":	nomiCitta=elaboraFile(docuMax);	break;
		}
		Vector <Citta> citta = new Vector <Citta>();
        
        	 for (Citta o : cittaPeriodo ) {
        		 CittaMeteo m= (CittaMeteo) o;	//downcast
        		for ( String s : nomiCitta) {
        			if ( s.equals(o.getName())) {
        				 citta.add(new CittaMeteo(m));
        			}
        		}
        	 }
        return citta;
	}
	/*
	 * metodo al quale gli viene passato un JSONObject
	 * il quale si occupa di trasformarlo in un Vector prendendo i parametri utili
	 * 
	 * in base al boolean crea oggetti diversi, nel caso di 'false' crea oggetti di tipo CittaMeteo,
	 * viene richiamato da com.progetto.myweather.service.CallApi
	 * nel caso di 'true' crea ogetti di tipo CittaMeteoData , viene richiamato da com.progetto.myweather.filtri.FiltraCitta
	 * 
	 * @param obj 
	 * @param singolaCitta : identifica quale tipo di classe creare
	 * 
	 */
	
		public Vector<Citta> parser(JSONObject obj, boolean singolaCitta) {
		
		Vector<Citta> citta = new Vector<Citta>();
		JSONArray  a = (JSONArray) obj.get("list");
		if (singolaCitta == false) {
			for(int i = 0 ; i<a.size();i++){ 
			 JSONObject jo = (JSONObject) a.get(i);
			 String name = (String) jo.get("name");
			 
			 
			 JSONObject main = (JSONObject) jo.get("main");
			 double temp_min = Double.parseDouble(main.get("temp_min").toString());
			 double temp_max = Double.parseDouble (main.get("temp_max").toString());
			 citta.add(new CittaMeteo(name, temp_max ,temp_min));
			 }
		}
		else if(singolaCitta == true){
			for(int i = 0 ; i<a.size();i++){ 
				 JSONObject jo = (JSONObject) a.get(i);
			
				 String name = (String) jo.get("name");
				 long dataUTC = Long.parseLong(jo.get("dt").toString());
				 JSONObject main = (JSONObject) jo.get("main");
				 double temp_min = Double.parseDouble(main.get("temp_min").toString());
				 double temp_max = Double.parseDouble (main.get("temp_max").toString());
				 citta.add(new CittaMeteoData(name,dataUTC,temp_max,temp_min));
				 }
		}
		 
		 return citta;
		}
		
		/*
		 * metodo che legge un file txt contenente le istruzioni da restituire all'utente
		 * 
		 * @return stringhe contenente le istruzioni basi che l'utente puo usare
		 */
		
		public Vector<String> getHome()  {
			
			String homeFile="C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\home.txt";
			Vector<String> home= new Vector<String>();    
			
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(homeFile));
				
				do { 
					home.add(reader.readLine());
				
				} while((reader.readLine()) != null); 
			
				 reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return home;
		}
}