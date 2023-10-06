package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

public class NoDataToClaimException extends RuntimeException{
    public NoDataToClaimException() {
        super(ErrorTypes.TO_CLAIM_TREATMENT_SEARCH_FAIL);
    }
}
