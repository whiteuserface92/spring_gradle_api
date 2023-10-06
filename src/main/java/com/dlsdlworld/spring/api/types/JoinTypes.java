package com.dlsdlworld.spring.api.types;

/**

 */
public enum JoinTypes {

    /** 본인인증 */
    ONLINE("10"),

    /** 창구인증 */
    OFFLINE("20"),

    /** 대리인인증 */
    REPRESENTATIVE("30"),

    /**  */
    INTERFACE("40");

    final String code;

    JoinTypes(String code) {
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
    public static JoinTypes codeOf(String code) {
        for (JoinTypes p : values()) {
            if (p.code.equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of joinType: " + code);
    }
}
