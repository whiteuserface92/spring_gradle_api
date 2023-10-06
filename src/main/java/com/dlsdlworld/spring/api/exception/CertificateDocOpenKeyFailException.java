package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class CertificateDocOpenKeyFailException extends RuntimeException {

    /**
     * 생성자
     */
    public CertificateDocOpenKeyFailException() {
        super(ErrorTypes.CERT_DOC_OPEN_KEY_FAIL);
    }

}
