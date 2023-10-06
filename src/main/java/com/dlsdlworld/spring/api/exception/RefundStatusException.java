package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 관리자 환불처리
 */
public class RefundStatusException extends RuntimeException{
    public RefundStatusException(String targetNm, String info ){
        super(ErrorTypes.REFUND_STATUS, targetNm, info);
    }
}
