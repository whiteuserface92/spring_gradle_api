package com.dlsdlworld.spring.api.validator;

import java.math.BigDecimal;

/**

 */
public class DigitsValidator {

    private final int maxIntegerLength;

    private final int maxFractionLength;

    public DigitsValidator(int maxIntegerLength, int maxFractionLength) {
        this.maxIntegerLength = maxIntegerLength;
        this.maxFractionLength = maxFractionLength;
        validateParameters();
    }

    public boolean isValid(Object value) {
        return isValid(value.toString());
    }

    public boolean isValid(Number value) {
        return isValid(value.toString());
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }

        BigDecimal bigNum = getBigDecimalValue( value );
        if ( bigNum == null ) {
            return false;
        }

        int integerPartLength = bigNum.precision() - bigNum.scale();
        int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();

        return ( maxIntegerLength >= integerPartLength && maxFractionLength >= fractionPartLength );
    }

    private BigDecimal getBigDecimalValue(CharSequence charSequence) {
        BigDecimal bd;
        try {
            bd = new BigDecimal( charSequence.toString() );
        }
        catch (NumberFormatException nfe) {
            return null;
        }
        return bd;
    }

    private void validateParameters() {
        if ( maxIntegerLength < 0 ) {
            throw new IllegalArgumentException("The length of the integer part cannot be negative");
        }
        if ( maxFractionLength < 0 ) {
            throw new IllegalArgumentException("The length of the fraction part cannot be negative");
        }
    }
}
