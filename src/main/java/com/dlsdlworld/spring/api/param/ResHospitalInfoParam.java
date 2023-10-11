package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**

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
