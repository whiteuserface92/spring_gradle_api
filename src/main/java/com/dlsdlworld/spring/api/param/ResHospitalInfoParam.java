package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/07
 * Time : 13:57 오후
 */

@Data
public class ResHospitalInfoParam {

    @NotEmpty
    private Long id;
    @NotEmpty
    private String hospitalCd;
    @NotEmpty
    private String patientId;
}
