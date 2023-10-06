package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class HospitalIdAlreadyExistsException extends RuntimeException {

    public HospitalIdAlreadyExistsException(Long hospitalId) {
        super(ErrorTypes.HOSPITAL_ID_ALREADY_EXISTS, hospitalId);
    }

    public HospitalIdAlreadyExistsException(Long hospitalId, Throwable cause) {
        super(ErrorTypes.HOSPITAL_ID_ALREADY_EXISTS, cause, hospitalId);
    }
}