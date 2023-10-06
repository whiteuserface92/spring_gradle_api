package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class EmailException extends RuntimeException {

    /**
     * 생성자
     */
    public EmailException(Throwable cause) {
        super(ErrorTypes.API_CALL, cause);
    }

}
