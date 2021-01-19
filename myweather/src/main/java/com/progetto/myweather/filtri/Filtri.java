package com.progetto.myweather.filtri;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import com.progetto.myweather.model.Citta;
import com.progetto.myweather.utilities.Utilities;
import com.progetto.myweather.utilities.Varianza;

@Service
public class Filtri {
	Varianza varianza = new Varianza();
	Utilities utilities = new Utilities ();
	FiltraMaxMin filtri = new FiltraMaxMin ();
	FiltraCitta filtraCitta = new FiltraCitta();
	
	/*
	 * metodo che in base al box selezionato e al periodo
	 * restituisce un Vector popolato di oggetti corrispondenti alle città scelte
	 * e al numero di giorni (periodo) corrispondenti al numero di file storici partendo a ritroso.
	 * Aggiungendo il calcolo della varianza e gli oggetti CittaTempMin e CittaTempMax
	 * 
	 * viene usato per il path '/archivioBox'
	 */
	public Vector<Citta> filtraggioBoxPeriodo (String box, int periodo) throws ParseException{
		
		Vector<Citta> citta = null;
		try {
			//popola il vettore con le citta corrispondenti al periodo e al box scelto
			citta = utilities.filtraggio(periodo, box);
			citta = varianza.aggiungiVarianza(citta, false);
			citta = filtri.aggiungiMaxMin(citta,false);
			
		} catch (IOException  e) {
			System.out.println("Errore try_filtraggioBoxPeriodo_Filtri");
			e.printStackTrace();
		}
		return citta;
	}
	
	/*
	 * Metodo simile, che si occupa di restituire all'utente una singola citta per il numero di giorni
	 * a ritroso da prelevare nello storico
	 * 
	 * viene usato per il path '/filtraCitta'
	 */
	public Vector<Citta> filtraCitta (int periodo, String nomeCitta) throws IOException{
		return filtraCitta.evidenziaStoricoCitta(periodo, nomeCitta);
	}
	
	
}
