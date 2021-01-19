package com.progetto.myweather;

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
	void vectorTest() throws CustomException {
		assertNotNull(callApi.ApiCall("box1")) ;
	}
}
