package utils;

import models.Hero;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class HeroValidator {

    private Set<ConstraintViolation<Hero>> validateHeroConstraint(Hero hero) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(hero);
    }

    public boolean validateHeroConstraintConsole(Hero hero) {
        Set<ConstraintViolation<Hero>> violations = validateHeroConstraint(hero);
        if (violations == null || violations.isEmpty())
            return true;
        else {
            violations.forEach(violation -> System.err.println(violation.getMessage()));
            return false;
        }
    }
}
