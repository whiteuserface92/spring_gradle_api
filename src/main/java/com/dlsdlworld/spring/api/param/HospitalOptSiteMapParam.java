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
public class HospitalOptSiteMapParam {

     Long id;
     Long hospitalId;
    /**
     * 옵션코드 참조 분류코드 코드
     */
     String refCd;

    /**
     * 참조코드에 대한 상세 코드리스트
     */
     String defCd;

    /**
     * 실제 사이트에 적용되는 site코드값 리스트
     */
     String siteCd;


     String hospitalCd;
}
