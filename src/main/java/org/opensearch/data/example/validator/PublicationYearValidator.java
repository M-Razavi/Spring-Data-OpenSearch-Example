package org.opensearch.data.example.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.opensearch.data.example.metadata.PublicationYear;

import java.time.Year;

public class PublicationYearValidator implements ConstraintValidator<PublicationYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !Year.of(value).isAfter(Year.now());
    }
}