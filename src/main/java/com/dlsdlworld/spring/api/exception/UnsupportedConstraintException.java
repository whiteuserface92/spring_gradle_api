package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 제약조건 위반시 발생되는 에러
 */
public class UnsupportedConstraintException extends RuntimeException {

    /**
     *
     */
	public UnsupportedConstraintException(String constraintName) {
        super(ErrorTypes.UNSUPPORTED_CONSTRAINT_VIOLATION, constraintName);
    }

}
