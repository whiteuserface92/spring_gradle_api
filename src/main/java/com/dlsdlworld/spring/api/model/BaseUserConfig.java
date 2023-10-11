package com.dlsdlworld.spring.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 사용자환경설정
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : hansik.shin
 * Date : 2020/05/13
 * Time : 4:14 오후
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseUserConfig extends BaseModifiable {
    /**
     * 진료과설정
     */
    @Column(name = "dept_cd" ,length = Columns.deptCd)
    private String deptCd;

    /**
     * 언어설정
     */
    @Column(name = "lang_cd" , length = Columns.langCd)
    private String langCd;
    /**
     *
     */
    @Column(name = "push_on_off", length = 1, nullable = true)
    private String pushOnOff;

    @Column(name = "alarm_off_starttime", length = 13, nullable = true)
    private String alarmOffStarttime;

    @Column(name = "alarm_off_endtime", length = 13, nullable = true)
    private String alarmOffEndtime;

    /**
     * session Time Out 분으로 저장
     */
    @Column(name = "sess_timeout_mm", length = 13, nullable = true)
    private String sessTimeoutMm;

    //옵션 추가 etc 1~3

    @Column(name = "etc1")
    private String etc1;

    @Column(name = "etc2")
    private String etc2;

    @Column(name = "etc3")
    private String etc3;

}
