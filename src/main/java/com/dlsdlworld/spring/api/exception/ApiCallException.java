package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진을 통해 API 호출시 발생되는 예외
 */
public class ApiCallException extends RuntimeException {

    /**
     * 생성자
     * @param apiUrl api 주소
     */
    public ApiCallException(String apiUrl) {
        super(ErrorTypes.API_CALL, apiUrl);
    }

    /**
     * 생성자
     * @param apiUrl api주소
     * @param cause 에러원인
     */
    public ApiCallException(String apiUrl, Throwable cause) {
        super(ErrorTypes.API_CALL, cause, apiUrl);
    }

}
