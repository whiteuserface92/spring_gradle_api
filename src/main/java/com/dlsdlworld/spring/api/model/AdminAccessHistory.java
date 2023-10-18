package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAdminAccessHistory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AdminAccessHistory)
public class AdminAccessHistory extends BaseAdminAccessHistory {
}
