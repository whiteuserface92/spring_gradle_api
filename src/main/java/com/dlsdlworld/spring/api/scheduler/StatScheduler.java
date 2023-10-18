package com.dlsdlworld.spring.api.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * <p>
 *   통계 function 호출하는 스케줄러
 *   기존 만들어 놓은 function 이 리턴이 없어서
 *   정상 작동했는지 확인 불가능
 * </p>
 * @author hyunmin.kim
 */
@Slf4j
@Component
public class StatScheduler {

  @PersistenceContext
  EntityManager entityManager;

  @Value("${lemoncare.logtodb.scheduleYn:true}")
  private boolean isScheduleYn;
  /**
   * <p>
   *   매일 00:01 분에 실행한다.
   * </p>
   */
  @Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
  public void scheduledStatistics(){

    log.info("통계 수집 스케줄러을 시작합니다. :{}", isScheduleYn);
    if(isScheduleYn) {
      String sql = "select cast(public.stat_menu_batch() AS VARCHAR), cast(public.stat_join_batch() AS VARCHAR), cast(public.stat_login_batch() AS VARCHAR);";

      Query query = entityManager.createNativeQuery(sql);
      List<Object[]> ret = query.getResultList();
      log.info("{} 실행 Result Count", ret.size());
    }

    log.info("통계 수집 스케줄러을 종료합니다.");
  }

}
