package com.dlsdlworld.spring.api.types;

/**
 */
public enum ResourceTypes {

     CODE("1")
    ,GROUP("2")
    ,HOSPITAL("3")
    ,APP("4")
    ,PUSH("5")
    ,MESSAGE("6")
    ,MENU("7")
    ,API("8")
    ,TERMS("9")
    ,USER("10")
    ,AUTHORITY("11")
    ,PAYMENT("12")
    ,INSU("13")
    ,CERT("14")
    ,RECEIPT("15")
    ,LOG("16")
    ,API_DATASOURCE("17")
    ,API_QUERY("18")
    ,GOODS("19")
    ,ORDER("20")
    ,APP_DEVICE("21")
    ,ALL("999")
    ;

    final String code;

    ResourceTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    /**
     *
     * @param code
     * @return
     */
    public static ResourceTypes codeOf(String code) {
        for (ResourceTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }
}
