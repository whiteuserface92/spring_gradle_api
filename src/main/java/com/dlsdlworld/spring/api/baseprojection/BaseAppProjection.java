package com.dlsdlworld.spring.api.baseprojection;

/**

 */
public interface BaseAppProjection extends BaseModifiableProjection {

    String getPlatformType();

    String getAppNm();

    String getAppVer();

    String getDeployType();

    String getFcmKey();

    String getHashKey();

    String getPkgNm();

    String getStoreUrl();

    String getFidoApiKey();

    Boolean getMultiDeviceEnabled();

    String getAppState();
}
