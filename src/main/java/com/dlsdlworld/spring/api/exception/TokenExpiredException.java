package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class TokenExpiredException extends RuntimeException {

    /**
     *
     */
    public TokenExpiredException() {
        super(ErrorTypes.TOKEN_EXPIRED);
    }

}