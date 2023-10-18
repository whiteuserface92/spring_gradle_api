//package com.dlsdlworld.spring.api.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
///**
// */
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseInsuBcLog implements Serializable {
//
//    /**
//     * KT BaaS에 저장할 블록체인 키
//     * 병원코드+환자번호를 이용하여 생성
//     * 3.0병원도 저장하기 위해서 user_key에서 변경
//     */
//    @Id
//    @Column(length = Columns.bcKey, nullable = false)
//    private String bcKey;
//
//    /**
//     * 블록체인키 데이터를 저장하는 단위그룹의 id
//     */
//    @Column(length = Columns.bcGroupId, nullable = false)
//    private String bcGroupId;
//
//    /**
//     * 고객청구번호(환자번호)
//     */
//    @Column(length = Columns.patientNo, nullable = false)
//    private String patientNo;
//
//    /**
//     * KT BaaS에 저장할 블록체인 키(CI를 이용하여 생성)
//     * 3.0병원은 CI값이 없어 저장이 안되서 bc_key를 생성
//     */
//    @Column(length = Columns.userKey)
//    private String userKey;
//
//    /**
//     * 사용자키 데이터를 저장하는 단위그룹의 id
//     */
//    @Column(length = Columns.userGroupId)
//    private String userGroupId;
//
//    /**
//     * 기록일시
//     */
//    private LocalDateTime createdOn;
//
//    /**
//     * 병원코드(과거 데이터 보관용)
//     */
//    @Column(length = Columns.hospitalCd)
//    private String hospitalCd;
//}
