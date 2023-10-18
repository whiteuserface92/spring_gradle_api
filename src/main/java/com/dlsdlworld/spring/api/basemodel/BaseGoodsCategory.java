//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * 상품분류
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseGoodsCategory extends BaseModifiable {
//
//    /**
//     * 분류명
//     */
//    @Column(length = Columns.clsNm, nullable = false)
//    private String clsNm;
//
//    /**
//     * 노드레벨
//     */
//    @Column(nullable = false)
//    private Short level;
//
//    /**
//     * 자체분류코드(제증명사례처럼 자체코드를 정의하여 참조용으로 사용할 경우)
//     */
//    @Column(length = Columns.clsCd)
//    private String clsCd;
//
//    /**
//     * 표시순서
//     */
//    @Column(nullable = false, length = Columns.dispOrd)
//    private String dispOrd;
//
//
//}
