package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 관심상
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGoodsKeep extends BasePersistable {

    /**
     * 관심상품코드
     */
    @Column(length = Columns.goodsCd, nullable = false)
    private String goodsCd;

    /**
     * 관심상품명
     */
    @Column(length = Columns.goodsNm, nullable = false)
    private String goodsNm;

    /**
     * 생성일시
     */
    @Column(nullable = false)
    private LocalDateTime createdOn;
}
