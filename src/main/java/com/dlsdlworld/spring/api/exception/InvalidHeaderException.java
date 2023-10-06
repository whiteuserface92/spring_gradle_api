package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class InvalidHeaderException extends RuntimeException {

    /**
     *
     */
    public InvalidHeaderException(String apiUrl, String errorMessage) {
        super(ErrorTypes.INVALID_HEADER, apiUrl, errorMessage);
    }

}