package com.dlsdlworld.spring.api.projection;

import com.dlsdlworld.spring.api.model.AdminAccessHistory;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 관리자 로그인 기록
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
