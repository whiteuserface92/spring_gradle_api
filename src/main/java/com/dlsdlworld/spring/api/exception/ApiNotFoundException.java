package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진을 통해 api 정보를 찾지 못했을때 발생되는 에러
 */
public class ApiNotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param apiUrl api주소
     */
    public ApiNotFoundException(String apiUrl) {
        super(ErrorTypes.API_NOT_FOUND, apiUrl);
    }

    /**
     * 생성자
     * @param apiUrl api주소
     * @param cause 에러
     */
    public ApiNotFoundException(String apiUrl, Throwable cause) {
        super(ErrorTypes.API_NOT_FOUND, cause, apiUrl);
    }

}
