package com.lsy.training.dataimport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScroller {

	public static class BadFileException extends RuntimeException {
		private static final long serialVersionUID = -5508042273626650544L;
		File file;
		BadFileException (File file) {
			this.file = file;
		}
		BadFileException(File file, Throwable e) {
			super(e);
			this.file = file;
		}
	}
	
	public static class InitException extends RuntimeException {
		public InitException(String msg) {
			super(msg);
		}
	}
	
	public static class EmptyFileException extends RuntimeException {
		public EmptyFileException() {
			super();
		}
	}
	
	BufferedReader reader = null;
	int lineCount=0;	
	File file;
	
	public void open(File file) {
		
		if (reader != null) {
			throw new InitException("already initialized");
		}

		lineCount = 0;
		this.file = file;
		
		if (file == null || !file.isFile() || !file.canRead() || !file.exists()){
			throw new BadFileException(file);
		}
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new BadFileException(file,e);
		}
	}
	
	
	public String[] next() {
		if (reader == null) {
			throw new InitException("Not initialized!");			
		}
		
		String[] result = null;
		String str;
		try {
			str = reader.readLine();
	
		} catch (IOException e) {
			throw new BadFileException(this.file);
		}
		if (str != null) {
			result = str.split(";");
		} else if (lineCount == 0) {
			throw new EmptyFileException();
		}
		++lineCount;
		return result;
	}
	
	
	public void close() {
		if (reader != null){
			try {
				reader.close();
			} catch (Exception e) {
				// ignore exception
			} finally {
				reader = null;
			}
		}
		lineCount = 0;
	}
	
}
