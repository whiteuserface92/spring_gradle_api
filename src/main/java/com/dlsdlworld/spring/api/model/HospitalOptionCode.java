package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseHospitalOptionCode;
import com.dlsdlworld.spring.api.event.HospitalOptionCodeListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project : 옵션관리에서 사용할 코드 선언 hospital_id로 연결되나 실제 REDISCACHE에서는 HOSPITAL_CD로 실행되어야 할 필요 있음
 */
@Getter
@Setter
@Entity
@EntityListeners(HospitalOptionCodeListener.class)
@Table(name = Tables.HospitalOptionCode)
public class HospitalOptionCode extends BaseHospitalOptionCode {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
}
