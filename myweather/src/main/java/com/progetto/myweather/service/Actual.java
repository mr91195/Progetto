package com.progetto.myweather.service;

import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.model.*;
import com.progetto.myweather.utilities.Utilities;

import java.util.Vector;
import org.springframework.stereotype.Component;


@Component
public class Actual implements RequestApi{
	
	CallApi ca= new CallApi();
	Utilities utilities = new Utilities();
	
	/*
	 * metodo che si occupa di popolare un vettore di tipo String per stamparle all'utente
	 * per una piccola spiegazione dei path
	 * @return stringhe contenti le istruzioni
	 */
	public Vector<String> istruction(){
		return utilities.getHome();
	}
	
	/*
	 * metodo che restituisce le citta presenti nel box scelto dall'utente
	 * sulla scelta dei tre box disponibili (box1, box2, box3)
	 * @see com.progetto.myweather.service.CallApi
	 * @param box
	 * @throws CustomException
	 * @return meteo attuale delle citta corrispondenti al box selezionato
	 */
	public Vector<Citta> meteoActual(String box)throws CustomException{
		 return ca.ApiCall(box);
	}
	
	
	
	
	/*
	 * metodo che restituisce le citta presenti nel box scelto dall'utente
	 * @override
	 * @param lon-lef
	 * @param lat-bottom
	 * @param lon-right
	 * @param lat-top
	 * @return meteo attuale presenti nel box scelto
	 * 
	 */
	
	public Vector<Citta> meteoActual(double lon_left, double lat_bottom, double lon_right, double lat_top)throws CustomException{
		if (lon_left>lon_right||lat_bottom>lat_top) throw (new CustomException("Parametri del box non validi"));
		return ca.ApiCallRectangle(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	
}
