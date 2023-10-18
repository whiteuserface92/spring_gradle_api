//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * 화면제어설정
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseUiControlDef extends BaseModifiable {
//
//    /**
//     * 제품코드
//     */
//    @Column(length = Columns.prodCd, nullable = false)
//    private String prodCd;
//
//    /**
//     * 기관구분
//     */
//    @Column(length = Columns.compType, nullable = false)
//    private String compType;
//
//    /**
//     * 기관코드
//     */
//    @Column(length = Columns.compCd, nullable = false)
//    private String compCd;
//
//    /**
//     * 화면제어코드
//     */
//    @Column(length = Columns.ctrlKey, nullable = false)
//    private String ctrlKey;
//
//    /**
//     * 화면제어값
//     */
//    @Column(length = Columns.ctrlVal, nullable = false)
//    private String ctrlVal;
//
//}
