package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * 상품가격이력
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/03/30
 * Time : 7:00 오전
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseGoodsPriceHistory extends BasePersistable {

    /**
     * 유효시작일
     */
    @Column(nullable = false)
    private LocalDate startedOn;

    /**
     * 유효종료일
     */
    @Column(nullable = false)
    private LocalDate endedOn;

    /**
     * 부가세포함 판매가격
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * 부가세포함 매입가격
     */
    @Column(nullable = false)
    private Integer buyAmt;

    /**
     * 부가세포함 대행수수료
     */
    @Column(nullable = false)
    private Integer fee;
}
