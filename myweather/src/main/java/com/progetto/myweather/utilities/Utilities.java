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
import com.progetto.myweather.model.CittaMeteo;


public class Utilities {
	
	String docuMax = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMax.txt";
	String docuMid = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMedium.txt";
	String docuMin = "C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\cittaBoxMin.txt";
	
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
	public Vector <Citta> calcoloPeriodo(int periodo) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		File path = new File ("C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio");
		String[] files = path.list();
	    Vector <Citta> citta = new Vector <Citta>();
		
       for(int i =0;i<periodo;i++) {
       
    	   		BufferedReader buf = new BufferedReader (new FileReader(
    	   												path + "\\" + files[i]));
    	   		String elem = buf.readLine();
    	   		obj = (JSONObject) parser.parse(elem);
    	   		Vector <Citta> tmp =new Vector<Citta> ();
    	   		tmp = parser(obj);
    	   		for ( Citta o : tmp) {
    	   			citta.add(o);
    	   		}
    	   		buf.close();
        }
       return citta;
   	}
	
	public Vector <Citta> filtraggio(int periodo,String box) throws IOException, ParseException{
		Vector <Citta> cittaPeriodo = null;
		cittaPeriodo = calcoloPeriodo(periodo);
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
	
	
		public Vector<Citta> parser(JSONObject obj) {
		
		Vector<Citta> citta = new Vector<Citta>();
		JSONArray  a = (JSONArray) obj.get("list");
		
		 for(int i = 0 ; i<a.size();i++){ 
			 JSONObject jo = (JSONObject) a.get(i);
			 String name = (String) jo.get("name");
			 
			 
			 JSONObject main = (JSONObject) jo.get("main");
			 //double temp = Double.parseDouble( main.get("temp").toString());
			 double temp_min = Double.parseDouble(main.get("temp_min").toString());
			 double temp_max = Double.parseDouble (main.get("temp_max").toString());
			 citta.add(new CittaMeteo(name, temp_max ,temp_min));
			 }
		 return citta;
		}
}