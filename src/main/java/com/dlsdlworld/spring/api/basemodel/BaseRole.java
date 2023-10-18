package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.basemodel.Columns;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 열할정의
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseRole extends BaseCreatable {

    /**
     * 역할명
     */
    @Column(length = Columns.roleNm, nullable = false)
    private String roleNm;

    /**
     * 설명
     */
    @Column(length = Columns.roleDesc)
    private String roleDesc;
}
