package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * 생성자
     */
    public UnauthorizedException() {
        super(ErrorTypes.INVALID_USER_CREDENTIALS);
    }

    /**
     *
     * @param cause
     */
    public UnauthorizedException(Throwable cause) {
        super(ErrorTypes.INVALID_USER_CREDENTIALS, cause);
    }

//    public UnauthorizedException(String apiName, Throwable cause) {
//        super(ErrorTypes.INVALID_USER_CREDENTIALS, cause, apiName);
//    }

//    public UnauthorizedException(String targetName) {
//        super(ErrorTypes.AUTHORIZED_FAIL, targetName);
//    }

}