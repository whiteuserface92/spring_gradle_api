package com.dlsdlworld.spring.api.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 환경설정값 캐시정보를 저장하는 저장소
 * Project : lemoncare-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/01/30
 * Time : 15:46
 */
@Repository
public interface UserConfigCacheRepository extends CrudRepository<UserConfigCache, Long> {

    Optional<UserConfigCache> findByMyCi(String myCi);
    //List<UserConfigCache>  findByMyCi(List<String> myCis);
    Optional<UserConfigCache>   findByUserId(Long userId);

   // List<UserConfigCache> findUserConfigCacheByMyCiIn(List<String> plusIds);

}
