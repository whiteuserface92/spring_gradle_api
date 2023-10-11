package com.dlsdlworld.spring.api.cache;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Set;

/**
 * 병원메뉴 별 속성 정보를 가지고 있는 캐시
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : suyeon.you
 * Date : 2020/07/01
 * Time : 04:16
 */
@Data
@NoArgsConstructor
@RedisHash("HospitalMenuCstmsCache")
public class HospitalMenuCstmsCache implements Serializable {

    @Id
    private Long hospitalMenuId;

    private Set<HospitalMenuAttr> customAttrs;

    @Builder
    public HospitalMenuCstmsCache(Long hospitalMenuId, Set<HospitalMenuAttr> customAttrs) {
        this.hospitalMenuId = hospitalMenuId;
        this.customAttrs = customAttrs;
    }
}

