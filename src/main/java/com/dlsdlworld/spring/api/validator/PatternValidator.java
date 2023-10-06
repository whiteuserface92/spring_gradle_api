package com.dlsdlworld.spring.api.validator;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

/**

 */
public class PatternValidator {

    private java.util.regex.Pattern pattern;

    public PatternValidator(String regexp) {
        try {
            pattern = java.util.regex.Pattern.compile( regexp );
        }
        catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid regular expression:" + regexp);
        }
    }

    public boolean isValid(String value) {
        if ( value == null ) {
            return true;
        }

        Matcher m = pattern.matcher( value );
        return m.matches();
    }
}
