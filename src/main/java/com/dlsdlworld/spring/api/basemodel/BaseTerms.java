package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.basemodel.Columns;
import com.dlsdlworld.spring.api.types.TermsType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 이용약관
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseTerms extends BaseModifiable {

    /**
     * 약관명
     */
    @Column(length = Columns.termsNm, nullable = false)
    private String termsNm;

    /**
     * 약관코드
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.termsCd, nullable = false)
    private TermsType termsCd;

    /**
     * 소유자코드
     */
    @Column(length = Columns.termsOwnerCd, nullable = false)
    private String termsOwnerCd;

    /**
     * 버전
     */
    @Column(length = Columns.termsVer, nullable = false)
    private String termsVer;

    /**
     * 내용
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String termsDesc;

    /**
     * 필수여부
     */
    @Column(nullable = false)
    private Boolean required;

    /**
     * 효력시작일시
     */
    @Column(nullable = false)
    private LocalDateTime startedOn;

    /**
     * 효력종료일시
     */
    @Column(nullable = false)
    private LocalDateTime endedOn;

    /**
     * 표시순서 (최대길 : 2020-05-02)
     */
    @Column(nullable = false)
    private String dispOrd;
}
