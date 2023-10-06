package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진이 api의 응답 파라미터를 찾지 못했을때 발생되는 에러
 */
public class ApiResonseParamsNotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param apiUrl api주소
     */
    public ApiResonseParamsNotFoundException(String apiUrl) {
        super(ErrorTypes.API_RESPONSE_PARAMS_NOT_FOUND, apiUrl);
    }

}
