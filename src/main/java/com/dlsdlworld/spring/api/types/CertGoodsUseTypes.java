package com.dlsdlworld.spring.api.types;

/**
 * 제증명 상품 상태 정보 email_link, email_attach, mfax, sns,download
 */
public enum CertGoodsUseTypes {
    EMAIL("0", "email") ,
    EMAILLINK("1", "email_link") ,
    EMAILATTACH("2", "email_attach") ,
    MobileFax("3", "mfax") ,
    SNSShare("4", "sns") ,
    Download("5", "download") ;

    private final String code;
    private final String name;

    CertGoodsUseTypes(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static CertGoodsUseTypes codeOf(String code) {
        for (CertGoodsUseTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid code of resourceType: " + code);
    }

}