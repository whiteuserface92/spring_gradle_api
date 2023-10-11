package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.List;

/**
 * 그룹정보
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 12:45 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGroup extends BaseModifiable {

    /**
     * 그룹명
     */
    @Column(length = Columns.groupNm, nullable = false)
    private String groupNm;

    /**
     * 표시순서
     */
    @Column(nullable = false)
    private String dispOrd;

    /**
     * 설명
     */
    @Column(length = Columns.groupDesc, nullable = false)
    private String groupDesc;

    /**
     * 그룹명 다국어코드
     */
    @Column(length = Columns.nameCd, nullable = false)
    private String nameCd;

    /**
     * 서비스주소
     */
    @Column(length = Columns.serviceUrl)
    private String serviceUrl;

    /**
     * 그룹명 다국어 전체 코드
     */
    @Transient
    private List<? extends BaseMessage> nameCds;
}
