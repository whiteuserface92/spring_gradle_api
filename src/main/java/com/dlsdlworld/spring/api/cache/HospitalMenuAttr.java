package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 병원별 메뉴 속성 정보 하위 POJO
 * Developer : suyeon.you
 * Date : 2020/07/01
 * Time : 04:16
 */
@Data
public class HospitalMenuAttr implements Serializable {

    private String attrKey;

    private String attrVal;

    private String msgCd;

    @Builder
    public HospitalMenuAttr(String attrKey, String attrVal, String msgCd) {
        this.attrKey = attrKey;
        this.attrVal = attrVal;
        this.msgCd = msgCd;
    }
}
