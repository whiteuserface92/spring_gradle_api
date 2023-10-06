package com.dlsdlworld.spring.api.validator;

import java.math.BigDecimal;

/**

 */
public class DecimalMaxValidator {

    private final BigDecimal maxValue;
    private final boolean inclusive;

    public DecimalMaxValidator(Number maxValue, boolean inclusive) {
        this(maxValue.toString(), inclusive);
    }

    public DecimalMaxValidator(String maxValue, boolean inclusive) {
        try {
            this.maxValue = new BigDecimal( maxValue );
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(maxValue + " does not represent a valid number format");
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
            int comparisonResult = new BigDecimal( value.toString() ).compareTo( maxValue );
            return inclusive ? comparisonResult <= 0 : comparisonResult < 0;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
