package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class AuthTokenNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public AuthTokenNotFoundException() {
        super(ErrorTypes.AUTH_TOKEN_NOT_FOUND);
    }

}
