package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserStaff extends BasePersistable {

    /**
     * 직책
     */
    @Column(length = Columns.dutyNm, nullable = false)
    private String dutyNm;

    /**
     * 인사번호
     */
    @Column(length = Columns.empNo, nullable = false)
    private String empNo;

    /**
     * 부서코드
     */
    @Column(length = Columns.deptCd, nullable = false)
    private String deptCd;

    /**
     * 부서명
     */
    @Column(length = Columns.deptNm, nullable = false)
    private String deptNm;

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 병원코드
     */
    @Column(length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;

    /** 생성일시*/
    // 없어서 추가했습니다. (최대길 : 2020-04-25)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    /**
     * 수정일시
     */
    @Column
    private LocalDateTime updatedOn;
}
