package com.dlsdlworld.spring.api.types;

/**
 */
public enum AccessTypes {

      READ("1")
     ,DOWNLOAD("2")
     ,WRITE("3")
     ,DELETE("4");

    final String code;

    AccessTypes(String code) {
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
    public static AccessTypes codeOf(String code) {
        for (AccessTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }
}
