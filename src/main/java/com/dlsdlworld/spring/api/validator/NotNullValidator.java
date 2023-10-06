package com.dlsdlworld.spring.api.validator;

/**

 */
public class NotNullValidator {

    public boolean isValid(Object object) {
        return object != null;
    }
}
