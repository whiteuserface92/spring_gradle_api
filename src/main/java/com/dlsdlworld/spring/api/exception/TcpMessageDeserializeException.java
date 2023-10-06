package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class TcpMessageDeserializeException extends RuntimeException {

    /**
     * 생성자
     */
    public TcpMessageDeserializeException() {
        super(ErrorTypes.TCP_MESSAGE_DESERILIZE);
    }
}