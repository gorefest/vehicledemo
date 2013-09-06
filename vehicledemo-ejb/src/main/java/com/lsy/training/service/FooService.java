package com.lsy.training.service;

import javax.ejb.Stateless;

@Stateless
public class FooService {

	public String foobar(String input) {
		return input != null ? input.toUpperCase() : null;
	}
	
}
