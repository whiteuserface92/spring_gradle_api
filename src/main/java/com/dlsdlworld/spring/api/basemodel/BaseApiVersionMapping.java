//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//
///**
// * API버전매핑
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseApiVersionMapping extends BasePersistable {
//
//    /**
//     * 서비스url
//     */
//    @Column(length = Columns.serviceUrl, nullable = false)
//    private String serviceUrl;
//
//    /**
//     * 표준버전id
//     */
//    @Column(nullable = false)
//    private Long stdVersionId;
//
//    /**
//     * 표준url
//     */
//    @Column(length = Columns.stdUrl, nullable = false)
//    private String stdUrl;
//
//}
