package com.palo.it.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;
public class ValidWordValidator implements ConstraintValidator<ValidWord, String> {

    @Override
    public boolean isValid(String word, ConstraintValidatorContext constraintValidatorContext) {
        return !isNull(word) && word.matches("[a-zA-Z]+");
    }
}
