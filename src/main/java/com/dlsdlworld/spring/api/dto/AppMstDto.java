package com.dlsdlworld.spring.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;

@Getter
@Setter
public class AppMstDto {

    private Number appId;
    private String appNm;
    private String appState;
    private String appVer;
    private boolean multiEnabled;
    private Number hospitalId;
    private String fcmKey;
    private String fidoApiKey;
    private String hospitalNm;
    private String hospitalCd;
    private String hospitalNmCd;
    private String prodCd;
    private Number appPlatformId;
    private String platformType;
    private String deployType;
    private String storeUrl;
    private String hashKey;
    private String pkgNm;
    private boolean iosProcessed;
    //private Number appVersionId;
    private String versionCd;
    //private String releaseNote;
    //private java.sql.Timestamp releasedOn;
    //private boolean required;

    private Number createdBy;
    @Basic
    private java.sql.Timestamp createdOn;
    private Number updatedBy;
    @Basic
    private java.sql.Timestamp updatedOn;

}
