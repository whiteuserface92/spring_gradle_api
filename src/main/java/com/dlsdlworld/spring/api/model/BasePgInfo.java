package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * PG설정
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePgInfo extends BasePersistable {

    /**
     * PG사코드
     */
    @Column(nullable = false, length = Columns.pgCompCd)
    private String pgCompCd;

    /**
     * 가맹점Key
     */
    @Column(nullable = false, length = Columns.storeKey)
    private String storeKey;
}
