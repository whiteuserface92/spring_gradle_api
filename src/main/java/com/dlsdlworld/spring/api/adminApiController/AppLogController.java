package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.cachemodel.AppLogCache;
import com.dlsdlworld.spring.api.cacherepository.AppLogCacheRepository;
import com.dlsdlworld.spring.api.model.AppLog;
import com.dlsdlworld.spring.api.param.AppLogCacheParam;
import com.dlsdlworld.spring.api.repository.AppLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AppLogController {


    private AppLogCacheRepository appLogCacheRepository;

    private AppLogRepository appLogRepository;

    public AppLogController(AppLogCacheRepository appLogCacheRepository
                 , AppLogRepository appLogRepository) {
        this.appLogCacheRepository = appLogCacheRepository;
        this.appLogRepository =  appLogRepository;

    }

    @Transactional
    @PostMapping(value = "/appLogCache")
    public ResponseEntity<?> saveAppLogCache(@RequestBody @Validated AppLogCacheParam appLogCacheParam) {
         AppLogCache appLogCache = new AppLogCache();
         try {
               BeanUtils.copyProperties(appLogCacheParam , appLogCache );
              String id =  UUID.randomUUID().toString();  // 순차 증가 ID 체번
              appLogCache.setUuid(id);
              appLogCache.setCreatedOn(LocalDateTime.now());
          } catch (Exception ex){
              log.error(ex.getLocalizedMessage());
         }
        AppLogCache ret= appLogCacheRepository.save(appLogCache);
         log.info("ret:{}",ret);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @GetMapping(value = "/applog/findAllByKeyword")
    public Page<AppLog> findAllByKeyword(Pageable page
            , @RequestParam(required=false, defaultValue = "") String errorEnabled
            , @RequestParam(required=false, defaultValue = "") String keyword
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDt
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDt ) {

         Page<AppLog> pages = appLogRepository.findAllByKeyword(page,
               //  errorEnabled,
                 LocalDateTime.parse(startDt),
                 LocalDateTime.parse(endDt),
                 keyword );

        return pages;
    }

}
