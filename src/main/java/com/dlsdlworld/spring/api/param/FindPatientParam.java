package com.dlsdlworld.spring.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/06/04
 * Time : 3:18 오후
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




