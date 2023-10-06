package com.dlsdlworld.spring.api.exception;

import com.dlsdlworld.spring.api.types.ErrorTypes;

/**
 * 캐시에서 정보를 찾지 못했을때 발생되는 에러
 */
public class ExportProcessFailException extends RuntimeException {

    /**
     * 생성자
     */
    public ExportProcessFailException(String exportMethod, String attachment, Integer price) {
        super(ErrorTypes.EXPORT_PROCESS_FAIL, exportMethod, attachment, price);
    }

}
