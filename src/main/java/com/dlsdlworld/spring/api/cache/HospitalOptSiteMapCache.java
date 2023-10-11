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
@RedisHash("HospitalOptSiteMapCache")
public final class HospitalOptSiteMapCache implements Serializable {

    /** 식별자*/
    @Id
    private Long id;
    @Indexed
    private Long hospitalId;

    /**
     * 옵션코드 참조 분류코드 코드
     */
    private String refCd;

    /**
     * 참조코드에 대한 상세 코드리스트
     */
    private String defCd;

    /**
     * 실제 사이트에 적용되는 site코드값 리스트
     */
    private String siteCd;

    @Indexed
    private String hospitalCd;

    @Builder
    public HospitalOptSiteMapCache(Long id, Long hospitalId, String refCd, String defCd, String siteCd, String hospitalCd) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.refCd = refCd;
        this.defCd = defCd;
        this.siteCd = siteCd;
        this.hospitalCd = hospitalCd;
    }
}
