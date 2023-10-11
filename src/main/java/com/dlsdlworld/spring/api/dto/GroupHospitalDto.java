package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupHospitalDto {
    private Number id;
    private Number groupId;
    private Number hospitalId;
    private String hospitalNm;
    private String hospitalNmCd;
    private String ovrdImgUrl;
    private String dispOrd;

    private Number createdBy;
    @Basic
    private java.sql.Timestamp createdOn;
    private Number updatedBy;
    @Basic
    private java.sql.Timestamp updatedOn;
}
