package com.dlsdlworld.spring.api.basemodel;


import com.dlsdlworld.spring.api.converter.PlatformTypesConverter;
import com.dlsdlworld.spring.api.types.PlatformTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseAppMenuLog extends BasePersistable {

    @Column(length = Columns.userAgent, nullable = false)
    private String userAgent;

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.deviceType, nullable = false)
    @Convert(converter = PlatformTypesConverter.class)
    private PlatformTypes deviceType;

    @Column(length = Columns.userIp, nullable = false)
    private String userIp;

    @Column(nullable = false)
    private Long menuId;

    @Column(nullable = false)
    private Long hospitalMenuId;

    @Column(length = Columns.userAccnt, nullable = true)
    private String userAccnt;

    @Column(nullable = false)
    private Long appId;

    @Column(length = Columns.deviceUuid)
    private String deviceUuid;

    @Column(nullable = true)
    private Long userId;

    @Column(length = Columns.patientNo, nullable = true)
    private String patientNo;

    @Column(nullable = false)
    private Long hospitalId;

    @Column(length = Columns.hashKey, nullable = true)
    private String hashKey;

    @Column(length = Columns.errorCd, nullable = true)
    private String errorCd;

    @Column(nullable = true)
    private String errorMsg;

    @Column(nullable = false)
    private Short logType;

    @Column(length = Columns.transId, nullable = true)
    private String transId;

    @Column(length = Columns.destUrl, nullable = true)
    private String destUrl;

    /** 생성일시*/
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
