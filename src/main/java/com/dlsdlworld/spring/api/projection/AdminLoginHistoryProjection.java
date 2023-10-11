package com.dlsdlworld.spring.api.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.dlsdlworld.spring.api.model.AdminAccessHistory;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 관리자 로그인 기록
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : jinho
 * Date : 2020/11/18
 * Time : 10:27 오전
 */
@Projection(name = "AdminAccessHistoryProjection", types = {AdminAccessHistory.class})
public interface AdminLoginHistoryProjection {

    Long getId();
    String getUserAccnt();
    String getSuccess();
    String getDetails();
    String getIp();
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getYmd();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime getCreatedOn();
}
