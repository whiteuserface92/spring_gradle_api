package com.dlsdlworld.spring.api.scheduler;

import com.dlsdlworld.spring.api.cachemodel.AppLogCache;
import com.dlsdlworld.spring.api.cachemodel.ClientLogCache;
import com.dlsdlworld.spring.api.cacherepository.AppLogCacheRepository;
import com.dlsdlworld.spring.api.cacherepository.ClientLogCacheRepository;
import com.dlsdlworld.spring.api.model.AppLog;
import com.dlsdlworld.spring.api.model.ClientLog;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.repository.AppLogRepository;
import com.dlsdlworld.spring.api.repository.ClientLogRepository;
import com.dlsdlworld.spring.api.types.ProductTypes;
import com.dlsdlworld.spring.api.types.ResourceTypes;
import com.dlsdlworld.spring.api.utils.AuthenticationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *    레디스에 저장된 appLog, MenuLog, loginLog등의 레디스 정보를 디비에 저장
 *   정상 작동했는지 확인 불가능
 * </p>
 * @author hyunmin.kim
 */
@Slf4j
@Component
public class RedisLogToDbInsertScheduler {


  @Autowired
  private AppLogRepository appLogRepository;

  @Autowired
  private AppLogCacheRepository appLogCacheRepository;

    @Autowired
    private ClientLogRepository clientLogRepository;

    @Autowired
    private ClientLogCacheRepository clientLogCacheRepository;

    @Value("${lemoncare.logtodb.scheduleYn:true}")
    private boolean isScheduleYn;

  /**
   * <p>
   *   매  30분 주기로 시작한다.  서버에 부담이되는듯 하다.
   *    @Scheduled(cron = "${scheduling.job.cron}")
   * </p>
   */
  @Scheduled(cron = "0 */30 * * * *", zone = "Asia/Seoul")
  public void scheduledAppLogToDbInsert(){
      // spring Security PASS FOR ROLE_SYSTEM_ADMIN : 999로 전체 어드민
      //AuthenticationUtil.configureAuthentication("ROLE_SYSTEM_ADMIN");
      AuthenticationUtil.configureAuthentication(ResourceTypes.ALL.getCode());
      // 1) AppLog
      if(isScheduleYn) {
          executeAppLogRedisToDb();
      }
      // 2) ApiLog
      AuthenticationUtil.clearAuthentication();
  }

    @Scheduled(cron = "0 */10 * * * *", zone = "Asia/Seoul")
    public void scheduledClientLogToDbInsert(){
        // spring Security PASS FOR ROLE_SYSTEM_ADMIN
        //AuthenticationUtil.configureAuthentication("ROLE_SYSTEM_ADMIN");
        AuthenticationUtil.configureAuthentication(ResourceTypes.ALL.getCode());
        // 1) AppLog
        if(isScheduleYn) {
            executeClientLogRedisToDb();
        }
        // 2) ApiLog
        AuthenticationUtil.clearAuthentication();
    }

    /**
     * 관리자 화면에서 Manual로 로그 Db로 넣기
     */
    public void callManualRedisToDbInsert(){
        // spring Security PASS FOR ROLE_SYSTEM_ADMIN
        //AuthenticationUtil.configureAuthentication("ROLE_SYSTEM_ADMIN");
        log.info(" Redis to Db insert  Start.====================================");
        AuthenticationUtil.configureAuthentication(ResourceTypes.ALL.getCode());
        // 1) ClientLog
        executeClientLogRedisToDb();
        // 2) ApiLog
        executeAppLogRedisToDb();
        log.info(" Redis to Db insert  End.======================================");
        AuthenticationUtil.clearAuthentication();
    }

