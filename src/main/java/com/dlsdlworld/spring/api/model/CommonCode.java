package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 6:48 오후
 */
@Getter
@Setter
@Entity
@Table(name = Tables.CommonCode)
public class CommonCode extends BaseCommonCode {
}
