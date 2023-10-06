package com.dlsdlworld.spring.api.types;

import com.dlsdlworld.spring.api.exception.RuntimeException;

/**
 */
public class InvalidUserCredentialsException extends RuntimeException {

    /**
     *
     */
    public InvalidUserCredentialsException() {
        super(ErrorTypes.INVALID_USER_CREDENTIALS);
    }

    /**
     *
     * @param throwable
     */
    public InvalidUserCredentialsException(Throwable throwable) {
        super(ErrorTypes.INVALID_USER_CREDENTIALS, throwable);
    }
}
