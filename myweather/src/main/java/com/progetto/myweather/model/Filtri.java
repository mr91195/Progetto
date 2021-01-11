package com.progetto.myweather.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.*;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

import com.progetto.myweather.service.CallApi;

@Service
public class Filtri {
	
	String box_min ="box1";
	String box_medium="box2";
	String box_max= "box3";
	
		CallApi el = new CallApi();
        JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		String[] cittaBoxMin= {"Ancona","Osimo","Castelfidardo","Iesi","Recanati","Senigallia","Fano"};
		String[] cittaBoxMedium= {"Falconara Marittima", "Ancona","Osimo","Castelfidardo","Iesi","Recanati","Senigallia","Macerata","Civitanova Marche","Fano","Sant'Elpidio a Mare","Porto Sant'Elpidio","Fabriano","Pesaro"};
		String[] cittaBoxMax= { "Falconara Marittima", "Ancona","Osimo","Castelfidardo","Iesi","Recanati","Senigallia","Fano","Macerata","Pesaro","Fabriano","Tolentino", "Civitanova Marche","Urbino","Sant'Elpidio a Mare","Porto Sant'Elpidio","Cattolica","Gualdo Tadino","Fermo","Porto San Giorgio","Riccione"};
		
		/*
		 * METODO CHE LEGGE I FILE NELLA DIRECTORY E NE CREA UN ARRAY
		 * IL CONTENUTO DI QUESTI FILE VIENE LETTO, TRASFORMATO DA STRINGA A JSONOBJECT
		 * E POI PASSATO A ELABORA DELLA CLASSE CALLAPI CHE RITORNERà UN VECTOR DI TIPO CITTà
		 * ELENCANDOLI PER PERIODO 
	   */
		
		
	public Vector <MeteoCitta> calcoloPeriodo(int periodo)  {
		File path = new File ("C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio");
		String[] files = path.list();
	     Vector <MeteoCitta> citta = new Vector <MeteoCitta>();
		
       for(int i =0;i<periodo;i++) {
       
    	   		BufferedReader buf;
				try {
					buf = new BufferedReader (new FileReader(
															path + "\\" + files[i]));
    	   		String element;
					element = buf.readLine();
  
					obj = (JSONObject) parser.parse(element);
				}catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				
				
    	   		Vector <MeteoCitta> tmp =new Vector<MeteoCitta> ();
    	   		tmp = el.elabora(obj);
    	   		for ( MeteoCitta o : tmp) {
    	   			citta.add(o);
    	   		}
        }
       return citta;
	
   	}
	
	
	
	
	public Vector <MeteoCitta> filtraggio(Vector <MeteoCitta> cittaPeriodo,String box){
		
		Vector <MeteoCitta> citta = new Vector <MeteoCitta>();
         if(box.equals(box_min)) {
        	 
        	for (MeteoCitta o : cittaPeriodo ) {
        		String nome = o.name;
        		for ( String s : cittaBoxMin) {
        			if ( s.equals(nome)) {
        				 citta.add(o);
        			}
        		}
        	}	         	
        }
	return citta;
	}
}
