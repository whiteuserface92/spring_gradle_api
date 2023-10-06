package com.dlsdlworld.spring.api.types;

/**
 */
public enum SiteTypes {

    /**
     * 청구시작
     */
    INSURANCE_COMPANY("10"),
    /**
     * 청구완료
     */
    HOSPITAL("20"),
    /**
     * 블록체인 등록
     */
    BC("30"),

    /**
     * 즐겨찾기 병원
     */
    FAVORITE_HOSPITAL("1"),

    /**
     * 즐겨찾기 보험사
     */
    FAVORITE_INSURANCE("2"),

    /**
     * 즐겨찾기 보험사
     */
    FAVORITE_BANK("3");

    final String code;

    SiteTypes(String code) {
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
    public static SiteTypes codeOf(String code) {
        for (SiteTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of siteType: " + code);
    }
}
