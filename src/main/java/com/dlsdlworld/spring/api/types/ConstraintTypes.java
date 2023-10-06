package com.dlsdlworld.spring.api.types;

/**

 */
public enum ConstraintTypes {

    /** 값이 널이 아닌지 체크*/
    NOT_NULL,

    /** 날짜가 과거인지 체크*/
    PAST,

    /** 날짜가 미래인지 체크*/
    FUTURE,

    /** 정규식과 일치하는 체크*/
    PATTERN,

    /** 길이가 min과 max값의 사이인지 체크*/
    SIZE,

    /** 이메일 형식인지 체크*/
    EMAIL,

    /** 지정된 최대값보다 작은지 체크*/
    MAX,

    /** 지정된 최소값 이상인지 체크*/
    MIN,

    /** null 또는 비어있지 않은지 체크*/
    NOT_EMPTY,

    /** null 또는 트림 된 길이가 0보다 큰지 체크*/
    NOT_BLANK,

    /** 값이 양수인지 체크*/
    POSITIVE,

    /** 값이 양수 또는 0인지*/
    POSITIVE_OR_ZERO,

    /** 값이 음수인지 체크*/
    NEGATIVE,

    /** 값이 음수 또는 0인지 체크*/
    NEGATIVE_OR_ZERO,

    /** 숫자체크*/
    DIGITS,

    /** 문자열 체크*/
    EQUAL;
}
