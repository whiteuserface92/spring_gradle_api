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
public abstract class BaseAdminAccessHistory extends BasePersistable {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionTypes action;

    @Column(length = Columns.adminDescription, nullable = false)
    private String description;

    @Column(length = Columns.hospitalCd)
    private String hospitalCd;

    @Column(length = Columns.userIp, nullable = false)
    private String ip;

    @Column(length = Columns.userNm, nullable = false)
    private String userAccnt;

    private Long targetUserId;

    /** 생성일 */
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ymd =  LocalDate.now();

    /** 생성일시 */
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdOn =  LocalDateTime.now();
}
