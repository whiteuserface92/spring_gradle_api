package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class HospitalApiNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public HospitalApiNotFoundException(Long hospitalId, String apiUrl) {
        super(ErrorTypes.HOSPITAL_API_NOT_FOUND, hospitalId, apiUrl);
    }

}
