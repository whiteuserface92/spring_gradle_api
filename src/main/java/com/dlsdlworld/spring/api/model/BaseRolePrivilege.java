package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/12
 * Time : 2:24 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseRolePrivilege extends BaseModifiable {
}