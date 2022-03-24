package org.zeep.library.ExceptionsAndValidators.validators;

import javax.validation.*;
import java.util.List;
import java.util.stream.*;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if(value == null ) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}
