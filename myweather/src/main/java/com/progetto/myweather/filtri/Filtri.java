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
	
	public Vector<Citta> filtraggioBoxPeriodo (String box, int periodo) throws ParseException{
		
		Vector<Citta> citta = null;
		try {
			citta = utilities.filtraggio(periodo, box);
			citta = varianza.aggiungiVarianza(citta, false);
			citta = filtri.aggiungiMaxMin(citta,false);
			
		} catch (IOException  e) {
			System.out.println("Errore try_filtraggioBoxPeriodo_Filtri");
			e.printStackTrace();
		}
		return citta;
	}
	
	
	
			
		

	
	
	
	
	
	
}
