package com.dlsdlworld.spring.api.types;

/**
 */
public enum ProductTypes {


    CARE("10"),

    CARE365("20"),

    INSU_CERT("30"),

    CAREPLUS("40");

    final String code;

    ProductTypes(String code) {
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
    public static ProductTypes codeOf(String code) {
        for (ProductTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of product type: " + code);
    }
}
