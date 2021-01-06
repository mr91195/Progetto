package com.progetto.myweather.controller;
import com.progetto.myweather.model.MeteoCitta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

	/*
	 * Controller dell'applicazione, sono presenti i vari Path che puo richiamare l'utente
	 */

@RestController
public class SimpleRestController {
	
	/*
	 * Path : /home, mostra i Path che l'utente ha a disposizione
	 */
	
	@RequestMapping("/home")
	public String home() {
		return "";
	}
	
	/*
	 * Path : /attuale, mostra le temperature attuali, mediante il parametro 'Box'
	 * puo scegliere tra i vari box delle città confinanti con Ancona
	 */

	@GetMapping("/attuale/{Box}")
	public Vector<MeteoCitta> meteoAttuale() {
		
		return;
	}
	
	/*
	 * Path: /statitica, mostra le statistiche della temperatura , mediante il parametro 'Period'
	 * puo scegliere su che intervallo di tempo calcolare le statistiche
	 */
	
	@GetMapping("/statistica/{Box}/{Periodo}")
	public String statistiche() {
		return "";
	}
}
