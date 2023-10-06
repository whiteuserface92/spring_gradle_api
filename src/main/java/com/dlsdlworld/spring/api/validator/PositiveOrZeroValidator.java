package com.dlsdlworld.spring.api.validator;

/**

 */
public class PositiveOrZeroValidator {

    public boolean isValid(Number value) {
        if ( value == null ) {
            return true;
        }

        return NumberSignHelper.signum( value ) >= 0;
    }
}
