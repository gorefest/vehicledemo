package com.lsy.training.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lsy.training.model.MaintenanceActivity;
import com.lsy.training.model.MaintenanceActivity.MaintenanceType;

/**
 * Implementierung eines Klassenconstraints
 */
public class DefectRequiresCommentCheckValidator implements ConstraintValidator<DefectRequiresComment, MaintenanceActivity>{

	
	@Override
	public void initialize(DefectRequiresComment constraintAnnotation) {   // keine Parameter

	}

	@Override
	public boolean isValid(MaintenanceActivity value, ConstraintValidatorContext context) {
		return (value.getMaintenanceType().equals(MaintenanceType.DEFECT) 
				&& value.getComment() != null && !value.getComment().trim().isEmpty())
			|| !value.getMaintenanceType().equals(MaintenanceType.DEFECT); 
	}

}
