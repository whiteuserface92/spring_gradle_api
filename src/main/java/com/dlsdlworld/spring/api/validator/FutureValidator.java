package com.dlsdlworld.spring.api.validator;

import java.time.LocalDateTime;

/**

 */
public class FutureValidator {

    public boolean isValid(LocalDateTime value) {
        if ( value == null ) {
            return true;
        }

        return LocalDateTime.now().isBefore(value);
    }
}
