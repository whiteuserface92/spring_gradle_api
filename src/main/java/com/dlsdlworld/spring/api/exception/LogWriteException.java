package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class LogWriteException extends RuntimeException {

    /**
     * 생성자
     */
    public LogWriteException(Throwable cause) {
        super(ErrorTypes.LOG_WRITE_FAILED, cause);
    }

}
