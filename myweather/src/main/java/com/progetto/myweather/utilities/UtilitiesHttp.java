package com.progetto.myweather.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class UtilitiesHttp {
	/*
	 * restituisce la key per l'API CurrentWeather
	 */
	public String getKey () {
		File file_key = new File ("C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\api-key.txt");
		String key="";
		try {
			BufferedReader buf = new BufferedReader(new FileReader (file_key));
			key = buf.readLine();
			buf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Problema nel try getKey_FileNotFound");
		} catch (IOException e) {System.out.println("Problema nel try getKey_IOException");}
		
		
	return 	key;
	}
	/*
	 * metodo che restituisce un vettore contenente le coordinate relative al box1,box2,box3
	 * in String FileBox viene passato il percorso del file 'FileBox'
	 */
	public Vector <String> elaboraStringheBox(String FileBox) {
		
		Vector <String> arrBox = new Vector <String>();
		BufferedReader fileReader;
		String elem;
		
		try {
			fileReader = new BufferedReader(new FileReader(FileBox));
			
			while((elem=fileReader.readLine())!=null) {
				arrBox.add(elem);
			}
			fileReader.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("Problema nel try elaboraStringhe_FileNotFound");
			}
			  catch (IOException e) {
				System.out.println("Problema nel try elaboraStringhe_IOException");
			}
		
		return arrBox;
		
	}
	/*
	 *  metodo che confronta il box selezionato dall'utente e restituisce le
	 *  coordinate del box corrette, da inserire nell'http
	 */
	public String selezionaBox(Vector<String> boxStringhe, String box) {
		String box_selezionato="";
		switch(box) {
		case "box1":	box_selezionato = boxStringhe.get(0); break;
		case "box2":	box_selezionato = boxStringhe.get(1); break;
		case "box3":	box_selezionato = boxStringhe.get(2); break;
		}
	return box_selezionato;			
	}
}