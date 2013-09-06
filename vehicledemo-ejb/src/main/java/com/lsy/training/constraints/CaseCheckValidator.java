package com.lsy.training.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementierung des CaseChecks. Namenskonvention für Implementierungen
 * {AnnotationsName}CheckValidator
 */
public class CaseCheckValidator implements ConstraintValidator<Case, String>{

    boolean isUpper;    // Custom-Attribut, welches durch Annotation gesetzt wird

    @Override
    public void initialize(Case caze) { // init-Routine bekommt die Annotation mitgegeben. Hier
        // können die Constraint-Spezika ausgelesen werden
        // um dann bei der Validierung herangezogen werden zu
        // können
        isUpper = (caze.value().equals(Case.CaseType.UPPERCASE));
    }


    /**
     * Validierungsroutine
     *
     * @param refStr - String, welcher validiert werden soll. typisiert durch das Generic
     * @param ctx - Context, aus welchem der ConstraintValidator gerufen wird
     * @return boolean, zeigt an, ob das Objekt valide ist
     */
    @Override
    public boolean isValid(String refStr, ConstraintValidatorContext ctx) {
        return refStr != null ? ( isUpper
                ? refStr.toUpperCase().equals(refStr)
                : refStr.toLowerCase().equals(refStr)) : true;
    }
}
