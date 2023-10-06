package com.dlsdlworld.spring.api.validator;

/**

 */
public class SizeValidator {

    private final int min;
    private final int max;

    public SizeValidator(int min, int max) {
        this.min = min;
        this.max = max;
        validateParameters();
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }
        int length = value.length();
        return length >= min && length <= max;
    }

    private void validateParameters() {
        if ( min < 0 ) {
            throw new IllegalArgumentException("The min parameter cannot be negative");
        }
        if ( max < 0 ) {
            throw new IllegalArgumentException("The max parameter cannot be negative");
        }
        if ( max < min ) {
            throw new IllegalArgumentException("The length cannot be negative");
        }
    }
}
