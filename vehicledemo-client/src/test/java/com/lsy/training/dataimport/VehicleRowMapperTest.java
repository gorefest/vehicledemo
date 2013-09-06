package com.lsy.training.dataimport;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.dto.VehicleDto;

public class VehicleRowMapperTest {

	VehicleRowMapper candidate;
	
	@Before
	public void setUp() throws Exception {
		candidate = new VehicleRowMapper();  
	}

	@Test
	public void testPositive() {
		VehicleDto probe = candidate.mapRow(new String[]{"4711","VW Golf", "VOLKSWAGEN", "123/123/123","4","1600","Diesel","75,5","12345.50"});	
		assertNotNull(probe);
		assertEquals("4711", probe.getFgnr());
		assertEquals("VW Golf", probe.getModelName());
		assertEquals("VOLKSWAGEN", probe.getVendorName());
		assertEquals("123/123/123", probe.getEngineId());
		assertEquals((Integer) 4, probe.getCylinders());
		// ...
	}

	@Test(expected=AbstractRowMapper.BadRowException.class)
	public void testBadRowSize() {
		candidate.mapRow(new String[]{"4711","VW Golf"});
	}

	@Test(expected=AbstractRowMapper.BadRowException.class)
	public void testNullRow() {
		candidate.mapRow(null);
	}

	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testNullColumn() {
		candidate.mapRow(new String[9]);	
	}
	
	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testEmptyString() {
		candidate.mapRow(new String[]{"","","","","","","","",""});
	}
	
	@Test
	public void testDecimalSeperator1() {
		candidate.mapRow(new String[]{"4711","VW Golf", "VOLKSWAGEN", "123/123/123","4","1600","Diesel","75,5","12345.50"});	
	}

	@Test
	public void testDecimalSeperator2() {
		candidate.mapRow(new String[]{"4711","VW Golf", "VOLKSWAGEN", "123/123/123","4","1600","Diesel","75.5","12345,50"});	
	}

	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testBadInteger1() {
		candidate.mapRow(new String[]{"4711","VW Golf", "VOLKSWAGEN", "123/123/123","4a","1600","Diesel","75.5","12345,50"});	
	}

	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testBadInteger2() {
		candidate.mapRow(new String[]{"4711","VW Golf", "VOLKSWAGEN", "123/123/123","4","1600b","Diesel","75.5","12345,50"});	
	}
	
	
}
