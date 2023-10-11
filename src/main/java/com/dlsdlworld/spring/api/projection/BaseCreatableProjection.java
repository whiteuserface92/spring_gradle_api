package com.dlsdlworld.spring.api.projection;

import java.time.LocalDateTime;

/**
 */
public interface BaseCreatableProjection {

    Long getId();

    LocalDateTime getCreatedOn();

    Long getCreatedBy();

    String getCreatedByName();
}
