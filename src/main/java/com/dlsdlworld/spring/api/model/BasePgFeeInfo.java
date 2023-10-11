package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * PG 수수료 관리
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 6:46 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BasePgFeeInfo extends BasePersistable {

    /**
     * 유효시작일
     */
    @Column(nullable = false)
    private LocalDate startedOn;

    /**
     * 유효종료일
     */
    @Column(nullable = false)
    private LocalDate endedOn;

    /**
     * 1:카드, 2:이체 등
     */
    @Column(nullable = false, length = Columns.pgFeeType)
    private String pgFeeType;

    /**
     * 수수료요율
     */
    @Column(nullable = false)
    private BigInteger pgFeeRate;
}
