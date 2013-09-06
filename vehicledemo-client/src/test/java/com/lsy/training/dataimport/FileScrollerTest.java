package com.lsy.training.dataimport;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.dataimport.FileScroller.BadFileException;
import com.lsy.training.dataimport.FileScroller.EmptyFileException;
import com.lsy.training.dataimport.FileScroller.InitException;
import com.lsy.training.dto.VendorDto;

public class FileScrollerTest {

	FileScroller candidate;
	File f1;
	File f2;
	
	@Before
	public void setUp() throws Exception {
		candidate = new FileScroller();
		f1 = File.createTempFile("test1", "csv");
		f1.deleteOnExit();
		
		f2 = File.createTempFile("test2", "csv");
		f2.deleteOnExit();

		PrintWriter p1 = new PrintWriter(f2);
		p1.println("BMW");
		p1.flush();
		p1.close();
	}

	@Test
	public void testOpen() {
		assertNull(candidate.reader);
		candidate.open(f1);
		assertNotNull(candidate.reader);		
	}

	@Test
	public void testNext() {
		candidate.open(f2);
		String[] str = candidate.next();
		assertNotNull(str);
		assertEquals(1, str.length);
		assertEquals(str[0], "BMW");		
		
		str = candidate.next();		
		assertNull(str);
	}

	@Test
	public void testClose() {
		assertNull(candidate.reader);
		candidate.open(f1);
		assertNotNull(candidate.reader);
		candidate.close();
		assertNull(candidate.reader);
	}
	
	@Test(expected=EmptyFileException.class)
	public void testBadFile() {
		candidate.open(f1);
		candidate.next();
	}

	@Test(expected=BadFileException.class)
	public void testNullFile() {
		candidate.open(null);
	}
	
	@Test(expected=InitException.class)
	public void testOpen2Files() {
		candidate.open(f1);
		candidate.open(f2);
	}

	@Test
	public void testDoubleClose() {
		candidate.open(f1);
		assertNotNull(candidate.reader);
		candidate.close();
		assertNull(candidate.reader);
		candidate.close();
		assertNull(candidate.reader);
	}
	
	@Test(expected=InitException.class)
	public void testNextWithoutOpen() {
		candidate.next();
	}

	@Test(expected=EmptyFileException.class)
	public void testEmptyFile() {
		candidate.open(f1);
		candidate.next();
	}
}
