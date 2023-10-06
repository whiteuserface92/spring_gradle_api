package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 리소스(엔티티)를 찾을 수 없을 때 발생되는 에러
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public EntityNotFoundException(String name) {
        super(ErrorTypes.RESOURCE_NOT_FOUND, name);
    }

    public EntityNotFoundException(String name, Object id) {
        super(ErrorTypes.RESOURCE_NOT_FOUND, name + ":" + id.toString());
    }

}

