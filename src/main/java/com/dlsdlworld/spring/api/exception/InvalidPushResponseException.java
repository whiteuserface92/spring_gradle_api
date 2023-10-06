package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class InvalidPushResponseException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidPushResponseException() {
        super(ErrorTypes.INVALID_PUSH_RESPONSE);
    }
}