package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**

 */
@Data
class FindPatientParam {

    @NotEmpty
    private String identificationNo1;

    @NotEmpty
    private String identificationNo2;

    @NotEmpty
    private String patientNm;

    @NotEmpty
    private String transKey;

    @NotEmpty
    private String prodCd;

    private String cellphoneNo;

}




