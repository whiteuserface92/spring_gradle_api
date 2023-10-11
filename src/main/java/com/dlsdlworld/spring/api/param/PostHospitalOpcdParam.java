package com.dlsdlworld.spring.api.param;

import lombok.Data;

/**

 */
@Data
public class PostHospitalOpcdParam {

    /**
     * 병원 ID
     */
    private Long hospitalId;
    /**
     * 병원 코드
     */
    private String hospitalCd;
    /**
     * 옵션 코드 1개 요청시
     */
    private String opCd;
    /**
     * 옵션 코드 ',' 딜리미네이터 구분자 목록
     */
    private String opCds;

}
