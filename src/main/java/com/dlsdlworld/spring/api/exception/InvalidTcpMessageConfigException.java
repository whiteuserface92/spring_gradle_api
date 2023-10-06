package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 전문 메시지의 필드 정보를 찾지 못했을때 발생되는 에러
 */
public class InvalidTcpMessageConfigException extends RuntimeException {

    /**
     * 생성자
     */
    public InvalidTcpMessageConfigException(String config) {
        super(ErrorTypes.INVALID_TCP_MESSAGE_CONFIG, config);
    }

    /**
     * 생성자
     * @param cause
     */
    public InvalidTcpMessageConfigException(Throwable cause) {
        super(ErrorTypes.INVALID_TCP_MESSAGE_CONFIG, cause);
    }

}
