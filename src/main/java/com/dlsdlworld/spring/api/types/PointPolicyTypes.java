package com.dlsdlworld.spring.api.types;

/**
 * 제증명 상품 상태 정보
 */
public enum PointPolicyTypes {
    PERCENT("PERCENT") ,
    POINT("POINT");

    private final String code;
    PointPolicyTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static PointPolicyTypes codeOf(String code) {
        for (PointPolicyTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}