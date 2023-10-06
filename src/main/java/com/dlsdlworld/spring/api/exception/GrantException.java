package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 인증 처리시 발생되는 런타임 에러
 */
public class GrantException extends RuntimeException {

    public GrantException(Throwable cause) {
        super(ErrorTypes.GRANT_FAILED, cause);
    }

}
