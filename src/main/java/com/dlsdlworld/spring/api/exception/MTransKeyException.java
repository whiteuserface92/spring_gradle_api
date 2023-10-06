package com.dlsdlworld.spring.api.exception;

import org.springframework.http.HttpStatus;

/**
 */
public class MTransKeyException extends HttpStatusException {

    //private final String message;

    public MTransKeyException(int rtn) {
        super();
        this.message = Type.getMessage(rtn);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public enum Type {
        ERR_SUCCESS                 (     0, "성공"),
        ERR_MTK_FAILED_LOADLIBRARY  (-  100, "라이브러리 로드를 실패했습니다."),
        ERR_MTK_NOT_FOUND_FUNCTION  (-  101, "해당 함수를 찾을 수 없습니다."),
        ERR_MTK_NOT_MATCH_HEADER    (-10000, "MTK 헤더 포맷이 맞지 않습니다."),
        ERR_MTK_INVALID_INPUT_PARAM (-10001, "입력 파라메터가 유효하지 않습니다."),
        ERR_MTK_INVALID_OUTPUT_PARAM(-10002, "출력 파라메터가 유효하지 않습니다."),
        ERR_MTK_FAILED_DECRYPT_DATA (-10003, "복호화를 실패했습니다."),
        ERR_MTK_FAILED_PARSE_DATA   (-10004, "보호화 된 데이터가 규약에 맞지 않습니다."),
        ERR_MTK_FAILED_MEMORY_ALLOC (-10005, "메모리 할당을 실패했습니다."),
        ERR_MTK_NOT_CORRECT_HMAC    (-10006, "암호화 데이터 무결성 검사를 실패했습니다."),
        ERR_MTK_INVALID_DATA_LEN    (-10007, "암호화 데이터의 길이가 유효하지 않습니다."),
        ERR_MTK_DATABASE            ( 50000, "키보드보안 데이터베이스 오류"),
        ERR_UNKNOWN                 ("알수 없는 에러 입니다. (%d)"),
        ;

        int code;
        String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        Type(String desc) {
            this.desc = desc;
        }

        public int code() {
            return code;
        }

        static String getMessage(int code) {
            for (Type v : values())
                if (v.code == code)
                    return v.desc;
            return String.format(ERR_UNKNOWN.desc, code);
        }
    }
}
