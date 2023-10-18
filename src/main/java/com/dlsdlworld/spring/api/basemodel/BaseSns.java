//package com.dlsdlworld.spring.api.model;
//
//import com.dlsdlworld.spring.api.types.SnsTypes;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * 공지사항
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseSns extends BaseModifiable {
//
//    /**
//     * 소셜종류
//     */
//    @Column(length = Columns.snsType)
//    private SnsTypes snsType;
//
//    /**
//     * 연동계정
//     */
//    @Column(length = Columns.snsAccnt)
//    private String snsAccnt;
//
//    /**
//     * 암호
//     */
//    @Column(length = Columns.snsPwd)
//    private String snsPwd;
//
//    /**
//     * 인증키
//     */
//    @Column(length = Columns.authKey)
//    private String authKey;
//}
