package com.lsy.training.constraints;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.model.MaintenanceActivity;
import com.lsy.training.model.Vendor;
import com.lsy.training.model.MaintenanceActivity.MaintenanceType;

/**
 * Test der Validation, also der Validierung durch die JSR 303 Implementierung
 */
public class DefectRequiresCommentCheckValidationTest {

	MaintenanceActivity probe1;
	MaintenanceActivity probe2;
	MaintenanceActivity probe3;
	
	ValidatorFactory factory;
	Validator validator;
	
	DefectRequiresCommentCheckValidator candidate = new DefectRequiresCommentCheckValidator();

	@Before
	public void setUp() throws Exception {
		probe1 = new MaintenanceActivity();
		probe1.setComment("FOO");
		probe1.setMaintenanceType(MaintenanceType.DEFECT);
		probe1.setMaintenanceDate(new Date());
		probe2 = new MaintenanceActivity();
		probe2.setMaintenanceType(MaintenanceType.DEFECT);
		probe2.setMaintenanceDate(new Date());
		probe3 = new MaintenanceActivity();
		probe3.setMaintenanceType(MaintenanceType.PLANNED_MAINTENANCE);
		probe3.setMaintenanceDate(new Date());
		
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
	}

	@Test
	public void testGood() {
		Set<ConstraintViolation<MaintenanceActivity>> constraints = validator.validate(probe1);
		assertNotNull(constraints);
		assertEquals(0, constraints.size());
		
	}
	@Test
	public void testBad() {
		Set<ConstraintViolation<MaintenanceActivity>> constraints = validator.validate(probe2);
		assertNotNull(constraints);
		assertEquals(1, constraints.size());
		
		
	}
	
	@Test
	public void testOther() {
		Set<ConstraintViolation<MaintenanceActivity>> constraints = validator.validate(probe3);
		assertNotNull(constraints);
		assertEquals(0, constraints.size());
	}
	

}
