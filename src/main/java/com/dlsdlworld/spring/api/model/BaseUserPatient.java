package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 병원별환자정보
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserPatient extends BaseModifiable {

    /**
     * 환자번호
     */
    @Column(length = Columns.patientNo, nullable = false)
    private String patientNo;

    /**
     * 환자관계코드
     */
    @Column(length = Columns.patientRel, nullable = false)
    private String patientRel;

    /**
     * 암호알고리즘
     */
    @Column(length = Columns.encAlgo, nullable = false)
    private String encAlgo;

    /**
     * 표시명
     */
    @Column(length = Columns.patientNm, nullable = false)
    private String patientNm;

    /**
     * 주민등록동의일시
     */
    @Column(nullable = false)
    private LocalDateTime agreedOn;
}
