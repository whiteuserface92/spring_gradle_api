package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : sungjin.park
 * Date : 2020/05/21
 * Time : 3:23 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseCertDemoUsers implements Serializable {

    /**
     * 병원ID
     */
    @Id
    @Column(nullable = false)
    private Long hospitalId;

    /**
     * 은행명
     */
    @Id
    @Column(nullable = false)
    private String patientNo;

    /**
     * 제증명서식코드
     */
    @Id
    @Column(nullable = false)
    private String certCd;

    /**
     * 데모타입
     */
    @Id
    @Column(nullable = false)
    private String demoType;

    /**
     * 대리환자번호
     */
    @Column(nullable = false)
    private String actingPatientNo;

    /**
     * 사용구분
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 입력일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일시
     */
    @Column
    private LocalDateTime updatedOn;
    
}
