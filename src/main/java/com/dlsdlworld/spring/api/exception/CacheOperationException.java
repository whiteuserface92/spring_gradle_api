package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;
/**
 * 캐시 트렌젝션 처리중 발생되는 에러
 */
public class CacheOperationException extends RuntimeException {

    /**
     * 생성자
     */
    public CacheOperationException() {
        super(ErrorTypes.CACHE_OPERATION);
    }

    /**
     * 생성자
     * @param cause
     */
    public CacheOperationException(Throwable cause) {
        super(ErrorTypes.CACHE_OPERATION, cause);
    }
}
