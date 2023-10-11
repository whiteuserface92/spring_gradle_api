package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * 병원별 메뉴 속성 정보
 */
@Repository
public interface HospitalMenuCstmsCacheRepository extends CrudRepository<HospitalMenuCstmsCache, Long> {

    /**
     * 병원 코드로 조회
     * @param hosptialMenuId 병원 메뉴 ID
     * @return 병원메뉴에 매핑된 attr
     */
    HospitalMenuCstmsCache findByHospitalMenuId(Long hosptialMenuId);

}
