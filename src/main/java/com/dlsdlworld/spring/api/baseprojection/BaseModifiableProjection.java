package com.dlsdlworld.spring.api.baseprojection;

import java.time.LocalDateTime;

/**

 */
public interface BaseModifiableProjection extends BaseCreatableProjection {

    LocalDateTime getUpdatedOn();

    Long getUpdatedBy();

    String getUpdatedByName();
}
