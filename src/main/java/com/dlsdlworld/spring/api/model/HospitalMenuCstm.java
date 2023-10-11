package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.event.HospitalMenuCstmListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 병원별 메뉴 속성 정보
 */
@Getter
@Setter
@Entity
@Table(name = Tables.HospitalMenuCstm)
@EntityListeners(HospitalMenuCstmListener.class)
public class HospitalMenuCstm extends BaseHospitalMenuCstm {

    @ManyToOne
    @JoinColumn(name = "hospital_menu_id", nullable = false)
    private HospitalMenu hospitalMenu;
}
