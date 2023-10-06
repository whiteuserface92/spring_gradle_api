package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class InsuApiNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public InsuApiNotFoundException(String compCd) {
        super(ErrorTypes.INSU_API_NOT_FOUND, compCd);
    }

    /**
     *
     * @param compCd
     * @param cause
     */
    public InsuApiNotFoundException(String compCd, Throwable cause) {
        super(ErrorTypes.INSU_API_NOT_FOUND, cause, compCd);
    }

}
