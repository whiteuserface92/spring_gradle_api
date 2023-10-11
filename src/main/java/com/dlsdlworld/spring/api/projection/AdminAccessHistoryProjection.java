package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.AdminAccessHistory;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 관리자 접근 기록
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : jinho
 * Date : 2020/11/18
 * Time : 10:27 오전
 */
@Projection(name = "AdminAccessHistoryProjection", types = {AdminAccessHistory.class})
public interface AdminAccessHistoryProjection {

    Long getId();
    String getAction();
    String getDescription();
    String getHospitalCd();
    String getIp();
    Long getTargetUserId();
    String getUserAccnt();
    LocalDate getYmd();
    LocalDateTime getCreatedOn();
}
