package com.dlsdlworld.spring.api.adminApiController;


import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.cacheloader.*;
import com.dlsdlworld.spring.api.scheduler.RedisLogToDbInsertScheduler;
import com.dlsdlworld.spring.api.types.ActionTypes;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CacheReloadController {

    private RedisTemplate redisTemplate;
    private CommonCodeCacheLoader commonCodeCacheLoader;
    private MenuCacheLoader menuCacheLoader;
    private MessageCacheLoader messageCacheLoader;
    private AppCacheLoader appCacheLoader;

    private HospitalOptionCodeCacheLoader hospitalOptionCodeCacheLoader;
    private HospitalOptSiteMapCacheLoader hospitalOptSiteMapCacheLoader;

    private UserConfigCacheLoader userConfigCacheLoader;

    /** 로그 이동을 위한 객체 */
    private RedisLogToDbInsertScheduler redisLogToDbInsertScheduler;

    @Autowired
    public CacheReloadController(
            RedisTemplate redisTemplate,
            CommonCodeCacheLoader commonCodeCacheLoader,
            MenuCacheLoader menuCacheLoader,
            MessageCacheLoader messageCacheLoader,
            AppCacheLoader appCacheLoader,
            HospitalOptionCodeCacheLoader  hospitalOptionCodeCacheLoader,
            HospitalOptSiteMapCacheLoader hospitalOptSiteMapCacheLoader,
            RedisLogToDbInsertScheduler redisLogToDbInsertScheduler,
            UserConfigCacheLoader userConfigCacheLoader) {
        this.redisTemplate = redisTemplate;
        this.commonCodeCacheLoader = commonCodeCacheLoader;
        this.menuCacheLoader = menuCacheLoader;
        this.messageCacheLoader = messageCacheLoader;
        this.appCacheLoader = appCacheLoader;
        this.hospitalOptionCodeCacheLoader = hospitalOptionCodeCacheLoader;
        this.hospitalOptSiteMapCacheLoader = hospitalOptSiteMapCacheLoader;
        this.redisLogToDbInsertScheduler   = redisLogToDbInsertScheduler;
        this.userConfigCacheLoader         = userConfigCacheLoader;
    }

    /**
     * 캐시 새로고침
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/caches/reload")
    public ResponseEntity reload() {
        try {
            flushCache();
            commonCodeCacheLoader.load();
            messageCacheLoader.load();
            menuCacheLoader.load();
            hospitalOptionCodeCacheLoader.load();
            hospitalOptSiteMapCacheLoader.load();
            userConfigCacheLoader.load();// UserConfig reload
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 캐시 새로고침
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/hospitalMenuCaches/reload")
    public ResponseEntity reloadMenus() {
        try {
            menuCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 캐시 새로고침
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/hospitalMenuCaches/{hospitalId}/reload")
    public ResponseEntity reloadHospitalMenusByHospiatlId(@PathVariable Long hospitalId,
                                      @RequestParam String prodCd) {
        try {
            menuCacheLoader.load(hospitalId, prodCd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 전체 코드 refresh
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/commonCode/reload")
    public ResponseEntity reloadCommonCodeByCodeCls() {
        try {
            commonCodeCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 전체 코드 refresh
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/commonCode/reloadByCodeCls")
    public ResponseEntity reloadCommonCodeByCodeCls(@RequestParam(required = false) String codeCls) {
        try {
            commonCodeCacheLoader.load(codeCls);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 전체 코드 refresh
     * @return
     */
    @Synchronized
    @Transactional
    @LogAdminExecution(code = ActionTypes.CREATE, descriptions = "메뉴 캐시 초기화")
    @PostMapping(value = "/rest/menuCaches/reload")
    public ResponseEntity reloadMenuCaches() {
        try {
            menuCacheLoader.loadMenu();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 전체 코드 refresh
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/messageCaches/reload")
    public ResponseEntity reloadMessageCaches() {
        try {
            messageCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 전체 코드 refresh
     * @return
     */
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/messageCaches/reloadByKeyword")
    public ResponseEntity reloadMessageCachesByKeyWord(@RequestParam(required = false) String keyword, @RequestParam String langCd) {
        try {
            messageCacheLoader.load(keyword, langCd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/appCaches/reload")
    public ResponseEntity reloadAppCaches() {
        try {
            appCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/hospitalOptionCodeCaches/reload")
    public ResponseEntity reloadHospitalOptionCodeCaches() {
        try {
            hospitalOptionCodeCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/hospitalOptSiteMapCaches/reload")
    public ResponseEntity reloadHospitalOptSiteMapCaches() {
        try {
            hospitalOptSiteMapCacheLoader.load();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Synchronized
    @Transactional
    @PostMapping(value = "/rest/redis/todb")
    public ResponseEntity reloadRedisToDb() {
      //  try {
          redisLogToDbInsertScheduler.callManualRedisToDbInsert();
            return ResponseEntity.ok().build();
       /* } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }



    /**
     * 캐시 플러시
     */
    private void flushCache() {
        log.info("### Redis flushCache ###");
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
    }

}
