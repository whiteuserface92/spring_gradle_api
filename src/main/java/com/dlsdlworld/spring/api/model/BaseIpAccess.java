package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseIpAccess extends BaseModifiable {

    /**
     * ip주소
     */
    @Column(length = Columns.serviceIp)
    private String ipAddr;
}
