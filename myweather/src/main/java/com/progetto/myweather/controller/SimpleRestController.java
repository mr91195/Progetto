package com.progetto.myweather.controller;

import com.progetto.myweather.model.Filtri;
import com.progetto.myweather.model.MeteoCitta;
import com.progetto.myweather.service.Actual;
import com.progetto.myweather.service.Actual;
import com.progetto.myweather.service.RequestApi;
import com.sun.el.parser.ParseException;
import com.progetto.myweather.service.CallApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Vector;
import java.io.IOException;

	/*
	 * Controller dell'applicazione, sono presenti i vari Path che puo richiamare l'utente
	 */

@RestController
public class SimpleRestController {
	
	@Autowired
	private Filtri filtri;
	@Autowired
	private CallApi call;
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
	 * puo scegliere tra i vari box delle città confinanti con Ancona
	 */

	@GetMapping("/attuale")
	public Vector <MeteoCitta> meteoAttuale(@RequestParam(name="box",defaultValue="box1")String box) throws IllegalArgumentException {
		
		return chiamata.meteoActual(box);
	}
	
	/*
	 * Path : /attuale/ , mostra le temperature attuali dove l'utente puo scegliere manualmente il box da selezionare
	 */
	
	@GetMapping (value = "/attualeRettangolo")
	public Vector<MeteoCitta> meteoAttualeBox(@RequestParam(name="lon-left",defaultValue ="12.984160791301779") double lon_left,
												@RequestParam(name="lat-bottom",defaultValue ="43.32726427106371") double lat_bottom,
												@RequestParam(name="lon-right",defaultValue ="13.9") double lon_right,
												@RequestParam(name="lat-top",defaultValue ="43.841951278387214") double lat_top){
		
		return chiamata.meteoActualBox(lon_left, lat_bottom, lon_right, lat_top);
	}
	
	
	
	/*
	 * Path: /statitica, mostra le statistiche della temperatura , mediante il parametro 'Period'
	 * puo scegliere su che intervallo di tempo calcolare le statistiche
	 */
	
	@GetMapping("/statistica")	
	public Vector<MeteoCitta> statistiche(@RequestParam(name="box",defaultValue="box1")String box,
											@RequestParam(name="periodo",defaultValue="1")int periodo) throws IOException, ParseException, org.json.simple.parser.ParseException {
		Vector<MeteoCitta> statistiche=new Vector<MeteoCitta>();
		
		statistiche = filtri.calcoloPeriodo(periodo);
	
	return filtri.filtraggio(statistiche, box);
		}
}
