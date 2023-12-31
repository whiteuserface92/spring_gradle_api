package com.dlsdlworld.spring.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;

/**
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeDTO {

    private Number id;
    private String privilegeNm;
    private String privilegeDesc;
    private Number createdBy;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp createdOn;

}
