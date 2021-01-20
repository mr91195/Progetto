package com.progetto.myweather.utilities;

import java.util.Vector;
import com.progetto.myweather.model.Citta;
import com.progetto.myweather.model.CittaMeteo;
import com.progetto.myweather.model.CittaMeteoData;
import com.progetto.myweather.model.CittaVarianza;

	/*
	 * classe che si occupa di calcolare la Varianza
	 */

public class Varianza {
	/*
	 * @param citta: contiene le citta sulle quali calcolare la varianza
	 * @param singolaCitta: serve per gestire il downCast
	 * @return ogetto della classe CittaVarianza, contenente il valore calcolato
	 */
	public CittaVarianza calcoloVarianza(Vector<Citta> citta, boolean singolaCitta) {
		//salvo i valori della temperatura media di ogni citta su un vettore di appoggio
		Vector<Double> valori = new Vector<Double>();
		CittaMeteo castCitta = new CittaMeteo();
		CittaMeteoData castData = new CittaMeteoData ();
		
		if (singolaCitta==true) {
			for (Citta o : citta) {
				if (o instanceof CittaMeteoData)
				castData = (CittaMeteoData) o;
				valori.add(castData.getTemp_media());
			}
		}
		else {
			for (Citta o : citta) {
			if (o instanceof CittaMeteo){
			castCitta = (CittaMeteo)o;
			valori.add(castCitta.getTemp_media());}
		}
		}
		
		//con il vettore di appoggio di tipo double calcolo la varianza
		double somma=0;
		for (double valore : valori) {
			somma=somma+valore;
		}
		double media_aritmetica = somma/valori.size();
		double numeratore =0;
		for (double valore : valori) {
			 numeratore += Math.pow((valore - media_aritmetica), 2);
		}
		double varianza = numeratore / valori.size();
		return new CittaVarianza("Varianza temperatura media", varianza);
	}
	/*
	 * @param citta: le citta sulle quali calolare la varianza
	 * @param singolaCitta: serve per il metodo calcoloVarianza()
	 * @return le citta con l'aggiunta della varianza
	 */
	public Vector<Citta> aggiungiVarianza(Vector <Citta> citta, boolean singolaCitta){
		Citta varianza = calcoloVarianza(citta, singolaCitta);
		citta.add(varianza);
		return citta;
		}
}
