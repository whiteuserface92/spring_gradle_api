package com.dlsdlworld.spring.api.validator;

/**

 */
public class NegativeValidator {

    /**
     * return valid
     * @param value
     * @return
     */
    public boolean isValid(Number value) {
        if ( value == null ) {
            return true;
        }

        return NumberSignHelper.signum( value ) < 0;
    }

}
