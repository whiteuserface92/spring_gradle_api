package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseHospitalOptSiteMap;
import com.dlsdlworld.spring.api.event.HospitalOptSiteMapListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * Project : 옵션관리에서 사용할 코드 선언 hospital_id로 연결되나 실제 REDISCACHE에서는 HOSPITAL_CD로 실행되어야 할 필요 있음
 */
@Getter
@Setter
@Entity
@EntityListeners(HospitalOptSiteMapListener.class)
@Table(name = Tables.HospitalOptSiteMap)
public class HospitalOptSiteMap extends BaseHospitalOptSiteMap {

}
