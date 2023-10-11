package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 위젯사용설정
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : kyunghun.kim
 * Date : 2020/04/28
 * Time : 3:41 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseHospitalWidget extends BasePersistable {

    /**
     * 재정의제목메세지
     */
    @Column(length = Columns.ovrdTitle)
    private String ovrdTitle;
}
