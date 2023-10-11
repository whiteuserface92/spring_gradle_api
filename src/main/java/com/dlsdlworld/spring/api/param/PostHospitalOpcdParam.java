package com.dlsdlworld.spring.api.param;

import lombok.Data;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : woong.jang
 * Date : 2020/05/019
 * Time : 3:57 오후
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
