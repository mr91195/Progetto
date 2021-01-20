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

	/*@Author Marco Romanelli
	 *@Author Cristiano Vagnoni
	 * 
	 * 
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
	public Vector <String> home() {
		
		return chiamata.istruction();
	}
	
	/*
	 * Path : /attuale, mostra le temperature attuali, mediante il parametro 'Box' (Box pre impostati)
	 * l'utente puo scegliere tra i vari box delle città confinanti con Ancona (Box1-Box2-Box3)
	 * 
	 * @param box 
	 * @return meteo attuale presenti nel box scelto
	 */

	@GetMapping("/attuale")
	public Vector <Citta> meteoAttuale(@RequestParam(name="box",defaultValue="box1")String box) throws IllegalArgumentException, CustomException{
		
		return chiamata.meteoActual(box);
	}
	
	/*
	 * Path : /attuale/rettangolo , mostra le temperature attuali
	 *  dove l'utente puo scegliere manualmente il box da selezionare, mediante i parametri della longitudine-sx,
	 *  latitutine-inferiore, longitudine-destra e latitudine-superiore.
	 *  @param lon-lef
	 *  @param lat-bottom
	 *  @param lon-right
	 *  @param lat-top
	 *  @return meteo attuale presenti nel box scelto
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
	 *	Path : /archivioBox, l'utente riceve le temperature con le statistiche sulla citta con temperatura minima,
	 *	con temperatura max e la varianza sulla temperatura media delle varie citta
	 *
	 *@param periodo : indica il numero di giorno a ritroso da considerare
	 *@param box : tra i vari box che ha l'utente a disposizione puo scegliere il box di citta
	 *@throws CustomException
	 *@return storici con statistiche.
	 */
	
	
	@GetMapping (value = "/archivioBox")
	public Vector<Citta> filtroBoxPeriodo(@RequestParam(name="periodo", defaultValue="1")int periodo,
											@RequestParam(name="box",defaultValue="box1")String box) throws ParseException, CustomException{
		return filtri.filtraggioBoxPeriodo(box, periodo);
	}
	
	/*
	 * Path : /filtraCittaBox, l'utente passando un body della classe CittaFiltro,
	 * e il box , riceve le citta appartenti al box scelto relative per i giorni richiesti (nel body)
	 * mostrando in piu gli storici della singola citta scelta e con le relative statistiche.
	 * 
	 * @param box : identifica il box con le relative citta
	 * @param citta : body della classe CittaFiltro ( contiene "name" della citta e "periodo" i giorni da valutare)
	 * @return le temperature delle citta per i giorni richiesti in piu filtra la singola citta scelta.
	 */
	@PostMapping (value = "/filtraCitta")
	public Vector<Citta> filtraCittaBox (@RequestParam (name="box", defaultValue="box1") String box,
								@RequestBody CittaFiltro citta) throws ParseException, IOException, CustomException {
		Vector<Citta> cityBox = filtri.filtraggioBoxPeriodo(box, citta.getGiorni());
		cityBox.addAll(filtri.filtraCitta(citta.getGiorni(),citta.getName(), cityBox));
		return cityBox;
	}
}
