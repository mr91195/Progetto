package com.progetto.myweather.filtri;

import java.util.Vector;
import com.progetto.myweather.model.*;

public class FiltraMaxMin {

	public Vector<Citta> aggiungiMaxMin(Vector <Citta> citta) {
		
		CittaTempMax CTmax = null;
		CittaTempMin CTmin=null;
	    Vector<CittaMeteo> castCitta = new Vector<CittaMeteo> ();
	    for (Citta o : citta) {
	    	castCitta.add((CittaMeteo) o);
	    }
	    CTmax = evidenziaMax(castCitta);
	    CTmin = evidenziaMin(castCitta);
	    citta.add(CTmax);
	    citta.add(CTmin);
	    return citta;
	}
	
	public CittaTempMax evidenziaMax ( Vector <CittaMeteo> citta) {
		CittaTempMax Tmax = null;
		double temp_max= citta.get(0).temp_max;
		String name =citta.get(0).getName();
		Tmax = new CittaTempMax(name, temp_max);
		for (int i = 0 ; i< citta.size(); i++) {
			if(citta.get(i).temp_max > temp_max) {
				temp_max = citta.get(i).temp_max;
				name = citta.get(i).getName();
				Tmax = new CittaTempMax (name, temp_max);
			}
		}
		
		return Tmax;
	}
	
	public CittaTempMin evidenziaMin(Vector<CittaMeteo> citta) {
		CittaTempMin Tmin = null;
		double temp_min = citta.get(0).temp_min;
		String name= citta.get(0).getName();
		Tmin = new CittaTempMin (name, temp_min);
		for (int i = 0 ; i<citta.size(); i++) {
			if (citta.get(i).temp_min<temp_min) {
				temp_min=citta.get(i).temp_min;
				name = citta.get(i).getName();
				Tmin = new CittaTempMin(name, temp_min);
			}
		}
		return Tmin;
	}

}
