package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 8:11 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseOrderDetail extends BasePersistable {

    /**
     * 상품코드를 별도로 관리하여 사용할 경우에 입력(제증명에서 서식코드로 사용)
     */
    @Column(length = Columns.goodsCd)
    private String goodsCd;

    /**
     * 주문당시 상품명(상품마스타에서 상품명이 바뀔수 있으므로)
     */
    @Column(length = Columns.goodsNm, nullable = false)
    private String goodsNm;

    /**
     * 단가(부가세포함)
     */
    @Column(nullable = false)
    private Integer unitPrice;

    /**
     * 부가세
     */
    @Column(nullable = false)
    private Integer unitVat;

    /**
     * 상품정보의 수수료정책에 따른 수수료(VAT별도)
     */
    @Column
    private Integer fee;

    /**
     * 수수료부가세
     */
    @Column
    private Integer feeVat;

    /**
     * 주문수량
     */
    @Column(nullable = false)
    private Short orderCnt;

    /**
     * 적립마일리지
     */
    @Column
    private Integer saveMileage;
}
