package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class InvalidTcpMessageException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidTcpMessageException() {
        super(ErrorTypes.INVALID_TCP_MESSAGE);
    }

    /**
     * 생성자
     * @param cause
     */
    public InvalidTcpMessageException(Throwable cause) {
        super(ErrorTypes.INVALID_TCP_MESSAGE, cause);
    }
}