package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 제약조건 위반시 발생되는 에러
 */
public class ParamConstraintViolationException extends RuntimeException {

    /**
     * -00039=파라미터{0}의 값{1}이 제약조건{2}에 위배됩니다.
     */
	public ParamConstraintViolationException(String paramName, Object paramValue, String constraintName) {
        super(ErrorTypes.API_PARAM_CONSTRAINT_VIOLATION, paramName, paramValue, constraintName);
    }

}
