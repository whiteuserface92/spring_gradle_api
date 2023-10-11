package com.dlsdlworld.spring.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 화면단에 내려준 HospitalOptionCode의 DTO
 * woong.jang
 */
@Data
public class HoOpCdDto implements Serializable {
    /***
     * Option Code
     */
    private String opCd;
    /**
     * Option Value
     */
    private String opVal;

}
