package com.dlsdlworld.spring.api.adminApiService;


import com.dlsdlworld.spring.api.projection.AdminAccessHistoryProjection;
import com.dlsdlworld.spring.api.projection.AdminLoginHistoryProjection;
import com.dlsdlworld.spring.api.projection.AdminPrivacyAccessProjection;
import com.dlsdlworld.spring.api.repository.AdminAccessHistoryRepository;
import com.dlsdlworld.spring.api.repository.AdminLoginHistoryRepository;
import com.dlsdlworld.spring.api.repository.AdminPrivacyAccessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminHistoryService {


    private final AdminAccessHistoryRepository adminAccessHistoryRepository;
    private final AdminLoginHistoryRepository adminLoginHistoryRepository;
    private final AdminPrivacyAccessRepository adminPrivacyAccessRepository;

    public AdminHistoryService( AdminAccessHistoryRepository adminAccessHistoryRepository
            , AdminLoginHistoryRepository adminLoginHistoryRepository
            , AdminPrivacyAccessRepository adminPrivacyAccessRepository ) {
        this.adminAccessHistoryRepository = adminAccessHistoryRepository;
        this.adminLoginHistoryRepository = adminLoginHistoryRepository;
        this.adminPrivacyAccessRepository = adminPrivacyAccessRepository;
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
    public Page<AdminAccessHistoryProjection> findAdminAccessHistory(Pageable page
            , String actionVal
            , String hospitalCdVal
            , String ipVal
            , Long targetUserIdVal
            , String userAccntVal
            , String startDtVal
            , String endDtVal ) {
        return adminAccessHistoryRepository.findAdminAccessHistory( page, actionVal, hospitalCdVal, ipVal, targetUserIdVal, userAccntVal, startDtVal, endDtVal);
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
    public Page<AdminLoginHistoryProjection> findAdminLoginHistory(Pageable page
            , String ipVal
            , String successVal
            , String userAccntVal
            , String startDtVal
            , String endDtVal ) {
        return adminLoginHistoryRepository.findAdminLoginHistory( page, ipVal, successVal, userAccntVal, startDtVal, endDtVal );
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
    public Page<AdminPrivacyAccessProjection> findAdminPrivacyAccess(Pageable page
            , String userAccntVal
            , String hospitalCdVal
            , String serviceVal
            , String ipVal
            , String actionVal
            , String startDtVal
            , String endDtVal ) {
        return adminPrivacyAccessRepository.findAdminPrivacyAccess( page, userAccntVal, hospitalCdVal, serviceVal, ipVal, actionVal, startDtVal, endDtVal );
    }
}
