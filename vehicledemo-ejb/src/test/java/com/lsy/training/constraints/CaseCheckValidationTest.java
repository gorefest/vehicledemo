package com.lsy.training.constraints;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.model.Vendor;

public class CaseCheckValidationTest {

	Vendor probe1;
	Vendor probe2;

	ValidatorFactory factory;           // Factory und Validator,notwendig, da wir
	Validator validator;                // über die Validation selbst testen
	
	@Before
	public void setUp() throws Exception {
		probe1 = new Vendor("VOLKSWAGEN");
		probe2 = new Vendor("volkswagen");
		factory = Validation.buildDefaultValidatorFactory(); // Laden der Factory aus der JSR-303 Implementierung (hier : Hibernate Validator)
		validator = factory.getValidator(); // validator erzeugen
	}

	@Test
	public void testIsValid() {
		Set<ConstraintViolation<Vendor>> constraints = validator.validate(probe1);  // Validierung des Pröbchens
		assertNotNull(constraints);
		assertEquals(0, constraints.size());
	}

	@Test
	public void testIsInValid() {
		Set<ConstraintViolation<Vendor>> constraints = validator.validate(probe2);
		assertNotNull(constraints);
		assertEquals(1, constraints.size());
		
		System.out.println(constraints.iterator().next().getMessage());
	}

	
}
