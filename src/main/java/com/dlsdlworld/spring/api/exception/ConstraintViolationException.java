package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 제약조건 위반시 발생되는 에러
 */
public class ConstraintViolationException extends RuntimeException {

	public ConstraintViolationException(String constraintName) {
		super(ErrorTypes.CONSTRAINT_VIOLATION, constraintName);
	}

    /**
     *
     */
	public ConstraintViolationException(Exception e, String constraintName) {
        super(ErrorTypes.CONSTRAINT_VIOLATION, e, constraintName);
    }

}
