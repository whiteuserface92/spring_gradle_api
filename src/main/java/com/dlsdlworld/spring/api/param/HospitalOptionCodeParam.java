package com.dlsdlworld.spring.api.param;

import lombok.Data;

/**

 */

@Data
public class HospitalOptionCodeParam {

    Long id;
    String optCd;
    String codeNm;
    String optVal;
    String refVal;
    Long hospitalId;
    String hospitalCd;
}
