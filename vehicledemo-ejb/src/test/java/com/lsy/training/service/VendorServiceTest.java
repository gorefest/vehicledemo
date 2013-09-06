package com.lsy.training.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.lsy.training.dao.VehicleDao;
import com.lsy.training.model.ApplicationLog;
import com.lsy.training.model.Engine;
import com.lsy.training.model.Vendor;
import com.lsy.training.model.Vehicle;

/**
 * Mockito-Test. Hier soll ein Service per Mockito in JUnit getestet werden
 */
@RunWith(MockitoJUnitRunner.class)
public class VendorServiceTest {


    @Mock
    ApplicationLogService applicationLogService;
	
	@Mock
	VehicleDao vehicleDao;
	
	@InjectMocks
    VendorService candidate;
	
	Collection<Vehicle> data;
	
	boolean logServiceGuard; // Guard. Analogie : Klemmbrett des Prüfers. Hier wird der Aufruf auf eine
                            //  void-Servicefunktion getestet.
	Answer<Void> logServiceAnswer = new Answer<Void>() {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			logServiceGuard = true;
			return null;
		}
	};
	
	
	@Before
	public void setUp() throws Exception {
		logServiceGuard = false;
		
		Vendor m1 = new Vendor("M1");
		Vendor m2 = new Vendor("M2");;
		Vendor m3 = new Vendor("M3");;
		
		Engine e1= new Engine();
		e1.setKw(100d);
		Engine e2= new Engine();
		e2.setKw(120d);
		Engine e3= new Engine();
		e3.setKw(100d);
			
		Vehicle v1 = new Vehicle();
		v1.setEngine(e1);
		v1.setVendor(m1);
		Vehicle v2 = new Vehicle();
		v2.setEngine(e2);
		v2.setVendor(m2);
		Vehicle v3 = new Vehicle();
		v3.setEngine(e3);
		v3.setVendor(m3);
		
		data = Arrays.asList(v1,v2,v3);
		
		when(vehicleDao.loadAllCompletley()).thenReturn(data);
		doAnswer(logServiceAnswer).when(applicationLogService).saveNewLogEntry(any(ApplicationLog.class));
		when(vehicleDao.getByFgnr(anyString())).thenCallRealMethod();
	}

	@Test
	public void testGetBigBlockManufacturer() {
		Vendor probe = candidate.getBigBlockVendor();
		assertNotNull(probe);
		assertEquals("M2", probe.getName());
		assertTrue(logServiceGuard);
	}

}
