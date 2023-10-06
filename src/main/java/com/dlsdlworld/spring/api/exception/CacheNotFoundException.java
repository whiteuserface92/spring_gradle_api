package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class CacheNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public CacheNotFoundException(String target) {
        super(ErrorTypes.CACHE_READ, target);
    }

    /**
     * 생성자
     * @param cause
     */
    public CacheNotFoundException(String target, Throwable cause) {
        super(ErrorTypes.CACHE_READ, cause, target);
    }
}
