package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 보험용직업코드
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCodeJob extends BaseModifiable {

    /**
     * 보험사코드
     */
    @Column(nullable = false, length = Columns.insuCompCd)
    private String insuCompCd;

    /**
     * 직업코드
     */
    @Column(nullable = false, length = Columns.jobCd)
    private String jobCd;

    /**
     * 직업코드명
     */
    @Column(nullable = false, length = Columns.jobCdNm)
    private String jobCdNm;

    /**
     * 레벨
     */
    @Column(nullable = false)
    private Short level;

}
