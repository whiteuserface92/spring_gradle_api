package com.dlsdlworld.spring.api.types;

/**
 */
public enum PlatformTypes {

    ANDROID("ANDROID"),

    IOS("IOS"),

    PC("PC"),

    ETC("ETC");

    final String code;

    PlatformTypes(String code) {
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
    public static PlatformTypes codeOf(String code) {
        for (PlatformTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of product type: " + code);
    }
}
