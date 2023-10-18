package com.dlsdlworld.spring.api.cacherepository;

import com.dlsdlworld.spring.api.cachemodel.MenuCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 메뉴정보 캐시 저장소
 */
@Repository
public interface MenuCacheRepository extends CrudRepository<MenuCache, Long> {

    /**
     * 이름코드로 조회
     * @param nameCd
     * @return
     */
    MenuCache findByNameCd(String nameCd);
}
