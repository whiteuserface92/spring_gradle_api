package com.dlsdlworld.spring.api.utils;

import com.dlsdlworld.spring.api.exception.ParamConstraintViolationException;
import com.dlsdlworld.spring.api.types.ConstraintTypes;
import com.dlsdlworld.spring.api.validator.NotEmptyValidator;
import com.dlsdlworld.spring.api.validator.PatternValidator;

import java.util.Collection;

public class Assert {

    private static final String NUMBER_ALPHABET_PATTERN = "^[a-zA-Z\\d]+$";
    private static final String NUMERIC_PATTERN = "^[\\+\\-]{0,1}[\\d]+$";
    private static final String FLOAT_PATTERN = "^[\\+\\-]{0,1}[\\d]+[\\.][0-9]+$";

    /**
     * 값이 영문 알파벳, 숫자가 아닌 경우 예외 발생
     * @param param
     * @param value
     */
    public static void isAlphaNumber(String param, String value) {
        isMatched(param, value, NUMBER_ALPHABET_PATTERN);
    }

    /**
     * 값이 숫자가 아닌 경우 예외 발생
     * @param param
     * @param value
     */
    public static void isNumeric(String param, String value) {
        isMatched(param, value, NUMERIC_PATTERN);
    }

    /**
     * 값이 float, double이 아닌 경우 예외 발생
     * @param param
     * @param value
     */
    public static void isFloat(String param, String value) {
        isMatched(param, value, FLOAT_PATTERN);
    }

    /**
     * 패턴 매치, 해당 패턴과 일치 하지 않는 경우 예외 발생
     * @param param
     * @param value
     * @param pattern
     */
    public static void isMatched(String param, String value, String pattern) {
        if (new NotEmptyValidator().isValid(value) == false)
            throw new ParamConstraintViolationException(param, value, ConstraintTypes.NOT_EMPTY.name());
        if (new PatternValidator(pattern).isValid(value) == false)
            throw new ParamConstraintViolationException(param, value, ConstraintTypes.PATTERN.name());
    }

    /**
     * 널이나 공백이면 예외 발생
     * @param param
     * @param value
     */
    public static void notEmpty(String param, String value) {
        if (new NotEmptyValidator().isValid(value) == false)
            throw new ParamConstraintViolationException(param, value, ConstraintTypes.NOT_EMPTY.name());
    }

    public static void notEmpty(String param, Collection value) {
        if (new NotEmptyValidator().isValid(value) == false)
            throw new ParamConstraintViolationException(param, value, ConstraintTypes.NOT_EMPTY.name());
    }

    /**
     * 널이면 예외 발생
     * @param param
     * @param value
     */
    public static void notNull(String param, Object value) {
        if (new NotEmptyValidator().isValid(value) == false)
            throw new ParamConstraintViolationException(param, value, ConstraintTypes.NOT_EMPTY.name());
    }

    public static void isTrue(String param, boolean expression) {
        if (!expression)
            throw new ParamConstraintViolationException(param, expression, ConstraintTypes.NOT_EMPTY.name());
    }

}
