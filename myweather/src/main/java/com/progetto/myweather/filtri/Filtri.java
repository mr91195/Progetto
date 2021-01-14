package com.progetto.myweather.filtri;

import java.io.IOException;
import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import com.progetto.myweather.model.Citta;
import com.progetto.myweather.utilities.Utilities;

@Service
public class Filtri {
	
	Utilities utilities = new Utilities ();
	
	public Vector<Citta> filtraggioBoxPeriodo (String box, int periodo){
		
		Vector<Citta> citta = null;
		try {
			citta = utilities.filtraggio(periodo, box);
		} catch (IOException | ParseException e) {
			System.out.println("Errore try_filtraggioBoxPeriodo_Filtri");
			e.printStackTrace();
		}
		return citta;
	}
	
	
	
			
		

	
	
	
	
	
	
}
