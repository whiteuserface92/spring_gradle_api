package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * 실손보험 병원
 */
@Getter
@Setter
@MappedSuperclass
@AttributeOverride(name = "id", column = @Column(name = "hospital_id"))
public abstract class BaseHospitalInsu extends BaseModifiable {

    /**
     * 병원표시명
     */
    @Column(length = Columns.hosptialDispNm, nullable = false)
    private String hospitalDispNm;

    /**
     * 표시순서
     */
    @Column(length = Columns.dispOrd, nullable = false)
    private String dispOrd;

    /**
     * 실손제공형태 공통코드(1:입원+통원 전체, 2:입원, 3:통원)
     */
    @Column(length = Columns.insuSvcType, nullable = false)
    private String insuSvcType;

    /**
     * 입원서비스 시작일
     */
    private LocalDate inpatSvcStartedOn;

    /**
     * 통원서비스 시작일
     */
    private LocalDate outpatSvcStartedOn;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

}