    /**
     * client Javascript Log 쌓기용 처리
     */
    private void executeClientLogRedisToDb() {
        log.info("executeClientLogRedisToDb Redis  log to AuthenticationUtil db  insert Schduler Start.");
        int nPageSize = 1000;
        // 100개씩 가져 온다.
        Page<ClientLogCache> pageCache = clientLogCacheRepository.findAll(Pageable.ofSize(nPageSize));
        int nTotalPage    = pageCache.getTotalPages();
        long nTotalElements = pageCache.getTotalElements();
        if(nTotalElements > nPageSize) {
            for ( int i =0 ; i < nTotalPage; i++)  {
                log.info("executeClientLogRedisToDb Paging Saving. nPageSize:{}, i:{}",nPageSize, i);
                if(i!=0) { // 첫번째것은 위에 것을 사용한다.
                    pageCache = clientLogCacheRepository.findAll(Pageable.ofSize(nPageSize));
                }
                saveOnePageClientLog(pageCache);
                //db insert 후에 삭제 처리
            }
        } else {// 1페이지 일경우
            log.info("executeClientLogRedisToDb Paging Saving. nPageSize:{}, i:{}",nPageSize, "one Paging....");
            saveOnePageClientLog(pageCache);
        }
        log.info("executeClientLogRedisToDb Redis log to AuthenticationUtil db insert  Schduler End.");
    }

    private void saveOnePageClientLog(Page<ClientLogCache> pageCache) {
        List<ClientLog> lstLog;
        List<ClientLogCache> lst;
        ClientLog clientLog;
        User oUser ;
        lst       = pageCache.getContent();

        lstLog = new ArrayList<ClientLog>();
        for( ClientLogCache appLogCache  : lst) {
            clientLog = new ClientLog();  // 이부분이 밖에 있으면 saveAll이어도 한번만 실행되어서 들어 가게 된다.
            BeanUtils.copyProperties(appLogCache, clientLog);
            lstLog.add(clientLog);
        }
        log.info("saveOnePageClientLog appLogRepository Saving...:{}",lstLog.size());
        if ( lstLog.size() > 0 ) {
            clientLogRepository.saveAll(lstLog);
            //delete AppLogCache
            log.info("saveOnePageClientLog appLogCacheRepository deleting...:{}", lst.size());
            clientLogCacheRepository.deleteAll(lst);
        }
    }

    /***
     * App Log로 Client에서 오는 로그 전체부분에 대해서 로그 남긴다.
     */
  private void executeAppLogRedisToDb() {
        log.info("Redis log to AuthenticationUtil db  insert Schduler Start.");
        int nPageSize = 1000;
        // 100개씩 가져 온다.
        Page<AppLogCache> pageCache = appLogCacheRepository.findAll(Pageable.ofSize(nPageSize));
        int nTotalPage    = pageCache.getTotalPages();
        long nTotalElements = pageCache.getTotalElements();
        if(nTotalElements > nPageSize) {
            for ( int i =0 ; i < nTotalPage; i++)  {
               log.info("scheduledAppLogToDbInsert Paging Saving. nPageSize:{}, i:{}",nPageSize, i);
                 if(i!=0) { // 첫번째것은 위에 것을 사용한다.
                   pageCache = appLogCacheRepository.findAll(Pageable.ofSize(nPageSize));
                 }
                saveOnePageAppLog(pageCache);
              //db insert 후에 삭제 처리
            }
        } else {// 1페이지 일경우
           log.info("scheduledAppLogToDbInsert Paging Saving. nPageSize:{}, i:{}",nPageSize, "one Paging....");
           saveOnePageAppLog(pageCache);
        }
        log.info("Redis log to AuthenticationUtil db insert  Schduler End.");
    }

    private void saveOnePageAppLog(Page<AppLogCache> pageCache) {
    List<AppLog> lstAppLog;
    List<AppLogCache> lst;
    AppLog appLog;
  //  User oUser ;
    lst       = pageCache.getContent();

    lstAppLog = new ArrayList<AppLog>();
    for( AppLogCache appLogCache  : lst) {
        appLog = new AppLog();  // 이부분이 밖에 있으면 saveAll이어도 한번만 실행되어서 들어 가게 된다.
        BeanUtils.copyProperties(appLogCache, appLog);
    /*    oUser = new User();
        oUser.setId(appLogCache.getUserId());
        appLog.setUser(oUser);*/
        appLog.setProdCd(ProductTypes.codeOf(appLogCache.getProdCd()));
        lstAppLog.add(appLog);
    }
    log.info("saveOnePageAppLog appLogRepository Saving...:{}",lstAppLog.size());
    if ( lstAppLog.size() > 0 ) {
        appLogRepository.saveAll(lstAppLog);
        //delete AppLogCache
        log.info("saveOnePageAppLog appLogCacheRepository deleting...:{}", lst.size());
        appLogCacheRepository.deleteAll(lst);
    }
  }

}
