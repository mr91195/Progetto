package com.progetto.myweather.filtri;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import com.progetto.myweather.model.Citta;
import com.progetto.myweather.model.CittaMeteoData;
import com.progetto.myweather.utilities.Utilities;
import com.progetto.myweather.utilities.Varianza;

@Service
public class FiltraCitta {

	Utilities utilities = new Utilities();
	FiltraMaxMin filtraMaxMin = new FiltraMaxMin();
	Varianza varianza = new Varianza();
	
	/*
	 * 	Metodo che si occupa di restituire un Vector popolato dalla singola citta richiesta dall'utente
	 * per il numero di giorni di storico richiesti.
	 * aggiungendo la tempMax , tempMin e la varianza
	 * 
	 * @param periodo : il numero di giorni a ritroso
	 * @param nomeCitta :  citta che restituisce
	 * @param cityBox : vettore sul quale cerca la citta richiesta
	 * @return lo storico di una singola citta 
	 */
	public Vector<Citta> evidenziaStoricoCitta( int periodo, String nomeCitta, Vector<Citta> cityBox) throws IOException{
		
		Vector<Citta> citta = new Vector<Citta>();
		Vector<Citta> cittaFiltrate = new Vector<Citta>();
		Vector<CittaMeteoData> custCitta = new Vector<CittaMeteoData>();
		CittaMeteoData m=null;
		try {
			cittaFiltrate = utilities.calcoloPeriodo(periodo, true);
			for (Citta s : cittaFiltrate) {
				m = (CittaMeteoData) s ;
				custCitta.add(m);
			}
			for (CittaMeteoData s : custCitta) {
				if (nomeCitta.equals(s.getName())) {
					citta.add(new CittaMeteoData(s));
				}
			}
			//aggiunge un oggetto CittaTempMax ed un oggetto CittaTempMin
			cityBox= filtraMaxMin.aggiungiMaxMin(citta, true);
			//aggiunge un oggetto CittaVarianza 
			cityBox = varianza.aggiungiVarianza(citta, true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cityBox;
	}
}

