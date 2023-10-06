package com.dlsdlworld.spring.api.validator;

import java.math.BigDecimal;

/**

 */
public class DecimalMinValidator {

    private final BigDecimal minValue;
    private final boolean inclusive;

    public DecimalMinValidator(Number minValue, boolean inclusive) {
        this(minValue.toString(), inclusive);
    }

    public DecimalMinValidator(String minValue, boolean inclusive) {
        try {
            this.minValue = new BigDecimal( minValue );
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(minValue + " does not represent a valid number format");
        }
        this.inclusive = inclusive;
    }

    public boolean isValid(Number value) {
        return isValid(value.toString());
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }
        try {
            int comparisonResult = new BigDecimal( value.toString() ).compareTo( minValue );
            return inclusive ? comparisonResult >= 0 : comparisonResult > 0;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
