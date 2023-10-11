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
public abstract class BaseCodeBank extends BasePersistable {

    /**
     * 금융기관 공통코드
     */
    @Column(length = Columns.bankCd, nullable = false)
    private String bankCd;

    /**
     * 은행명
     */
    @Column(length = Columns.bankNm, nullable = false)
    private String bankNm;

    /**
     * 은행표시명
     */
    @Column(length = Columns.bankDispNm, nullable = false)
    private String bankDispNm;

    /**
     * 은행:BANK, 증권:STOCK
     * 값을 직접입력하여 관리
     */
    @Column(length = Columns.bankType, nullable = false)
    private String bankType;
}
