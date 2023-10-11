package com.dlsdlworld.spring.api.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Slf4j
//@Builder
public class ClientLogCacheParam {

    /* 10.toNative , 20.callback ,30.Native App call,  99.js Error */
    @NotEmpty
    private String clientCd;

    private String funcCall;

    private String argsParam;

    private String logData;

    private Integer elapsedTime;

    private String traceId;

    private String userIp;

    private String userAccnt;

    private Long userId;

    private LocalDateTime createdOn;
}
