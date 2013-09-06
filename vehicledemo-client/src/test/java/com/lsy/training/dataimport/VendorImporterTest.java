package com.lsy.training.dataimport;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.lsy.training.dto.VendorDto;
import com.lsy.training.ws.VendorWebService;

@RunWith(MockitoJUnitRunner.class)
public class VendorImporterTest {

	@Mock
	VendorWebService vendorWebService;
	
	@Mock
	FileScroller fileScroller;
	
	@InjectMocks
	VendorImporter candidate;	
	
	boolean openGuard;
	Answer<Void> openAnswer = new Answer<Void>() {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			openGuard = true;
			return null;
		}
	};
	
	int lineCount;
	Answer<String[]> nextAnswer = new Answer<String[]>() {
		@Override
		public String[] answer(InvocationOnMock invocation) throws Throwable {
			if (lineCount == 0) {
				lineCount++;
				return new String[] {"BMW"};
			} else {
				return null;
			}
		}
	};
	
	VendorDto lastVendor;
	Answer<Long> saveAnswer = new Answer<Long>() {
		@Override
		public Long answer(InvocationOnMock invocation) throws Throwable {
			lastVendor = (VendorDto) invocation.getArguments()[0];
			return Long.valueOf(lineCount);
		}
	};
	
	@Before
	public void setUp() throws Exception {
		candidate.rowMapper = new VendorRowMapper();
		openGuard = false;
		lineCount = 0;
		
		doAnswer(openAnswer).when(fileScroller).open(any(File.class));
		doAnswer(nextAnswer).when(fileScroller).next();
		doAnswer(saveAnswer).when(vendorWebService).saveVendor(any(VendorDto.class));
		doCallRealMethod().when(fileScroller).close();
		
	}

	@Test
	public void testPerformImport() {
		candidate.performImport();
		assertTrue(openGuard);
		assertEquals(1,lineCount);
		assertNotNull(lastVendor);
		assertEquals("BMW", lastVendor.getName());
	}

}
