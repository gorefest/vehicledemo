package com.lsy.training.constraints;

import static org.junit.Assert.*;

import java.util.Date;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.lsy.training.model.MaintenanceActivity;
import com.lsy.training.model.MaintenanceActivity.MaintenanceType;

/**
 * Test des Validators, also der Implementierung des Checks
 */
public class DefectRequiresCommentCheckValidatorTest {

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
	public void test() {
		assertTrue(candidate.isValid(probe1, null));
		assertFalse(candidate.isValid(probe2, null));
		assertTrue(candidate.isValid(probe3, null));
	}
	
	

}
