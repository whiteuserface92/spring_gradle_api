package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

import java.util.Arrays;

/**
 * 프레임워크에서 발생되는 최상위 런타임 에러
 */
public class RuntimeException extends java.lang.RuntimeException {

    /**
     * 에러타입
     */
    private ErrorTypes errorType;

    /**
     * 메세지 아규먼트
     */
    private Object[] args;

    /**
     *
     */
    public RuntimeException() {
    	this(ErrorTypes.INTERNAL_SERVER);
    }

    /**
     *
     * @param errorType
     */
    public RuntimeException(ErrorTypes errorType) {
        super(errorType.toString());
        this.errorType = errorType;
    }

    /**
     *
     * @param errorType
     * @param args
     */
    public RuntimeException(ErrorTypes errorType, Object... args) {
    	super(errorType + ":" + Arrays.toString(args));
    	this.errorType = errorType;
    	this.args = args;
    }

    /**
     *
     * @param cause
     */
    public RuntimeException(Throwable cause) {
        this(ErrorTypes.INTERNAL_SERVER, cause);
    }

    /**
     *
     * @param errorType
     * @param cause
     */
    public RuntimeException(ErrorTypes errorType, Throwable cause) {
        super(errorType.toString(), cause);
        this.errorType = errorType;
    }

    /**
     *
     * @param errorType
     * @param cause
     * @param args
     */
    public RuntimeException(ErrorTypes errorType, Throwable cause, Object... args) {
        this(errorType, cause);
        this.args = args;
    }

    /**
     *
     * @return
     */
    public ErrorTypes getErrorType() {
        return errorType;
    }

    /**
     *
     * @return
     */
    public Object[] getArgs() {
        return args;
    }

}
