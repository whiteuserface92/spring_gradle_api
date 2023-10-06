package com.dlsdlworld.spring.api.validator;

import java.time.LocalDateTime;

/**

 */
public class PastValidator {

    public boolean isValid(LocalDateTime value) {
        if ( value == null ) {
            return true;
        }

        return LocalDateTime.now().isAfter(value);
    }
}
