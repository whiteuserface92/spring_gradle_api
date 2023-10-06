package com.dlsdlworld.spring.api.types;

/**
 */
public enum PermissionTypes {

    ALL(ResourceTypes.ALL.getCode())

    ,CODE_READ(ResourceTypes.CODE.getCode() + AccessTypes.READ.getCode())
    ,CODE_DOWNLOAD(ResourceTypes.CODE.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,CODE_WRITE(ResourceTypes.CODE.getCode() + AccessTypes.WRITE.getCode())
    ,CODE_DELETE(ResourceTypes.CODE.getCode() + AccessTypes.DELETE.getCode())

    ,GROUP_READ(ResourceTypes.GROUP.getCode() + AccessTypes.READ.getCode())
    ,GROUP_DOWNLOAD(ResourceTypes.GROUP.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,GROUP_WRITE(ResourceTypes.GROUP.getCode() + AccessTypes.WRITE.getCode())
    ,GROUP_DELETE(ResourceTypes.GROUP.getCode() + AccessTypes.DELETE.getCode())

    ,HOSPITAL_READ(ResourceTypes.HOSPITAL.getCode() + AccessTypes.READ.getCode())
    ,HOSPITAL_DOWNLOAD(ResourceTypes.HOSPITAL.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,HOSPITAL_WRITE(ResourceTypes.HOSPITAL.getCode() + AccessTypes.WRITE.getCode())
    ,HOSPITAL_DELETE(ResourceTypes.HOSPITAL.getCode() + AccessTypes.DELETE.getCode())

    ,APP_READ(ResourceTypes.APP.getCode() + AccessTypes.READ.getCode())
    ,APP_DOWNLOAD(ResourceTypes.APP.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,APP_WRITE(ResourceTypes.APP.getCode() + AccessTypes.WRITE.getCode())
    ,APP_DELETE(ResourceTypes.APP.getCode() + AccessTypes.DELETE.getCode())

//    ,APP_DEVICE_READ(ResourceTypes.APP_DEVICE.code + AccessTypes.READ.code)

    ,PUSH_READ(ResourceTypes.PUSH.getCode() + AccessTypes.READ.getCode())
    ,PUSH_DOWNLOAD(ResourceTypes.PUSH.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,PUSH_WRITE(ResourceTypes.PUSH.getCode() + AccessTypes.WRITE.getCode())
    ,PUSH_DELETE(ResourceTypes.PUSH.getCode() + AccessTypes.DELETE.getCode())

    ,MESSAGE_READ(ResourceTypes.MESSAGE.getCode() + AccessTypes.READ.getCode())
    ,MESSAGE_DOWNLOAD(ResourceTypes.MESSAGE.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,MESSAGE_WRITE(ResourceTypes.MESSAGE.getCode() + AccessTypes.WRITE.getCode())
    ,MESSAGE_DELETE(ResourceTypes.MESSAGE.getCode() + AccessTypes.DELETE.getCode())

    ,MENU_READ(ResourceTypes.MENU.getCode() + AccessTypes.READ.getCode())
    ,MENU_DOWNLOAD(ResourceTypes.MENU.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,MENU_WRITE(ResourceTypes.MENU.getCode() + AccessTypes.WRITE.getCode())
    ,MENU_DELETE(ResourceTypes.MENU.getCode() + AccessTypes.DELETE.getCode())

    ,API_READ(ResourceTypes.API.getCode() + AccessTypes.READ.getCode())
    ,API_DOWNLOAD(ResourceTypes.API.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,API_WRITE(ResourceTypes.API.getCode() + AccessTypes.WRITE.getCode())
    ,API_DELETE(ResourceTypes.API.getCode() + AccessTypes.DELETE.getCode())

    ,API_DATASOURCE_READ(ResourceTypes.API_DATASOURCE.getCode() + AccessTypes.READ.getCode())
    ,API_DATASOURCE_DOWNLOAD(ResourceTypes.API_DATASOURCE.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,API_DATASOURCE_WRITE(ResourceTypes.API_DATASOURCE.getCode() + AccessTypes.WRITE.getCode())
    ,API_DATASOURCE_DELETE(ResourceTypes.API_DATASOURCE.getCode() + AccessTypes.DELETE.getCode())

    ,API_QUERY_READ(ResourceTypes.API_QUERY.getCode() + AccessTypes.READ.getCode())
    ,API_QUERY_DOWNLOAD(ResourceTypes.API_QUERY.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,API_QUERY_WRITE(ResourceTypes.API_QUERY.getCode() + AccessTypes.WRITE.getCode())
    ,API_QUERY_DELETE(ResourceTypes.API_QUERY.getCode() + AccessTypes.DELETE.getCode())

    ,TERMS_READ(ResourceTypes.TERMS.getCode() + AccessTypes.READ.getCode())
    ,TERMS_DOWNLOAD(ResourceTypes.TERMS.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,TERMS_WRITE(ResourceTypes.TERMS.getCode() + AccessTypes.WRITE.getCode())
    ,TERMS_DELETE(ResourceTypes.TERMS.getCode() + AccessTypes.DELETE.getCode())

    ,USER_READ(ResourceTypes.USER.getCode() + AccessTypes.READ.getCode())
    ,USER_DOWNLOAD(ResourceTypes.USER.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,USER_WRITE(ResourceTypes.USER.getCode() + AccessTypes.WRITE.getCode())
    ,USER_DELETE(ResourceTypes.USER.getCode() + AccessTypes.DELETE.getCode())

    ,AUTHORITY_READ(ResourceTypes.AUTHORITY.getCode() + AccessTypes.READ.getCode())
    ,AUTHORITY_DOWNLOAD(ResourceTypes.AUTHORITY.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,AUTHORITY_WRITE(ResourceTypes.AUTHORITY.getCode() + AccessTypes.WRITE.getCode())
    ,AUTHORITY_DELETE(ResourceTypes.AUTHORITY.getCode() + AccessTypes.DELETE.getCode())

    ,PAYMENT_READ(ResourceTypes.PAYMENT.getCode() + AccessTypes.READ.getCode())
    ,PAYMENT_DOWNLOAD(ResourceTypes.PAYMENT.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,PAYMENT_WRITE(ResourceTypes.PAYMENT.getCode() + AccessTypes.WRITE.getCode())
    ,PAYMENT_DELETE(ResourceTypes.PAYMENT.getCode() + AccessTypes.DELETE.getCode())

    ,INSU_READ(ResourceTypes.INSU.getCode() + AccessTypes.READ.getCode())
    ,INSU_DOWNLOAD(ResourceTypes.INSU.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,INSU_WRITE(ResourceTypes.INSU.getCode() + AccessTypes.WRITE.getCode())
    ,INSU_DELETE(ResourceTypes.INSU.getCode() + AccessTypes.DELETE.getCode())

    ,CERT_READ(ResourceTypes.CERT.getCode() + AccessTypes.READ.getCode())
    ,CERT_DOWNLOAD(ResourceTypes.CERT.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,CERT_WRITE(ResourceTypes.CERT.getCode() + AccessTypes.WRITE.getCode())
    ,CERT_DELETE(ResourceTypes.CERT.getCode() + AccessTypes.DELETE.getCode())

    ,RECEIPT_READ(ResourceTypes.RECEIPT.getCode() + AccessTypes.READ.getCode())
    ,RECEIPT_DOWNLOAD(ResourceTypes.RECEIPT.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,RECEIPT_WRITE(ResourceTypes.RECEIPT.getCode() + AccessTypes.WRITE.getCode())
    ,RECEIPT_DELETE(ResourceTypes.RECEIPT.getCode() + AccessTypes.DELETE.getCode())

    ,LOG_READ(ResourceTypes.LOG.getCode() + AccessTypes.READ.getCode())
    ,LOG_DOWNLOAD(ResourceTypes.LOG.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,LOG_WRITE(ResourceTypes.LOG.getCode() + AccessTypes.WRITE.getCode())
    ,LOG_DELETE(ResourceTypes.LOG.getCode() + AccessTypes.DELETE.getCode())

    ,GOODS_READ(ResourceTypes.GOODS.getCode() + AccessTypes.READ.getCode())
    ,GOODS_DOWNLOAD(ResourceTypes.GOODS.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,GOODS_WRITE(ResourceTypes.GOODS.getCode() + AccessTypes.WRITE.getCode())
    ,GOODS_DELETE(ResourceTypes.GOODS.getCode() + AccessTypes.DELETE.getCode())

    ,ORDER_READ(ResourceTypes.ORDER.getCode() + AccessTypes.READ.getCode())
    ,ORDER_DOWNLOAD(ResourceTypes.ORDER.getCode() + AccessTypes.DOWNLOAD.getCode())
    ,ORDER_WRITE(ResourceTypes.ORDER.getCode() + AccessTypes.WRITE.getCode())
    ,ORDER_DELETE(ResourceTypes.ORDER.getCode() + AccessTypes.DELETE.getCode())

    ;

    final String code;

    PermissionTypes(String code) {
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
    public static PermissionTypes codeOf(String code) {
        for (PermissionTypes p : values()) {
            if (p.name().equals(code)) {
                return p;
            }
        }

        throw new IllegalArgumentException("Invalid code of Permission: " + code);
    }
}
