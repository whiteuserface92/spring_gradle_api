package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class InvalidApiResponseException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidApiResponseException() {
        super(ErrorTypes.INVALID_API_RESPONSE);
    }

    /**
     * 생성자
     * @param cause
     */
    public InvalidApiResponseException(Throwable cause) {
        super(ErrorTypes.INVALID_API_RESPONSE, cause);
    }

}
