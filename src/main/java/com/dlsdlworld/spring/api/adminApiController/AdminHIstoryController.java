package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.AdminHistoryService;
import com.dlsdlworld.spring.api.projection.AdminAccessHistoryProjection;
import com.dlsdlworld.spring.api.projection.AdminLoginHistoryProjection;
import com.dlsdlworld.spring.api.projection.AdminPrivacyAccessProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class AdminHIstoryController {

    private AdminHistoryService adminHistoryService;

    @Autowired
    public AdminHIstoryController( AdminHistoryService adminHistoryService ) {
        this.adminHistoryService = adminHistoryService;
    }

    /**
     * 2020.11.18 : 김진호
     * 관리자 접근 기록
     * @param page
     * @param actionVal
     * @param hospitalCdVal
     * @param ipVal
     * @param targetUserIdVal
     * @param userAccntVal
     * @param startDtVal
     * @param endDtVal
     * @return
     */
    @GetMapping("/getAdminAccessHistory")
    public Page<AdminAccessHistoryProjection> getAdminAccessHistory(Pageable page
            , @RequestParam(required=false, defaultValue = "ALL") String actionVal
            , @RequestParam(required=false, defaultValue = "ALL") String hospitalCdVal
            , @RequestParam(required=false, defaultValue = "ALL") String ipVal
            , @RequestParam(required=false, defaultValue = "0") Long targetUserIdVal
            , @RequestParam(required=false, defaultValue = "ALL") String userAccntVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDtVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDtVal ) {
        // 디버그 로그 추가
        log.debug("" +
                        "actionVal : {}" +
                        "hospitalCdVal : {}" +
                        "ipVal : {}" +
                        "targetUserIdVal : {}" +
                        "userAccntVal : {}" +
                        "startDtVal : {}" +
                        "endDtVal : {}",
                actionVal, hospitalCdVal, ipVal, targetUserIdVal, userAccntVal, startDtVal, endDtVal);
        return adminHistoryService.findAdminAccessHistory( page, actionVal, hospitalCdVal, ipVal, targetUserIdVal, userAccntVal, startDtVal, endDtVal);
    }

    /**
     * 2020.11.18 : 김진호
     * 관리자 로그인 기록
     * @param page
     * @param ipVal
     * @param successVal
     * @param userAccntVal
     * @param startDtVal
     * @param endDtVal
     * @return
     */
    @GetMapping("/getAdminLoginHistory")
    public Page<AdminLoginHistoryProjection> getAdminLoginHistory(Pageable page
            , @RequestParam(required=false, defaultValue = "ALL") String ipVal
            , @RequestParam(required=false, defaultValue = "ALL") String successVal
            , @RequestParam(required=false, defaultValue = "ALL") String userAccntVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDtVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDtVal ) {
        // 디버그 로그 추가
        log.debug("" +
                "ipVal : {}" +
                "successVal : {}" +
                "userAccntVal : {}" +
                "startDtVal : {}" +
                "endDtVal : {}",
                ipVal, successVal, userAccntVal, startDtVal, endDtVal);
        return adminHistoryService.findAdminLoginHistory( page, ipVal, successVal, userAccntVal, startDtVal, endDtVal );
    }

    /**
     * 2020.11.18 : 김진호
     * 관리자 민감정보 접근 기록
     * @param page
     * @param userAccntVal
     * @param hospitalCdVal
     * @param serviceVal
     * @param ipVal
     * @param actionVal
     * @param startDtVal
     * @param endDtVal
     * @return
     */
    @GetMapping("/getAdminPrivacyAccess")
    public Page<AdminPrivacyAccessProjection> getAdminPrivacyAccess(Pageable page
            , @RequestParam(required=false, defaultValue = "ALL") String userAccntVal
            , @RequestParam(required=false, defaultValue = "ALL") String hospitalCdVal
            , @RequestParam(required=false, defaultValue = "ALL") String serviceVal
            , @RequestParam(required=false, defaultValue = "ALL") String ipVal
            , @RequestParam(required=false, defaultValue = "ALL") String actionVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String startDtVal
            , @RequestParam(required=false, defaultValue = "1900-01-01") String endDtVal ) {
        // 디버그 로그 추가
        log.debug("" +
                    "userAccntVal : {}" +
                    "hospitalCdVal : {}" +
                    "serviceVal : {}" +
                    "ipVal : {}" +
                    "actionVal : {}" +
                    "startDtVal : {}" +
                    "endDtVal : {}",
                userAccntVal, hospitalCdVal, serviceVal, ipVal, actionVal, startDtVal, endDtVal);
            return adminHistoryService.findAdminPrivacyAccess( page, userAccntVal, hospitalCdVal, serviceVal, ipVal, actionVal, startDtVal, endDtVal );
    }
}
