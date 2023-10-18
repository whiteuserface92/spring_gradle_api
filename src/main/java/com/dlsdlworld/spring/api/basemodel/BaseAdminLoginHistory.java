package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.YnTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseAdminLoginHistory extends BasePersistable {

    @Column(length = Columns.userNm, nullable = false)
    private String userAccnt;

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.yN, nullable = false)
    private YnTypes success;

    @Column(length = Columns.adminDetail)
    private String details;

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