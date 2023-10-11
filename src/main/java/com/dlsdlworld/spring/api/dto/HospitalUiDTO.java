package com.dlsdlworld.spring.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 작업일자: 2022-04-30
 * elctTest.js UI로그인화면에서 사용할 hospital_mst id, hospitalCd, hospitalNm으로 서비스 하기위해 DTO 만듬
 */
@Getter
@Setter
@NoArgsConstructor
public class HospitalUiDTO {


    Long id;
    private String code;
    private String name;

 /*   public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
*/
    public HospitalUiDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
