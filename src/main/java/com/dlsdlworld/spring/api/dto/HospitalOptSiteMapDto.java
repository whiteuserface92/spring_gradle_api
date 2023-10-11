package com.dlsdlworld.spring.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */

@Data
@NoArgsConstructor
public class HospitalOptSiteMapDto implements Serializable {



    Long id;
    /**
     * 병원코드
     */
    private Long hospitalId;

    /**
     * 옵션코드 참조 분류코드 코드
     */
    private String refCd;

    /**
     * 참조코드에 대한 상세 코드리스트
     */
    private String defCd;

    /**
     * 실제 사이트에 적용되는 site코드값 리스트
     */ 
    private String siteCd;
    /**
     * 실제 defCdNm
     */
    private String defCdNm;
    /**
     * 분류 코드
     */
    private String codeCls;

    public HospitalOptSiteMapDto(Long id, Long hospitalId, String refCd, String defCd, String siteCd, String defCdNm, String codeCls) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.refCd = refCd;
        this.defCd = defCd;
        this.siteCd = siteCd;
        this.defCdNm = defCdNm;
        this.codeCls = codeCls;
    }
}
