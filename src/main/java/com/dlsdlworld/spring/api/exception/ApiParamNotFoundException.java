package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진이 api 파라미터 정보를 찾지 못했을때 발생되는 에러
 */
public class ApiParamNotFoundException extends RuntimeException {

    /**
     * 생성자
     * @param apiUrl api주소
     * @param param 파라미터
     */
    public ApiParamNotFoundException(String apiUrl, Object param) {
        super(ErrorTypes.API_PARAM_NOT_FOUND, apiUrl, param);
    }

}
