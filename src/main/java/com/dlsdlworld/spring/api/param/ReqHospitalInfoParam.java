package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**

 */

@Data
public class ReqHospitalInfoParam {


    private Long userId;
    @NotEmpty
    private String hospitalCd;
    @NotEmpty
    private String patientId;
}
