package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 상품마스타
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGoods extends BaseModifiable {

    /**
     * 발급수수료(증명)/컨텐츠제공(건강분석등)/상품판매 등
     * 상품의 종류에 따라 상품금액 구성이 달라진다.
     * 기초코드에 관리하며, 상품종류에 따라 관리할 속성이 많아지면 별도의 엔티티로 분리할것
     */
    @Column(length = Columns.goodsType, nullable = false)
    private String goodsType;

    /**
     * 상품명
     */
    @Column(length = Columns.goodsNm, nullable = false)
    private String goodsNm;

    /**
     * 상품코드를 별도로 관리하여 사용할 경우에 입력(제증명에서 서식코드로 사용)
     */
    @Column(length = Columns.goodsCd)
    private String goodsCd;

    /**
     * 상품마스터에서 상품을 삭제하지 않고 사용여부를 관리함
     * false인 경우 삭제된 상품으로 인지함
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 표시순서
     */
    @Column(length = Columns.dispOrd, nullable = false)
    private String dispOrd;

    /**
     * 상품목록에 노출여부를 결정함
     */
    @Column(nullable = false)
    private Boolean saleEnabled;

    /**
     * 판매시작일
     */
    @Column(nullable = false)
    private LocalDateTime saleStartedOn;

    /**
     * 판매종료일
     */
    @Column(nullable = false)
    private LocalDateTime saleEndedOn;

    /**
     * 환불가능여부
     */
    @Column(nullable = false)
    private Boolean refundEnabled;

    /**
     * 주문취소가능여부
     */
    @Column(nullable = false)
    private Boolean cancelEnabled;

    /**
     * 소비자가격(VAT포함)
     * 쇼핑몰에 표시할 소비자가격이며, 실제 판매금액은 아님
     */
    @Column(nullable = false)
    private Integer consumerPrice;

    /**
     * 실제 판매금액(VAT포함)
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * 과세여부(true:과세, false:면세)
     */
    @Column(nullable = false)
    private Boolean taxEnabled;

    /**
     * 과세율 퍼센트(기본 10.0%)
     */
    @Column(nullable = false)
    private Integer taxPercent;

    /**
     * 매입가(VAT포함)
     * 병원 증명서의 경우 판매가격과 매입가격이 같다.
     * 레몬은 대행수수료에 대한 매출이익을 가져간다.
     */
    @Column
    private Integer buyAmt;

    /**
     * 매입과세여부(true:과세, false:면세)
     */
    @Column(nullable = false)
    private Boolean buyTaxEnabled;

    /**
     * 매입과세율(기본 10.0%)
     */
    @Column
    private Integer buyTaxPercent;

    /**
     * 적립마일리지(상품,서비스 매출에 대한 마일리지 제공값)
     */
    @Column
    private Short giveMileage;

    /**
     * 상품에 대한 별점(1~5점)의 평균점수
     */
    @Column
    private BigDecimal gradeAvg;

}
