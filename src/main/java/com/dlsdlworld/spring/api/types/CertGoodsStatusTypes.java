package com.dlsdlworld.spring.api.types;

/**
 * 제증명 상품 상태 정보
 */
public enum CertGoodsStatusTypes {
    Ready("0") ,
    Publish("1") ,
    Paying("2") ,
    Paid("3") ,
    UseCompleted("4"),
    Cancelled("9"),
    PublishForInsu("21"),
    UsedForInsu("22"),
    PublishForHospital("31"),
    PublishForKakaoNotice("41"),
    Expired("98"),
    Deleted("99");

    private final String code;
    CertGoodsStatusTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static CertGoodsStatusTypes codeOf(String code) {
        for (CertGoodsStatusTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}