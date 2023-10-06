package com.dlsdlworld.spring.api.validator;

/**

 */
public class PositiveValidator {

    /**
     *
     * @param value
     * @return
     */
    public boolean isValid(Number value) {
        if ( value == null ) {
            return true;
        }

        return NumberSignHelper.signum( value ) > 0;
    }

}
