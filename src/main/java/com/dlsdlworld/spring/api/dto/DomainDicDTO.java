package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainDicDTO implements Serializable {

    private Number id;
    private String domainnm;
    private String domainver;
    private String dataformat;
    private String domaindesc;

    private String memo;
    private String hashtag;
    private Number compid;
    private Number createdby;
    @Basic
    private java.sql.Timestamp createdon;

    private Number updatedby;
    @Basic
    private java.sql.Timestamp updatedon;


}
