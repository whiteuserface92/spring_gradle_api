package com.dlsdlworld.spring.api.validator;

import java.math.BigDecimal;

/**

 */
public class MinValidator {

    private BigDecimal minValue;

    public MinValidator(String minValue) {
        try {
            this.minValue = new BigDecimal(minValue);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(minValue + " does not represent a valid number format");
        }
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }
        try {
            return new BigDecimal( value ).compareTo( minValue ) != -1;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
