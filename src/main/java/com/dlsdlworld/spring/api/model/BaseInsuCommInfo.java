package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * 보험사수수료기준
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuCommInfo extends BaseCreatable {

    /**
     * 적용시작일
     */
    @Column(nullable = false)
    private LocalDate startedOn;

    /**
     * 적용종료일
     */
    @Column(nullable = false)
    private LocalDate endedOn;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 입원적용수수료
     */
    @Column(nullable = false)
    private Integer enterFee;

    /**
     * 외래적용수수료
     */
    @Column(nullable = false)
    private Integer forignFee;

    /**
     * 입원적용수수료
     */
    @Column(nullable = false)
    private Integer enterUnitPrice;

    /**
     * 외래적용수수료
     */
    @Column(nullable = false)
    private Integer forignUnitPrice;

    /**
     * 변경사유
     */
    @Column(length = Columns.rem)
    private String rem;
}
