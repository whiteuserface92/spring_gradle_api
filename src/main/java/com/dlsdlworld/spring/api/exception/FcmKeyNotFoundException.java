package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class FcmKeyNotFoundException extends RuntimeException {

    /**
     * 생성자
     */
    public FcmKeyNotFoundException(Long appId) {
        super(ErrorTypes.FCM_KEY_NOT_FOUND, appId);
    }

}