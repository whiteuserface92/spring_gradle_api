package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 메타엔진이 api 호출시 응답 타입을 찾지 못했을때 발생되는 예외
 */
public class ApiResponseBodyClassCastException extends RuntimeException {

    /**
     * 생성자
     * @param bodyClass 응답클래스
     * @param castTypeName 응답타입명
     */
    public ApiResponseBodyClassCastException(Class bodyClass, String castTypeName) {
        super(ErrorTypes.INVALID_API_RESPONSE_BODY_TYPE);
    }

    /**
     * 생성자
     * @param cause 에러
     * @param bodyClass 응답클래스
     * @param castTypeName 응답타입명
     */
    public ApiResponseBodyClassCastException(Throwable cause, Class bodyClass, String castTypeName) {
        super(ErrorTypes.INVALID_API_RESPONSE_BODY_TYPE, cause, bodyClass, castTypeName);
    }

}
