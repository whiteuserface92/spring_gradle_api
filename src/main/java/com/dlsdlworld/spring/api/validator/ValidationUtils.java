package com.dlsdlworld.spring.api.validator;

import com.dlsdlworld.spring.api.exception.ConstraintViolationException;
import com.dlsdlworld.spring.api.types.ConstraintTypes;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 입력값 검증시 사용하는 유틸리티
 */
public class ValidationUtils extends org.springframework.validation.ValidationUtils {

    /**
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String validationBean(T obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<T>> violationSet = validator.validate(obj);
        for (ConstraintViolation violation : violationSet) {
            result.append(violation.getPropertyPath()).append(":").append(violation.getMessage());
        }

        return result.toString();
    }

    /**
     *
     * @param constraintType
     * @param value
     * @param args
     */
    public static boolean isValid(ConstraintTypes constraintType, Object value, Object...args) {

        try {
            switch (constraintType) {
                case EQUAL:
                    return new AssertTrueValidator().isValid(value.toString().equals(args[0].toString()));

                case MAX:
                    return new MaxValidator(args[0].toString()).isValid((String)value);

                case MIN:
                    return new MinValidator(args[0].toString()).isValid((String)value);

                case SIZE:
                    return new SizeValidator((int)args[0], (int)args[1]).isValid((String)value);

                case PATTERN:
                    return new PatternValidator((String)args[0]).isValid((String)value);

                case NOT_NULL:
                    return new NotNullValidator().isValid(value);

                case NOT_EMPTY:
                    return new NotEmptyValidator().isValid(value);

                case NOT_BLANK:
                    return new NotBlankValidator().isValid(value);

                case DIGITS:
                    return new DigitsValidator((int)args[0], (int)args[1]).isValid(value);

                case EMAIL:
                    return new EmailValidator().isValid(value.toString());

                case PAST:
                    if (value instanceof LocalDateTime)
                        return new PastValidator().isValid((LocalDateTime)value);
                    else if (value instanceof LocalDate)
                        return new PastValidator().isValid((LocalDateTime)value);
                    else
                        return new PastValidator().isValid(LocalDateTime.parse(value.toString()));

                case FUTURE:
                    if (value instanceof LocalDateTime)
                        return new FutureValidator().isValid((LocalDateTime)value);
                    else if (value instanceof LocalDate)
                        return new FutureValidator().isValid((LocalDateTime)value);
                    else
                        return new FutureValidator().isValid(LocalDateTime.parse(value.toString()));

                case NEGATIVE:
                    if (value instanceof Number)
                        return new NegativeValidator().isValid((Number)value);
                    else
                        return new NegativeValidator().isValid(NumberFormat.getInstance().parse(value.toString()));

                case POSITIVE:
                    if (value instanceof Number)
                        return new PositiveValidator().isValid((Number)value);
                    else
                        return new PositiveValidator().isValid(NumberFormat.getInstance().parse(value.toString()));

                case NEGATIVE_OR_ZERO:
                    if (value instanceof Number)
                        return new NegativeOrZeroValidator().isValid((Number)value);
                    else
                        return new NegativeOrZeroValidator().isValid(NumberFormat.getInstance().parse(value.toString()));

                case POSITIVE_OR_ZERO:
                    if (value instanceof Number)
                        return new PositiveOrZeroValidator().isValid((Number)value);
                    else
                        return new PositiveOrZeroValidator().isValid(NumberFormat.getInstance().parse(value.toString()));

                default:
                    throw new UnsupportedOperationException(constraintType + " not supported yet. Please inform the core team");
            }
        } catch(Exception e) {
            throw new ConstraintViolationException(e, constraintType.name());
        }
    }

    public static void rejectIfPast(Errors errors, String field, String errorCode) {
        Assert.notNull(errors, "Errors object must not be null");
        Object value = errors.getFieldValue(field);
        if (value == null || !StringUtils.hasLength(value.toString())) {
            errors.rejectValue(field, errorCode, null, null);
        } else {
            if (value instanceof LocalDateTime) {
                if (new PastValidator().isValid((LocalDateTime)value) == true) {
                    errors.rejectValue(field, errorCode, null, null);
                }
            } else {
                errors.rejectValue(field, errorCode, null, "The field value types is not LocalDateTime");
            }
        }
    }

    public static void rejectIfEmptyOrMin(Errors errors, String field, Number minValue, String errorCode) {
        Assert.notNull(errors, "Errors object must not be null");
        Object value = errors.getFieldValue(field);
        if (value == null || !StringUtils.hasLength(value.toString())) {
            errors.rejectValue(field, errorCode, new Object[]{minValue}, null);
        } else {
            if (new MinValidator(minValue.toString()).isValid(value.toString()) == false) {
                errors.rejectValue(field, errorCode, new Object[]{minValue}, null);
            }
        }
    }

}
