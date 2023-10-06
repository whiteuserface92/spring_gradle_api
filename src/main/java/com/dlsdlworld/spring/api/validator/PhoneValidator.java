package com.dlsdlworld.spring.api.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

/**

 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        PhoneNumberUtil util = PhoneNumberUtil.getInstance();
        final String country = Locale.getDefault().getCountry();
        try {
            return PhoneNumberUtil.getInstance().isValidNumber(util.parseAndKeepRawInput(field, country));
        } catch (NumberParseException e) {
            return false;
        }
    }
}
