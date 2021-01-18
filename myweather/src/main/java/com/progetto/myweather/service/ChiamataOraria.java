package com.progetto.myweather.service;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.Vector;
//import com.progetto.myweather.utilities.UtilitiesHttp;
//
//public class ChiamataOraria extends CreaStorico{
//	
//	UtilitiesHttp utilitiesHttp = new UtilitiesHttp();
//	
//	
//	public void eseguiChiamata () {
//		TimerTask timerTask = new TimerTask(){
//		@Override
//	public void run ( ) {
//			chiama("box1");
//			System.out.println("Task");}
//		};
//	
//	Timer timer = new Timer ("Chiamata oraria");
//	//((long) Math.pow(3.6, 6))
//	timer.scheduleAtFixedRate(timerTask, 30,999999999);
//	}
//		
//	
//	protected void chiama (String box) {
//		String nome_file ="provaSalvataggio" ;
//		String FileBox ="C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\FileBox.txt";
//		Vector<String> boxStringhe = utilitiesHttp.elaboraStringheBox(FileBox);
//	    String box_selezionato = utilitiesHttp.selezionaBox(boxStringhe,box);
//		String site1 = "https://api.openweathermap.org/data/2.5/box/city?bbox=";
//		String site2 = "&appid=";
//		
//		
//		try { 
//			URLConnection openConnection = new URL(site1+box_selezionato+site2+utilitiesHttp.getKey()).openConnection();
//			InputStream input = openConnection.getInputStream();
//			BufferedReader buf = new BufferedReader(new InputStreamReader (input));
//			PrintWriter file_output = new PrintWriter (new BufferedWriter (new FileWriter(nome_file)));
//			file_output.println(buf.readLine());
//			file_output.close();
//			buf.close();
//		}
//		catch (IOException e)
//		{e.printStackTrace();}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	
//	
//	}
//
