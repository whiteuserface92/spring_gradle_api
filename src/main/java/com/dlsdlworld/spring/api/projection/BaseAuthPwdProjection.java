package com.dlsdlworld.spring.api.projection;

import java.time.LocalDateTime;

/**

 */
public interface BaseAuthPwdProjection {

    Long getId();

    String getUserAccnt();

    String getPwd();

    Integer getLoginRetries();

    String getHashSalt();

    String getHashAlgo();

    LocalDateTime getPwdChangedOn();
}
