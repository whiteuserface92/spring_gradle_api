package com.dlsdlworld.spring.api.types;

/**
 * 상품 정보
 */
public enum GoodsTypes {
    ProductDistribution("1") ,
    ContentsProvider("2") ,
    CertificateProvider("3"),
    InsuranceClaimFee("4");

    private final String code;
    GoodsTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static GoodsTypes codeOf(String code) {
        for (GoodsTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}