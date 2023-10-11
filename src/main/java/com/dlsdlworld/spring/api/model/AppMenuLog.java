package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Project : lamoncare-plus-parent
 * Plus 모델링이 변경되어 재정의 하여 처리
 * @author  : hyunmin.kim
 * @since : 21-04-29
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AppMenuLog)
public class AppMenuLog extends BaseAppMenuLog {
}
