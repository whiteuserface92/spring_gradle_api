package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : HeeJin Ahn
 * Date : 2020/11/06
 * Time : 15:15
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AdminAccessHistory)
public class AdminAccessHistory extends BaseAdminAccessHistory {
}
