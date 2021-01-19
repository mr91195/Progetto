package com.progetto.myweather.service;

import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.filtri.Filtri;
import com.progetto.myweather.model.*;

import java.util.Vector;
import org.springframework.stereotype.Component;


@Component
public class Actual implements RequestApi{
	
	CallApi ca= new CallApi();
	
	/*
	 * metodo che restituisce le citta presenti nel box scelto dall'utente
	 * sulla scelta dei tre box disponibili
	 * richiama ApiCall di CallApi
	 */
	public Vector<Citta> meteoActual(String box)throws CustomException{
		 return ca.ApiCall(box);
	}
	
	
	@Override
	
	/*
	 * metodo che restituisce le citta presenti nel box scelto dall'utente
	 * l'utente passa manualmente i quattro parametri del box
	 * richiama ApiCallRectangle di CallApi
	 */
	
	public Vector<Citta> meteoActual(double lon_left, double lat_bottom, double lon_right, double lat_top)throws CustomException{
		if (lon_left>lon_right||lat_bottom>lat_top) throw (new CustomException("Parametri del box non validi"));
		return ca.ApiCallRectangle(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	
}
