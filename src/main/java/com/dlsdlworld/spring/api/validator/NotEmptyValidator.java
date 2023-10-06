package com.dlsdlworld.spring.api.validator;

import java.util.Collection;

/**

 */
public class NotEmptyValidator {

    public boolean isValid(Object value) {
        if ( value == null ) {
            return false;
        }

        if (value instanceof Collection)
            return ((Collection) value).size() > 0;
        else
            return value.toString().length() > 0;
    }
}
