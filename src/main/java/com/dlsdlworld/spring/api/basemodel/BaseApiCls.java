//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * API 업무분류
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseApiCls extends BaseModifiable {
//
//    /**
//     * 분류코드(service url을 생성하는 경로 참고용)
//     */
//    @Column(length = Columns.clsCd, nullable = false)
//    private String clsCd;
//
//    /**
//     * 분류명
//     */
//    @Column(length = Columns.clsNm, nullable = false)
//    private String clsNm;
//
//    /**
//     * 분류설명
//     */
//    @Column(length = Columns.clsDesc)
//    private String clsDesc;
//
//    /**
//     * 레벨
//     */
//    @Column(nullable = false)
//    private Short level;
//
//    /**
//     * 표시순서
//     */
//    @Column(length = Columns.dispOrd, nullable = false)
//    private String dispOrd;
//}
