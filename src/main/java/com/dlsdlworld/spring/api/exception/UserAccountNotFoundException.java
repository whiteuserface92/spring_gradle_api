package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 */
public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String account) {
        super(ErrorTypes.USER_ACCOUNT_NOT_FOUND, account);
    }

    public UserAccountNotFoundException(String account, Throwable cause) {
        super(ErrorTypes.USER_ACCOUNT_NOT_FOUND, cause, account);
    }
}