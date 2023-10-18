package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuthTemp extends BasePersistable{
    @Column(length = Columns.certKey, nullable = false)
    private String certKey;
    @Column(length = Columns.hospitalCd, nullable = false)
    private String hospitalCd;
    @Column(length = Columns.patientNo, nullable = false)
    private String patientNo;
    @Column(length = Columns.ci, nullable = true)
    private String myCi;
    @Column(length = Columns.userNm, nullable = true)
    private String userNm;
    /** 생성일시*/
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;
}
