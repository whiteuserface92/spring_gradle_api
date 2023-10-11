package com.dlsdlworld.spring.api.projection;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/02/01
 * Time : 18:39
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
