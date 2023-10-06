package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 프레임워크 설정시 발생되는 예외
 */
public class FrameworkConfigException extends RuntimeException {

    /**
     *
     * @param config
     */
    public FrameworkConfigException(String config) {
        super(ErrorTypes.FRAMEWORK_CONFIG, config);
    }

    /**
     * 생성자
     * @param cause
     */
    public FrameworkConfigException(Throwable cause) {
        super(ErrorTypes.FRAMEWORK_CONFIG, cause);
    }
}
