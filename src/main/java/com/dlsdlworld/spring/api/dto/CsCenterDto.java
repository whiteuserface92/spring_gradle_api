package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;

/**
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsCenterDto {


    private String hospitalCd;
    private String hospitalNm;
    private String patientNm;
    private String patientId;
    private String phoneNo;
    private Number hospitalId;
    private Number userId;
    private boolean enabled;
    private String userNm;
    private String birthDay;
    private String sex;
    private String email;
    private String countryCd;
    private String joinType;
    @Basic
    private java.sql.Timestamp createdOn;


}
