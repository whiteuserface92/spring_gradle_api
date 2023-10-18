package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.ActionTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseAdminPrivacyAccess extends BasePersistable {

    @Column(length = Columns.userNm, nullable = false)
    private String userAccnt;

    @Column(length = Columns.hospitalCd)
    private String hospitalCd;

    @Column(length = Columns.adminService, nullable = false)
    private String service;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionTypes action;

    @Column(length = Columns.adminDescription, nullable = false)
    private String description;

    @Column(length = Columns.adminRequest)
    private String request;

    @Column(length = Columns.adminResult)
    private String result;

    @Column(length = Columns.userIp, nullable = false)
    private String ip;

    /** 생성일 */
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ymd =  LocalDate.now();

    /** 생성일시 */
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdOn =  LocalDateTime.now();
}