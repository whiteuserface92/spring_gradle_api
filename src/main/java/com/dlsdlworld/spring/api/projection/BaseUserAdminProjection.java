package com.dlsdlworld.spring.api.projection;

/**
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon
 * Date : 2020/02/19
 * Time : 13:36
 */
public interface BaseUserAdminProjection {

    Long getId();

    String getDutyNm();

    String getEmpNo();

    String getDeptCd();

    String getDeptNm();

    String getIpAddr();

    Boolean getEnabled();

}
