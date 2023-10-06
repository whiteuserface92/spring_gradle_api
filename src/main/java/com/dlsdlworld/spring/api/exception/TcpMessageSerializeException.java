package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class TcpMessageSerializeException extends RuntimeException {

    /**
     * 생성자
     */
    public TcpMessageSerializeException() {
        super(ErrorTypes.TCP_MESSAGE_SERILIZE);
    }
}