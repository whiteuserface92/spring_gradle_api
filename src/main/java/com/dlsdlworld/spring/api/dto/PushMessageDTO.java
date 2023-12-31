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
public class PushMessageDTO {

    private Number id;
    private String contents;
    private String fcmContents;

    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp createdOn;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp readOn;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp receivedOn;
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp sendedOn;

}
