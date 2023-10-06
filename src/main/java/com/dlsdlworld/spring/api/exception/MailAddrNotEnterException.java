package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class MailAddrNotEnterException extends RuntimeException {

    /**
     * 생성자
     */
    public MailAddrNotEnterException() {
        super(ErrorTypes.MAIL_ADDR_NOT_ENTER);
    }

}
