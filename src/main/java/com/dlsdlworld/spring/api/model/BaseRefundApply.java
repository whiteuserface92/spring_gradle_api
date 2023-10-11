package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 환불신청
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/29
 * Time : 12:29 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseRefundApply extends BasePersistable {

    /**
     * 환불신청일시
     */
    private LocalDateTime refundDttm;

    /**
     * 환불사유코드
     */
    @Column(length = Columns.refundCd)
    private String refundCd;

    /**
     * 환불신청사유
     */
    @Column(length = Columns.refundTxt)
    private String refundTxt;


}
