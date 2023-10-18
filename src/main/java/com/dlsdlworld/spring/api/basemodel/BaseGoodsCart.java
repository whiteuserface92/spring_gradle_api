//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
///**
// * 장바구니
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseGoodsCart extends BasePersistable {
//
//    /**
//     * 자체상품코드
//     */
//    @Column(length = Columns.goodsCd, nullable = false)
//    private String goodsCd;
//
//    /**
//     * 수량
//     */
//    @Column(nullable = false)
//    private Integer cartCnt;
//
//    /**
//     * 생성일시
//     */
//    @Column(nullable = false)
//    private LocalDateTime createdOn;
//}
