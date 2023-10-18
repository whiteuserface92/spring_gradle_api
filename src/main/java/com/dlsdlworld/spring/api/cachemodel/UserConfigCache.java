package com.dlsdlworld.spring.api.cachemodel;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * 사용자 환경설정  캐시
 */
@Data
@NoArgsConstructor
@RedisHash("UserConfigCache")
public final class UserConfigCache implements Serializable {

    @Id
    private Long id;

    private String deptCd;




    /**
     * 언어설정
     */
    private String langCd;

    private String pushOnOff;

    private String alarmOffStarttime;

    private String alarmOffEndtime;

    /**
     * session Time Out 분으로 저장
     */
    private String sessTimeoutMm;

    /***
     * 기간계의 로그인 ID
     */
    @Indexed
    private String myCi;
    /**
     * 레몬 UserId
     */
    @Indexed
    private Long userId;
    @Builder
    public UserConfigCache(Long id, String deptCd, String langCd, String pushOnOff, String alarmOffStarttime, String alarmOffEndtime, String sessTimeoutMm, String myCi, Long userId) {
        this.id = id;
        this.deptCd = deptCd;
        this.langCd = langCd;
        this.pushOnOff = pushOnOff;
        this.alarmOffStarttime = alarmOffStarttime;
        this.alarmOffEndtime = alarmOffEndtime;
        this.sessTimeoutMm = sessTimeoutMm;
        this.myCi = myCi;
        this.userId = userId;
    }

}
