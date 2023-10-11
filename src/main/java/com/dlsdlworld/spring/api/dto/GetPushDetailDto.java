package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPushDetailDto {

    private String status;

    private String receiveId;

    private Date sendedOn;

    private Date readOn;

    private int pushRequestId;

    private String contents;

    private String sendId;

    private int userId;
}
