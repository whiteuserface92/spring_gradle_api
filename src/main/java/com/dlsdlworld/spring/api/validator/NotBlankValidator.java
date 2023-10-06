package com.dlsdlworld.spring.api.validator;

/**

 */
public class NotBlankValidator {

    public boolean isValid(Object value) {
        if ( value == null ) {
            return false;
        }

        return value.toString().trim().length() > 0;
    }
}
