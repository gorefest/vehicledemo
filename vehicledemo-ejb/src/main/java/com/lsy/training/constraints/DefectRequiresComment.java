package com.lsy.training.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lsy.training.constraints.Case.CaseType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy=DefectRequiresCommentCheckValidator.class)
@Documented
public @interface DefectRequiresComment {

	String message() default "{com.lsy.constraint.defectTypeViolation}";  // Platzhalter f. Text in ValidationMessager.properties
	
	Class<? extends Payload>[] payload() default {};              // Payload, welche vom Verwender im Falle
                                                                  // einer Constraint-Verletzung mitgegebn werden kann
	
	Class<?>[] groups() default {};                               // default-Gruppen für Constraint
	
	
}
