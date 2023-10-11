package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 가상키관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuVirtualKey extends BasePersistable {

    /**
     * 가상키보드 암복호화에 사용되는 key 값
     */
    @Column(length = Columns.key, nullable = false)
    private String key;
}
