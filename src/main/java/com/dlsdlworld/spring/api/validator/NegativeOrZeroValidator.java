package com.dlsdlworld.spring.api.validator;

/**

 */
public class NegativeOrZeroValidator {

    public boolean isValid(Number value) {
        if ( value == null ) {
            return true;
        }

        return NumberSignHelper.signum( value ) <= 0;
    }
}
