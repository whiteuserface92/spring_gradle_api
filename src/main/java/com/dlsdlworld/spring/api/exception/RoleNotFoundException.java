package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * API 호출시 발생되는 예외
 */
public class RoleNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public RoleNotFoundException(String roleNm) {
        super(ErrorTypes.ROLE_NOT_FOUND, roleNm);
    }

}
