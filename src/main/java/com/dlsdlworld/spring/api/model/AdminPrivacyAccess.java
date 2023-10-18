package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAdminPrivacyAccess;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AdminPrivacyAccess)
public class AdminPrivacyAccess extends BaseAdminPrivacyAccess {
}
