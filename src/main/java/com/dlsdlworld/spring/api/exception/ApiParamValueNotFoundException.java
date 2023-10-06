package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진이 api의 파라미터 값을 찾지 못했을때 발생되는 에러
 */
public class ApiParamValueNotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param apiUrl api주소
     * @param param 파라미터
     */
    public ApiParamValueNotFoundException(String apiUrl, Object param) {
        super(ErrorTypes.API_PARAM_VALUE_NOT_FOUND, apiUrl, param);
    }

}
