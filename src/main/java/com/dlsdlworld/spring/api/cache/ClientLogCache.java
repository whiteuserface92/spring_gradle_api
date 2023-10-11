package com.dlsdlworld.spring.api.cache;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 레디스 로그 저장용 객체로 ID 객체는 사용하지 않음
 */
@Data
@NoArgsConstructor
@RedisHash("ClientLogCache")
public class ClientLogCache implements Serializable {

    /**
     * 식별자 : AppLog Id , server_ip는 있으나, localIp가 없다 이것을 찾을 필요가 있지 않나?
     * userIp라고 해서 로그 남기는 로직이 필요
     * 다른캐시는 디비에 있는 저장후 캐시가 되나, Log 캐시는 Redis에 입력된 후에 디비로 이관 처리 된다.
     */
    @Id     // ID 객체는 로그라 필요 없음
    private String uuid;
    /* 10.toNative , 20.callback ,30.Native App call,  99.js Error */
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
