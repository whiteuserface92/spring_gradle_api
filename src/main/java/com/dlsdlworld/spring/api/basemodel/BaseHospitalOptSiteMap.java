package com.dlsdlworld.spring.api.basemodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 병원별 옵션 사이트 맵
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalOptSiteMap extends BasePersistable {

    /**
     * 병원코드로 테이블의 키값은 hospitalId+refCd+defCd값으로 키값이 들어 가야 한다.
     */
    @Column(columnDefinition = "int8" , nullable = false)
    private Long hospitalId;

     /**
     * 옵션코드 참조 분류코드 코드
     */
    @Column(length = Columns.code, nullable = false)
    private String refCd;

    /**
     * 참조코드에 대한 상세 코드리스트
     */
    @Column(length = Columns.code, nullable = false)
    private String defCd;

    /**
     * 실제 사이트에 적용되는 site코드값 리스트
     */
    @Column(length = Columns.code, nullable = false)
    private String siteCd;

}
