package com.dlsdlworld.spring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */

@Data
@NoArgsConstructor
public class CommonCodeDto implements Serializable {

    Long id;
    String code;
    String codeNm;
    String dispOrd;

    public CommonCodeDto(Long id, String code, String codeNm) {
        this.id = id;
        this.code = code;
        this.codeNm = codeNm;
    }

    public CommonCodeDto(Long id, String code, String codeNm, String dispOrd) {
        this.id = id;
        this.code = code;
        this.codeNm = codeNm;
        this.dispOrd = dispOrd;
    }
}
