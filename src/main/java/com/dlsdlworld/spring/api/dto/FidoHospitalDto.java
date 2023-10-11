package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FidoHospitalDto {
    private Number id;
    private String tenantCd;
    private String apiKey;
    private Number hospitalId;
    private String hospitalNm;
    private String hospitalCd;
    private String hospitalNmCd;

    private Number createdBy;
    @Basic
    private java.sql.Timestamp createdOn;
    private Number updatedBy;
    @Basic
    private java.sql.Timestamp updatedOn;




}
