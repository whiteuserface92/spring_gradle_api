package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class UserAccountAlreadyExistsException extends RuntimeException {

    public UserAccountAlreadyExistsException(String account) {
        super(ErrorTypes.USER_ACCOUNT_ALREADY_EXISTS, account);
    }

    public UserAccountAlreadyExistsException(String account, Throwable cause) {
        super(ErrorTypes.USER_ACCOUNT_ALREADY_EXISTS, cause, account);
    }
}