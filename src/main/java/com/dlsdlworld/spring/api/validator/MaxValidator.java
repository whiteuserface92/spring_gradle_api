package com.dlsdlworld.spring.api.validator;

import java.math.BigDecimal;

/**
 */
public class MaxValidator {

    private final BigDecimal maxValue;

    public MaxValidator(String maxValue) {
        try {
            this.maxValue = new BigDecimal(maxValue);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(maxValue + " does not represent a valid number format");
        }
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }
        try {
            return new BigDecimal( value ).compareTo( maxValue ) != 1;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
