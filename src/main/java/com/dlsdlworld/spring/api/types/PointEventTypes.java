package com.dlsdlworld.spring.api.types;

/**
 * 제증명 상품 상태 정보
 */
public enum PointEventTypes {
    JOIN("JOIN") ,
    SALE("SALE") ,
    REVIEW("REVIEW");

    private final String code;
    PointEventTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static PointEventTypes codeOf(String code) {
        for (PointEventTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}