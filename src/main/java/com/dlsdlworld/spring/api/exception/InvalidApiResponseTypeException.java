package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class InvalidApiResponseTypeException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidApiResponseTypeException(String apiUrl, String responseType) {
        super(ErrorTypes.INVALID_API_RESPONSE_TYPE, apiUrl, responseType);
    }

}
