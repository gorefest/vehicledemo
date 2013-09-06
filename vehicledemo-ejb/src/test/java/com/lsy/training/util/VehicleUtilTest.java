package com.lsy.training.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lsy.training.model.Vendor;
import org.junit.Before;
import org.junit.Test;

import com.lsy.training.model.Engine;
import com.lsy.training.model.Vehicle;

public class VehicleUtilTest {

	List<Vehicle> probes;
	List<Vehicle> probes2;
	
	@Before
	public void setUp() throws Exception {
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
		
		probes = Arrays.asList(v1,v2,v3);
		probes2 = Arrays.asList(v1,v2,v2);
		
	}

	@Test
	public void testGetBigBlockManufacturer() {
		Vendor probe = VehicleUtil.getBigBlockVendor(probes);
		assertNotNull(probe);
		assertEquals("M2",probe.getName());
	}

	@Test
	public void testGetBigBlockManufacturerNegative() {
		assertNull(VehicleUtil.getBigBlockVendor(new ArrayList<Vehicle>()));
	}
	
	@Test	
	public void testGetBigBlockManufacturerNegative2() {
		assertNull(VehicleUtil.getBigBlockVendor(null));
	}


	@Test(expected=Exception.class)
	public void testGetBigBlockManufacturerExtreme() {
		VehicleUtil.getBigBlockVendor(probes2);
	}
	
	
}
