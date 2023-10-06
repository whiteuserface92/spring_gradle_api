package com.dlsdlworld.spring.api.utils;

import org.springframework.util.Assert;

/**
 * 문자열을 처리를 위한 유틸리티
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 문자열을 카멜케이스로 변환
     * @param s
     * @return
     */
    public static String toCamelCase(String s) {
        String[] tokens = s.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (String t : tokens) {
            sb.append(beginWithUpperCase(t));
        }

        return sb.toString();
    }

    /**
     * 첫 문자를 대문자로 변경
     * @param s
     * @return
     */
    public static String beginWithUpperCase(String s) {
        if (s.length() == 0) return "";
        char[] array = s.toCharArray();
        array[0] = Character.toUpperCase(array[0]);

        return new String(array);
    }

    /**
     * 문자열을 스네이크 케이스로 변환
     * @param s
     * @return
     */
    public static String toSnakeCase(String s) {
        String text = "";
        boolean isFirst = true;
        for(char c: s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (isFirst)
                    isFirst = false;
                else
                    text += "_";

                text += Character.toLowerCase(c);
            } else {
                text += c;
            }
        }

        return text.toUpperCase();
    }

    /**
     * prefix 제거
     * @param s
     * @param prefix
     * @return
     */
    public static String removePrefix(String s, String prefix) {
        if (s != null && prefix != null && s.startsWith(prefix))
            return s.substring(prefix.length());

        return s;
    }

    /**
     * Positive Integer 체크
     * @param cs
     * @return
     */
    public static boolean isPositiveInteger(String cs) {
        try {
            Integer x = Integer.parseInt(cs);
            return x > 0;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static String removeFirstSlush(String s) {
        Assert.notNull(s, "Argument string must not be null");
        if (isEmpty(s) || s.trim().length() < 2)
            throw new IllegalArgumentException("invalid string:" + s);
        if (s.trim().startsWith("/"))
            return s.substring(1);

        return s;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String removeLastSlush(String s) {
        Assert.notNull(s, "Argument string must not be null");
        if (isEmpty(s) || s.trim().length() < 2)
            throw new IllegalArgumentException("invalid string:" + s);
        if (s.trim().endsWith("/"))
            return s.substring(0, s.length() - 1);

        return s;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String removeFirstAndLastSlush(String s) {
        return removeLastSlush(removeFirstSlush(s));
    }

    /**
     *
     * @param text
     * @param searchParam
     * @param replacementParam
     * @return
     */
    public static String replaceParam(String text, String searchParam, String replacementParam) {
        return replace(text, "\"" + searchParam + "\"", "\"" + replacementParam + "\"");
    }
}

