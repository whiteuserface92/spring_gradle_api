package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.StatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class StatController {

    StatService statService;

    public StatController(StatService statService){
        this.statService = statService;
    }

    @Transactional
    @GetMapping(value = "/stats/findMenuStat")
    public String getMenuStat(
            @RequestParam(required=false) String hospitalCd,
             @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        //log.info("period : " +endDt+ "," +startDt);

        return statService.getPeriodMenuStat2(hospitalCd, startDt, endDt);
    }

    @Transactional
    @GetMapping(value = "/stats/findUserStat")
    public String getUserStat(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
    ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        //log.info("period : " +endDt+ "," +startDt);

        return statService.getUserStat2(startDt, endDt);
    }

    @Transactional
    @GetMapping(value = "/stats/findUserHourlyStat")
    public String getUserHourlyStat(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
    ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 23,59,59);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 00,00,00);
        }

        //log.info("period : " +endDt+ "," +startDt);

        return statService.getUserHourlyStat(startDt, endDt);
    }

    /**
     * 2020.09.22 : 김진호
     *
     * @param startDt
     * @param endDt
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findLoginLogtat")
    public String getLoginLogStat(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "ALL") String platformType
            , @RequestParam(required=false, defaultValue = "0") int hospitalId) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        return statService.getLoginLogStat(startDt, endDt, platformType, hospitalId);
    }


    /**
     * 2020.10.19 : 김진호
     * 일별 사용자 방문 플랫폼 현황
     * @param startDt
     * @param endDt
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findLoginpPlatformType")
    public String getLoginpPlatformType(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "0") int hospitalId) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        return statService.getLoginpPlatformType(startDt, endDt, hospitalId);
    }

    /**
     * 2020.10.19 : 김진호
     * 월별 누적 사용자 현황
     * @param startMm
     * @param endMm
     * @param platformType
     * @param hospitalId
     * @param serviceNmAllSum
     * @param platformTypeAllSum
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findMonthlyLoginLogtat")
    public String getMonthlyLoginLogtat(
            @RequestParam(required=false, defaultValue = "") String startMm
            , @RequestParam(required=false, defaultValue = "") String endMm
            , @RequestParam(required=false, defaultValue = "ALL") String platformType
            , @RequestParam(required=false, defaultValue = "0") int hospitalId
            , @RequestParam(required=false, defaultValue = "N") String serviceNmAllSum
            , @RequestParam(required=false, defaultValue = "N") String platformTypeAllSum ) {

        if( StringUtils.isEmpty(startMm) ) {
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusMonths(12);
//            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
            startMm = startDt.getYear() + "-" + startDt.getMonth();
        }

        if( StringUtils.isEmpty(endMm) ) {
            LocalDateTime now = LocalDateTime.now();
            endMm = now.getYear() + "-" + now.getMonth();
        }

        return statService.getMonthlyLoginLogtat(startMm, endMm, platformType, hospitalId, serviceNmAllSum, platformTypeAllSum);
    }


    /**
     * 2020.10.19 : 김진호
     * 회원 가입 현황(가입유형별)
     * @param startDt
     * @param endDt
     * @param platformType
     * @param hospitalCd
     * @param hospitalCdALL
     * @param ageCdAll
     * @param sexCdAll
     * @param areaCdAll
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findStatJoin")
    public String getJoinStat(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "ALL") String platformType
            , @RequestParam(required=false, defaultValue = "ALL") String hospitalCd
            , @RequestParam(required=false, defaultValue = "Y") String hospitalCdALL
            , @RequestParam(required=false, defaultValue = "Y") String ageCdAll
            , @RequestParam(required=false, defaultValue = "Y") String sexCdAll
            , @RequestParam(required=false, defaultValue = "Y") String areaCdAll ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        return statService.getJoinStat(startDt, endDt, hospitalCd, platformType, hospitalCdALL, ageCdAll, sexCdAll, areaCdAll);
    }


    /**
     * 2020.10.19 : 김진호
     * 회원 탈퇴 현황(탈퇴사유별)
     * @param startDt
     * @param endDt
     * @param userId
     * @param inactivedOnAll
     * @param inactiveTypeAll
     * @param withdrawalTypeAll
     * @param withdrawalType
     * @param inactivedOnPeriod
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findStatInactived")
    public String getStatInactived(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "0") int userId
            , @RequestParam(required=false, defaultValue = "Y") String inactivedOnAll
            , @RequestParam(required=false, defaultValue = "Y") String inactiveTypeAll
            , @RequestParam(required=false, defaultValue = "N") String withdrawalTypeAll
            , @RequestParam(required=false, defaultValue = "ALL") String withdrawalType
            , @RequestParam(required=false, defaultValue = "N") String inactivedOnPeriod ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        return statService.getStatInactived(startDt, endDt, userId, inactivedOnAll, inactiveTypeAll, withdrawalTypeAll, withdrawalType, inactivedOnPeriod );
    }


    /**
     * 2020.10.19 : 김진호
     * 전체 병원 사용자 현황(각병원별)
     * @param startDt
     * @param endDt
     * @param hospitalid
     * @param platformTypeAllSum
     * @param logedOnPeriod
     * @param appId
     * @param appIdAllSum
     * @return
     */
    @Transactional
    @GetMapping(value = "/stats/findStatHospital")
    public String getStatHospital(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDt
            , @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDt
            , @RequestParam(required=false, defaultValue = "0") int hospitalid
            , @RequestParam(required=false, defaultValue = "Y") String platformTypeAllSum
            , @RequestParam(required=false, defaultValue = "Y") String logedOnPeriod
            , @RequestParam(required=false, defaultValue = "0") int appId
            , @RequestParam(required=false, defaultValue = "N") String appIdAllSum ) {

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            endDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).plusDays(1);
            startDt = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 0,0,0).minusDays(14);
        }

        return statService.getStatHospital(startDt, endDt, hospitalid, platformTypeAllSum, logedOnPeriod, appId, appIdAllSum );
    }
}
