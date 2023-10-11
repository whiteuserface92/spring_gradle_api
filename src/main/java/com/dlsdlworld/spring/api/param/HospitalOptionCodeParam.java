package com.dlsdlworld.spring.api.param;

import lombok.Data;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/07
 * Time : 13:57 오후
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
