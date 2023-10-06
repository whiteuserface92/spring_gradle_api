package com.dlsdlworld.spring.api.types;

/**
 * 제증명 상품 상태 정보
 */
public enum CertDemoTypes {
    CertificateList("LIST") ,
    Certificate("CERTIFICATE") ,
    Payment("PAYMENT");

    private final String code;
    CertDemoTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static CertDemoTypes codeOf(String code) {
        for (CertDemoTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }


}