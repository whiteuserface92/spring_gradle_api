package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020/07/02
 * Time : 10:22 오전
 * 일단 persistable 로 만들고 김상무님이 createdOn 추가해 주시면 Modifiable로 변경
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalMenuCstm extends BaseModifiable {
    @Column(length = Columns.attrKey)
    private String attrKey;

    @Column(length = Columns.attrVal)
    private String attrVal;

    @Column(length = Columns.msgCd)
    private String msgCd;

}
