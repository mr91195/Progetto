package com.progetto.myweather.model;

import java.util.Vector;

public class Statistiche {
	public Vector<MeteoCitta> calcolaStatistiche(Vector <MeteoCitta> citta) {
		MeteoCitta e1 = evidenziaMax(citta);
		MeteoCitta e2 = evidenziaMin(citta);
		citta.add(e1);
		citta.add(e2);
	    
		return citta;
	}
	
	
	public MeteoCitta evidenziaMax (Vector <MeteoCitta> citta) {
		
		MeteoCitta tMax=null;
		
		String name = null;
		double temp_max = citta.get(0).temp_max;
		
		for (int i = 0; i < citta.size(); i++) {
			if(citta.get(i).temp_max > temp_max) {
				temp_max = citta.get(i).temp_max;
				name = citta.get(i).name;
				tMax= new MeteoCitta("[Città con temperatura massima] :"+name, 0, 0, temp_max);
			}
		 }
	
	return tMax;
}
	
	

	public MeteoCitta evidenziaMin(Vector <MeteoCitta> citta) {
		
		MeteoCitta tMin=null;
		String name= null;
		double temp_min = citta.get(0).temp_min;
		
		for (int i = 0; i < citta.size(); i++) {
			if(citta.get(i).temp_min < temp_min) {
				temp_min = citta.get(i).temp_min;
				name = citta.get(i).name;
				tMin= new MeteoCitta("[Città con temperatura minima] :"+name, 0, temp_min, 0);
			}
		 }
		
		return tMin;
	}
		

}
