package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.types.DataTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * 도메인사전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseDomainDic extends BaseModifiable {

    /**
     * 도메인명
     */
    @Column(length = Columns.domainNm, nullable = false)
    private String domainNm;

    /**
     * 데이터타입
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Columns.dataType, nullable = false)
    private DataTypes dataType;

    /**
     * 데이터포맷
     */
    @Column(length = Columns.dataFormat)
    private String dataFormat;

    /**
     * 도메인설명
     */
    @Column(length = Columns.domainDesc, nullable = false)
    private String domainDesc;

    /**
     * 관리메모
     */
    @Column(length = Columns.memo)
    private String memo;

    /**
     * 도메인검색을 위한 해시태그값
     */
    @Column(length = Columns.hashTag)
    private String hashTag;

    /**
     * 과거(3.0) 데이터 등을 구분하기 위해 참고값으로 등록
     */
    @Column(length = Columns.dockerver)
    private String domainVer;

}
