package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAdminLoginHistory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AdminLoginHistory)
public class AdminLoginHistory extends BaseAdminLoginHistory {
}
