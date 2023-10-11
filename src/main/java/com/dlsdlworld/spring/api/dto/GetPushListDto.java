package com.dlsdlworld.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPushListDto {

    private String contents;


    private int pushRequestId;


    private Date readOn;


    private Date sendedOn;


    private int userId;
}
