package com.progetto.myweather.controller;

import com.progetto.myweather.service.Actual;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Vector;
import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.filtri.Filtri;
import com.progetto.myweather.model.*;

	/*
	 * Controller dell'applicazione, sono presenti i vari Path che puo richiamare l'utente
	 */

@RestController
public class SimpleRestController {
	
	@Autowired
	private Filtri filtri;
	@Autowired
	private Actual chiamata ;
	
	
	
	/*
	 * Path : /home, mostra i Path che l'utente ha a disposizione
	 */
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	/*
	 * Path : /attuale, mostra le temperature attuali, mediante il parametro 'Box' (Box pre impostati)
	 * l'utente puo scegliere tra i vari box delle città confinanti con Ancona (Box1-Box2-Box3)
	 */

	@GetMapping("/attuale")
	public Vector <Citta> meteoAttuale(@RequestParam(name="box",defaultValue="box1")String box) throws IllegalArgumentException, CustomException{
		
		return chiamata.meteoActual(box);
	}
	
	/*
	 * Path : /attuale/rettangolo , mostra le temperature attuali
	 *  dove l'utente puo scegliere manualmente il box da selezionare, mediante i parametri della longitudine-sx,
	 *  latitutine-inferiore, longitudine-destra e latitudine-superiore.
	 */
	
	@GetMapping (value = "/attuale/rettangolo")
	public Vector<Citta> meteoAttualeBox(@RequestParam(name="lon-left",defaultValue ="12.984160791301779") double lon_left,
												@RequestParam(name="lat-bottom",defaultValue ="43.32726427106371") double lat_bottom,
												@RequestParam(name="lon-right",defaultValue ="13.9") double lon_right,
												@RequestParam(name="lat-top",defaultValue ="43.841951278387214") double lat_top)
														throws CustomException{
		
		return chiamata.meteoActual(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	/*
	 *	Path : /archivioBox, l'utente mediante due parametri : 
	 *															-periodo : rappresenta il numero di giorni da prendere in locale
	 * 															-box : rappresenta il box contenente le citta da mostrare
	 * restituisce i dati degli archivi, aggiungendo la varianza della temperatura media
	 */
	
	
	@GetMapping (value = "/archivioBox")
	public Vector<Citta> filtroBoxPeriodo(@RequestParam(name="periodo", defaultValue="1")int periodo,
											@RequestParam(name="box",defaultValue="box1")String box) throws ParseException{
		return filtri.filtraggioBoxPeriodo(box, periodo);
	}
	
	/*
	 * Path : /filtraCitta, l'utente passando un body della classe CittaFiltro,
	 * richiede il numero di giorni degli storici di una singola citta
	 */
	
	@PostMapping (value = "/filtraCitta")
	public Vector<Citta> filtraCitta (@RequestBody CittaFiltro citta) throws IOException {
		return filtri.filtraCitta(citta.getGiorni(),citta.getName());
	}
}
