package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 전문 메시지의 필드 정보를 찾지 못했을때 발생되는 에러
 */
public class MessageConfigNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public MessageConfigNotFoundException(String messageName) {
        super(ErrorTypes.MESSAGE_CONFIG_NOT_FOUND, messageName);
    }

}
