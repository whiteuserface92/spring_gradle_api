package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class InvalidApiRequestSpecException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidApiRequestSpecException(String id, String spec) {
        super(ErrorTypes.INVALID_API_SPEC, id, spec);
    }

}
