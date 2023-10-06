package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 전문 메시지의 필드 정보를 찾지 못했을때 발생되는 에러
 */
public class FieldConfigNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public FieldConfigNotFoundException(String field) {
        super(ErrorTypes.FIELD_CONFIG_NOT_FOUND, field);
    }

}
