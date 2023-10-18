package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 병원정보관리
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospital extends BaseModifiable {

    /**
     * 사용여부
     */
    @Column(nullable = false)
    private Boolean enabled;

    /**
     * 병원코드
     */
    @Column(name = "hospital_cd", length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;

    /**
     * 병원주소메시지코드
     */
    @Column(length = Columns.hospitalAddrCd, nullable = false)
    private String hospitalAddrCd;

    /**
     * 병원명메시지코드
     */
    @Column(length = Columns.hospitalNmCd, nullable = false)
    private String hospitalNmCd;

    /**
     * 병원이름
     */
    @Column(length = Columns.hospitalNm)
    private String hospitalNm;

    /**
     * 병원전화번호메시지코드
     */
    @Column(length = Columns.hospitalTelnoCd, nullable = false)
    private String hospitalTelnoCd;

    /**
     * 병원종류
     */
    @Column(length = Columns.hospitalType, nullable = false)
    private String hospitalType;

    /**
     * 병원소재지코드
     */
    @Column(length = Columns.locCd, nullable = false)
    private String locCd;

    /**
     * 대표자명
     */
    @Column(length = Columns.repNm)
    private String repNm;

    /**
     * 대표전화번호
     */
    @Column(length = Columns.telNo)
    private String telNo;

    /**
     * 사업자번호
     */
    @Column(length = Columns.bizNo)
    private String bizNo;

    /**
     * 서비스 IP
     */
    @Column(length = Columns.serviceIp, nullable = false)
    private String serviceIp;

    /**
     * 서비스 URL
     */
    @Column(length = Columns.serviceUrl, nullable = false)
    private String domainUrl;

    /**
     * fido사용여부
     */
    @Column(nullable = false)
    private Boolean fidoEnabled;

    /**
     * api url
     */
    @Column(length = Columns.apiUrl, nullable = false)
    private String apiUrl;

    /**
     * 암호변경개월수
     */
    @Column(nullable = false)
    private Short maxPwdAge;

    /**
     * 휴면적용개월수
     */
    @Column(nullable = false)
    private Short maxDormantAge;

    /**
     * 가족등록허용여부
     */
    @Column(nullable = false)
    private Boolean familyEnabled;

    /**
     * 암호실패헝요횟수
     */
    @Column(nullable = false)
    private Short pwdRetryCnt;

    /**
     * 홈페이지 주소
     */
    @Column(length = Columns.homeUrl)
    private String homeUrl;

    /**
     * 병원소개내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String introContent;

    /**
     * 로고 url
     */
    @Column(length = Columns.logoUrl)
    private String logoUrl;

    /**
     * 캠페인 문구
     */
    @Column(length = Columns.campainTxt)
    private String campainTxt;

    /**
     * 서비스오픈일
     */
    @Column
    private LocalDateTime openedOn;

    /**
     * 일내원환자수
     */
    @Column
    private Short dailyVisitCnt;

    /**
     * 병상수
     */
    @Column
    private Short bedCnt;

    /**
     * 관리메모
     */
    @Column(length = Columns.memo)
    private String memo;

}

