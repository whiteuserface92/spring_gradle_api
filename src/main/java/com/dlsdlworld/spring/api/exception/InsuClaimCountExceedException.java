package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

public class InsuClaimCountExceedException extends RuntimeException{
    public InsuClaimCountExceedException(){
        super(ErrorTypes.TO_CLAIM_TREATMENT_COUNT_EXCEED);
    }
}
