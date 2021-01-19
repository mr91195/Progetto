package com.progetto.myweather;

import static org.junit.Assert.assertThrows;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.progetto.myweather.exception.CustomException;
import com.progetto.myweather.service.CallApi;
import com.progetto.myweather.utilities.UtilitiesHttp;
import junit.framework.TestCase;

@SpringBootTest
class MyweatherApplicationTests extends TestCase {
	UtilitiesHttp utilitiesHttp = new UtilitiesHttp();
	CallApi callApi = new CallApi();
	
	@BeforeEach
	public void setUp() {
	}
	@AfterEach
	public void tearDown() {
		
	}
	@Test
	void getKayTest() {
		assertEquals(utilitiesHttp.getKey(),"e1d1909fbc1e5ac544a707c9140efee5");
	}
	
	@Test
	public void exceptionTest() {
	    CustomException exception = assertThrows(CustomException.class, () -> {
	    	callApi.ApiCall("box5");
	    });

	    String expectedMessage = "Box selezionato errato";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void vectorTest() {
		Vector<String> expected = new Vector<String>();
		expected.add("12.984160791301779,43.32726427106371,13.9,43.841951278387214,20");
		expected.add("12.784160791301779,43.21726427106371,14.1,43.951951278387214,20");
		expected.add("12.584160791301779,43.12726427106371,14,44.141951278387214,20");
		Vector<String> actual = utilitiesHttp.elaboraStringheBox("C:\\Users\\MR911\\git\\ProgettoPO\\myweather\\src\\main\\resources\\Archivio\\cittaBox\\FileBox.txt");
		assertEquals(actual,expected);
	}
}
