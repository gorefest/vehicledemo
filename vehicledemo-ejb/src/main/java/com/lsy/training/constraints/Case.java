package com.lsy.training.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy=CaseCheckValidator.class)
@Documented
public @interface Case {

	enum CaseType { UPPERCASE, LOWERCASE };
	
	CaseType value();
	

	String message() default "{com.lsy.constraint.caseViolation}";  // Platzhalter f. Text in ValidationMessager.properties
	
	Class<? extends Payload>[] payload() default {};              // Payload, welche vom Verwender im Falle
                                                                  // einer Constraint-Verletzung mitgegebn werden kann
	
	Class<?>[] groups() default {};                               // default-Gruppen für Constraint
	
	
}
