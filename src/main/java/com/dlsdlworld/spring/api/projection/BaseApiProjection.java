package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.types.ApiBodyTypes;
import com.dlsdlworld.spring.api.types.ApiMethodTypes;

/**
 */
public interface BaseApiProjection extends BaseModifiableProjection {

    String getApiNm();

    String getApiVer();

    ApiMethodTypes getMethodType();

    String getRestUrl();

    String getApiDesc();

    String getServiceUrl();

    String getExampleUrl();

    ApiBodyTypes getReqBodyType();

    ApiBodyTypes getResBodyType();

}
