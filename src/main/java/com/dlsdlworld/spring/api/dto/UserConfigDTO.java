/*
 * Copyright (c) 2022.
 * LemonHealthCare  Plus Team
 * Woong Jang.
 */

package com.dlsdlworld.spring.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class UserConfigDTO {

    Long id;
    private String deptCd;

    /**
     * 언어설정
     */
    private String langCd;
    /**
     *
     */
    private String pushOnOff;

    private String alarmOffStarttime;

    private String alarmOffEndtime;

    /**
     * session Time Out 분으로 저장
     */
    private String sessTimeoutMm;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
