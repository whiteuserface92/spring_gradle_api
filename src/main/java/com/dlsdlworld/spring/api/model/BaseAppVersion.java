package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 앱버전관리
 */
@Getter
@Setter
@MappedSuperclass
public class BaseAppVersion extends BaseModifiable {

    @Column(length = Columns.versionCd, nullable = false)
    private String versionCd;

    @Column(nullable = false)
    private String releaseNote;

    @Column(nullable = false)
    private LocalDateTime releasedOn;

    @Column(nullable = false)
    private Boolean required;


}
