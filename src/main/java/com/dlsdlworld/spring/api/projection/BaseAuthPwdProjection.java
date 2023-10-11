package com.dlsdlworld.spring.api.projection;

import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon
 * Date : 2020/04/16
 * Time : 12: 26
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
