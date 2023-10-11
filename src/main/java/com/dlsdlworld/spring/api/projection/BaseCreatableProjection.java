package com.dlsdlworld.spring.api.projection;

import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/02/04
 * Time : 12:23 오후
 */
public interface BaseCreatableProjection {

    Long getId();

    LocalDateTime getCreatedOn();

    Long getCreatedBy();

    String getCreatedByName();
}
