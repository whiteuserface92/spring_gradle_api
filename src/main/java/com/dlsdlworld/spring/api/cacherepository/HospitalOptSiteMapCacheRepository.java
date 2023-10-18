package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.HospitalOptSiteMapCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 병원별 옵션코드 캐시정보를 저장하는 저장소
 */
@Repository
public interface HospitalOptSiteMapCacheRepository extends CrudRepository<HospitalOptSiteMapCache, Long> {

    /**
     * 병원 코드로  옵션코드 조회
     * @param hospitalCd 병원 코드
     * @return 저장된 병원 메뉴 캐시 리스트
     */
    List<HospitalOptSiteMapCache> findByHospitalCd(String hospitalCd);

    /**
     * 병원 Id로 캐시 정보 조회
     * @param hospitalId
     * @return
     */
    List<HospitalOptSiteMapCache> findByHospitalId(Long hospitalId);



 

}
