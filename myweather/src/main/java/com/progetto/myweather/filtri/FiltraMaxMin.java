package com.progetto.myweather.filtri;

import java.util.Vector;
import com.progetto.myweather.model.*;

public class FiltraMaxMin {
	
	/*
	 * metodo che si occupa di fare le statistiche sulle temperature e di restituirle inserite nel vettore
	 * 
	 * @param citta : vettore sul quale fare le statitstiche
	 * @param singolaCitta : se lavora con una singola citta (serve per il downcast)
	 * @return il vettore popolato anche con l'oggetto CittaTempMin e CittaTempMax
	 */

	public Vector<Citta> aggiungiMaxMin(Vector <Citta> citta, boolean singolaCitta) {
		CittaTempMax CTmax = evidenziaMax(citta, singolaCitta);
		CittaTempMin CTmin=evidenziaMin(citta, singolaCitta);
	    citta.add(CTmax);
	    citta.add(CTmin);
	    return citta;
	}
	
	/*
	 * metodo che si occupa di calcolare la citta con la temperatura max
	 * 
	 * @param citta: vettore sul quale lavora
	 * @param singolaCitta : se lavora con una singola citta  o piu
	 * @return l'oggetto CittaTempMax
	 */
	public CittaTempMax evidenziaMax ( Vector <Citta> citta, boolean singolaCitta) {
		CittaTempMax Tmax = null;
		Vector<CittaMeteoData> castData = new Vector <CittaMeteoData>();
		Vector<CittaMeteo> castCitta = new Vector<CittaMeteo> ();
		if (singolaCitta == true) {
			for (Citta o : citta) {
				if (o instanceof CittaMeteoData) {
					castData.add((CittaMeteoData)o);
				}
				
			}
			double temp_max= castData.get(0).getTemp_max();
			String name =castData.get(0).getName();
			Tmax = new CittaTempMax(name, temp_max);
			for (int i = 0 ; i< castData.size(); i++) {
				if(castData.get(i).getTemp_max() > temp_max) {
					temp_max = castData.get(i).getTemp_max();
					name = castData.get(i).getName();
					Tmax = new CittaTempMax (name, temp_max);
				}
			}
		}
		else if (singolaCitta == false) {
				for (Citta o : citta) {
			    	if (o instanceof CittaMeteo) {
			    		castCitta.add((CittaMeteo) o);
			    	}
			    }
				double temp_max= castCitta.get(0).getTemp_max();
				String name =castCitta.get(0).getName();
				Tmax = new CittaTempMax(name, temp_max);
				for (int i = 0 ; i< castCitta.size(); i++) {
					if(castCitta.get(i).getTemp_max() > temp_max) {
						temp_max = castCitta.get(i).getTemp_max();
						name = castCitta.get(i).getName();
						CittaTempMax tmp = new CittaTempMax(name, temp_max);
						Tmax = tmp;
					}
				}
				
		}
	return Tmax;
}
	/*
	 * metodo analogo a evidenziaMax
	 * 
	 */
	public CittaTempMin evidenziaMin(Vector<Citta> citta, boolean singolaCitta) {
		CittaTempMin Tmin = null;
		Vector<CittaMeteoData> castData = new Vector <CittaMeteoData>();
		Vector<CittaMeteo> castCitta = new Vector<CittaMeteo> ();
		if (singolaCitta == true) {
			for (Citta o : citta) {
				if (o instanceof CittaMeteoData) {
					castData.add((CittaMeteoData)o);
				}
				
			}
			
			double temp_min= castData.get(0).getTemp_min();
			String name =castData.get(0).getName();
			Tmin = new CittaTempMin(name, temp_min);
			for (int i = 0 ; i< castData.size(); i++) {
				if(castData.get(i).getTemp_min() < temp_min) {
					temp_min = castData.get(i).getTemp_min();
					name = castData.get(i).getName();
					Tmin = new CittaTempMin (name, temp_min);
				}
			}
		}
		else if (singolaCitta == false) {
				for (Citta o : citta) {
			    	if (o instanceof CittaMeteo) {
			    		castCitta.add((CittaMeteo) o);
			    	}
				}
				double temp_min= castCitta.get(0).getTemp_min();
				String name =castCitta.get(0).getName();
				Tmin = new CittaTempMin(name, temp_min);
				for (int i = 0 ; i< castCitta.size(); i++) {
					if(castCitta.get(i).getTemp_min() < temp_min) {
						temp_min = castCitta.get(i).getTemp_min();
						name = castCitta.get(i).getName();
						Tmin = new CittaTempMin (name, temp_min);
					}
				}
		}		
		return Tmin;
	}

}
