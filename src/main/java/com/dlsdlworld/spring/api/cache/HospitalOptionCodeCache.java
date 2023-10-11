package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * 병원별옵션코드 캐시.
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/11
 * Time : 15:07
 */
@Data
@NoArgsConstructor
@RedisHash("HospitalOptionCodeCache")
public final class HospitalOptionCodeCache implements Serializable {

    /** 식별자*/
    @Id
    private Long id;
    @Indexed
    private String optCd;
    private String optVal;
    @Indexed
    private Long  hospitalId;
    @Indexed
    private String hospitalCd;

    @Builder
    public HospitalOptionCodeCache(Long id, String optCd, String optVal, Long hospitalId, String hospitalCd) {
        this.id = id;
        this.optCd = optCd;
        this.optVal = optVal;
        this.hospitalId = hospitalId;
        this.hospitalCd = hospitalCd;
    }
}
