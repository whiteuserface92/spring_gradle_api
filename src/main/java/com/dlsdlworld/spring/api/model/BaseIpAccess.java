package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/05
 * Time : 10:39 오전
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
