package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 병원 메뉴 캐시정보를 저장하는 저장소
 */
@Repository
public interface HospitalMenuCacheRepository extends CrudRepository<HospitalMenuCache, Long> {

    /**
     * 병원 코드로 조회
     * @param hospitalCd 병원 코드
     * @return 저장된 병원 메뉴 캐시 리스트
     */
    List<HospitalMenuCache> findByHospitalCd(String hospitalCd);


    /**
     * 병원 코드로 조회
     * @param hospitalCd
     * @param prodCd
     * @return
     */
    List<HospitalMenuCache> findByHospitalCdAndProdCd(String hospitalCd, String prodCd);

    /**
     * UI INTERCEPTOR  에서 메뉴 정보를 LOCALERESOLVER를 타도록 변경하기 위해서 추가했습니다.
     * @param hospitalCd
     * @param prodCd
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> findByHospitalCdAndProdCd(String hospitalCd, String prodCd, Class<T> type);

    /**
     * ID로 ParentId 조회
     * @param id
     * @return
     */
    List<HospitalMenuCache> findByParentId(Long id);

}
