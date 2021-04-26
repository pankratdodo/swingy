package utils;

import models.hero.Hero;

import javax.swing.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.*;
import java.util.Set;

public class HeroValidator {

    private Set<ConstraintViolation<Hero>> validateHeroConstraint(Hero hero) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(hero);
    }

    public boolean validateHeroConstraintConsole(Hero hero, String view) {
        Set<ConstraintViolation<Hero>> violations = validateHeroConstraint(hero);
        if (violations == null || violations.isEmpty())
            return true;
        else {
            if (view.equals("console"))
                violations.forEach(violation -> System.err.println(violation.getMessage()));
            else {
                violations.forEach(violation -> {
                    JOptionPane.showMessageDialog(new Frame(), violation.getMessage());
                    return;
                });
            }
            return false;
        }
    }
}
