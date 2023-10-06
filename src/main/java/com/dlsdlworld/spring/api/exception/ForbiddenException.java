package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class ForbiddenException extends RuntimeException {

    /**
     * 생성자
     */
    public ForbiddenException() {
        super(ErrorTypes.INSUFFICIENT_PRIVILEGES);
    }

    /**
     * 생성자
     * @param cause
     */
    public ForbiddenException(Throwable cause) {
        super(ErrorTypes.INSUFFICIENT_PRIVILEGES, cause);
    }

//    public ForbiddenException(String apiName, Throwable cause) {
//        super(ErrorTypes.INSUFFICIENT_PRIVILEGES, cause, apiName);
//    }
}