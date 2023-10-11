package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseInsuExecLog extends BasePersistable {

    /**
     * 기기식별번호
     */
    @Column(length = Columns.uuid, nullable = false)
    private String uuid;

    /**
     * 생성일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * 화면단위의 진행처리 단계
     */
    @Column(length = Columns.execStep, nullable = false)
    private String execStep;

    /**
     * 화면단위의 상세처리단계명
     */
    @Column(length = Columns.execDtlStep)
    private String execDtlStep;

    /**
     * 환자번호
     */
    @Column(length = Columns.patientNo)
    private String patientNo;

    /**
     * 레몬에서 부여한 보험사 코드(영문대문자로 구성)
     */
    @Column(length = Columns.insuCompCd)
    private String insuCompCd;

    /**
     * H:병원, I:보험사, R:레몬내부, N:info
     */
    @Column(length = Columns.sectionCd)
    private String sectionCd;

    /**
     * 응답코드
     */
    @Column(length = Columns.replyCd)
    private String replyCd;

    /**
     * 응답메시지
     */
    @Lob
    private String replyMsg;

    /**
     * 주간집계시 사용되는 짧은 오류메시지
     */
    @Column(length = Columns.aggrMsg)
    private String aggrMsg;

    /**
     * 병원코드(과거 데이터 보관용)
     */
    @Column(length = Columns.hospitalCd)
    private String hospitalCd;

    /**
     * 예비용1
     */
    @Column(length = Columns.etc)
    private String etc1;

    /**
     * 예비용2
     */
    @Column(length = Columns.etc)
    private String etc2;

    /**
     * 예비용3
     */
    @Column(length = Columns.etc)
    private String etc3;

    /**
     * 예비용4
     */
    @Column(length = Columns.etc)
    private String etc4;

    /**
     * 예비용5
     */
    @Column(length = Columns.etc)
    private String etc5;
}
