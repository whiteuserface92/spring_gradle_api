package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.cachemodel.ClientLogCache;
import com.dlsdlworld.spring.api.cacherepository.ClientLogCacheRepository;
import com.dlsdlworld.spring.api.model.ClientLog;
import com.dlsdlworld.spring.api.param.ClientLogCacheParam;
import com.dlsdlworld.spring.api.repository.ClientLogRepository;
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
public class ClientLogController {


    private ClientLogCacheRepository clientLogCacheRepository;

    private ClientLogRepository clientLogRepository;

    public ClientLogController(ClientLogCacheRepository clientLogCacheRepository
                               , ClientLogRepository clientLogRepository) {
        this.clientLogCacheRepository = clientLogCacheRepository;
        this.clientLogRepository     = clientLogRepository;
    }

    @Transactional
    @PostMapping(value = "/clientLogCache")
    public ResponseEntity<?> saveClientLogCache(@RequestBody @Validated ClientLogCacheParam clientLogCacheParam) {
        ClientLogCache clientLogCache = new ClientLogCache();
         try {
               BeanUtils.copyProperties(clientLogCacheParam , clientLogCache );
              String id =  UUID.randomUUID().toString();  // 순차 증가 ID 체번
             clientLogCache.setUuid(id);
             clientLogCache.setCreatedOn(LocalDateTime.now());
          } catch (Exception ex){
              log.error(ex.getLocalizedMessage());
         }
        ClientLogCache ret= clientLogCacheRepository.save(clientLogCache);
         log.info("ret:{}",ret);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @GetMapping(value = "/clientlog/findAllByKeyword")
    public Page<ClientLog> findAllByKeyword(Pageable page
            , @RequestParam(required=false, defaultValue = "") String keyword
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDt
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDt ) {

        Page<ClientLog> pages = clientLogRepository.findAllByKeyword(page,
                LocalDateTime.parse(startDt),
                LocalDateTime.parse(endDt),
                keyword );

        return pages;
    }

}
