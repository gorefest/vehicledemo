package com.lsy.training.dataimport;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.dto.VendorDto;

public class VendorRowMapperTest {

	VendorRowMapper candidate;
	
	@Before
	public void setUp() throws Exception {
		candidate = new VendorRowMapper();
	}

	@Test
	public void testMapRow() {
		VendorDto probe = candidate.mapRow(new String[]{"BMW"});
		assertNotNull(probe);
		assertEquals("BMW", probe.getName());
		assertEquals("BMW", probe.getIdentifier());
	}

	@Test(expected=AbstractRowMapper.BadRowException.class)
	public void testBadRowSize() {
		candidate.mapRow(new String[]{"BMW","VW"});
	}

	@Test(expected=AbstractRowMapper.BadRowException.class)
	public void testNullRow() {
		candidate.mapRow(null);
	}

	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testNullColumn() {
		candidate.mapRow(new String[1]);	
	}
	
	@Test(expected=AbstractRowMapper.BadColumnException.class)
	public void testEmptyString() {
		candidate.mapRow(new String[]{""});
	}

	
	
}
