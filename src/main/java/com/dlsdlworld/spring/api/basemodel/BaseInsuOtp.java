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
//public abstract class BaseInsuOtp implements Serializable {
//
//    /**
//     * 일회용암호
//     */
//    @Id
//    @Column(length = Columns.otpKey, nullable = false)
//    private String otpKey;
//
//    /**
//     * 환자번호
//     */
//    @Column(length = Columns.patientNo, nullable = false)
//    private String patientNo;
//
//    /**
//     * 유효기간
//     */
//    @Column(nullable = false)
//    private LocalDateTime expiredOn;
//}
