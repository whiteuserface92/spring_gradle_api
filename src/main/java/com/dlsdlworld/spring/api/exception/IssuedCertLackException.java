package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class IssuedCertLackException extends RuntimeException {

    /**
     * 생성자
     */
    public IssuedCertLackException(Integer purchase, Integer use) {
        super(ErrorTypes.ISSUED_CERT_LACK_FAIL, purchase, use);
    }

}
