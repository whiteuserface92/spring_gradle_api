package com.dlsdlworld.spring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

@Data
@NoArgsConstructor
public class HospitalOptionCodeDto implements Serializable {

    Long id;
    String optCd;
    String codeNm;
    String optVal;
    String refVal;
    Long hospitalId;
    String hospitalCd;
    List<CommonCodeDto> baseCodes;

    /**
     * HospitalCD 제외 : Repo에서 조회시 사용
     * @param id
     * @param code
     * @param codeNm
     * @param optVal
     * @param refVal
     * @param hospitalId
     */
    public HospitalOptionCodeDto(Long id, String optCd, String codeNm, String optVal, String refVal, Long hospitalId) {
        this.id = id;
        this.optCd = optCd;
        this.codeNm = codeNm;
        this.optVal = optVal;
        this.refVal = refVal;
        this.hospitalId = hospitalId;
    }

    /**
     * HospitalCd 포함
     * @param id
     * @param code
     * @param codeNm
     * @param optVal
     * @param refVal
     * @param hospitalId
     * @param hospitalCd
     */
    public HospitalOptionCodeDto(Long id, String optCd, String codeNm, String optVal, String refVal, Long hospitalId, String hospitalCd) {
        this.id = id;
        this.optCd = optCd;
        this.codeNm = codeNm;
        this.optVal = optVal;
        this.refVal = refVal;
        this.hospitalId = hospitalId;
        this.hospitalCd = hospitalCd;
    }
}
