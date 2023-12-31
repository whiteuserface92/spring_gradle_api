package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseAppMenuLog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Plus 모델링이 변경되어 재정의 하여 처리
 */
@Getter
@Setter
@Entity
@Table(name = Tables.AppMenuLog)
public class AppMenuLog extends BaseAppMenuLog {
}
