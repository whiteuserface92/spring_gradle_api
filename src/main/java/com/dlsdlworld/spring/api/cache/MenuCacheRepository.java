package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 메뉴정보 캐시 저장소
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/29
 * Time : 14:57
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
