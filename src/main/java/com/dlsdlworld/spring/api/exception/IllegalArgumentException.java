package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String param) {
        super(ErrorTypes.ILLEGAL_ARGUMENT, param);
    }

    public IllegalArgumentException(String param, Throwable cause) {
        super(ErrorTypes.ILLEGAL_ARGUMENT, cause, param);
    }

}
