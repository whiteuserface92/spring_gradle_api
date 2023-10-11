package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.AdminPrivacyAccess;
import com.dlsdlworld.spring.api.projection.AdminPrivacyAccessProjection;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface AdminPrivacyAccessRepository extends BaseAdminPrivacyAccessRepository<AdminPrivacyAccess> {

    /**
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
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    @LogAdminExecution(descriptions = "Admin Privacy Access")
    @Query(
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM (" +
                    "select id           as id                            \n" +
                    "     , user_accnt   as userAccnt                     \n" +
                    "     , hospital_cd  as hospitalCd                    \n" +
                    "     , service      as service                       \n" +
                    "     , action       as action                        \n" +
                    "     , description  as description                   \n" +
                    "     , request      as request                       \n" +
                    "     , result       as result                        \n" +
                    "     , ip           as ip                            \n" +
                    "     , ymd          as ymd                           \n" +
                    "     , created_on   as createdOn                     \n" +
                    "  from admin_privacy_access                          \n" +
                    " where ( case when :userAccntVal = 'ALL' then 1 = 1  \n" +
                    "              else user_accnt = :userAccntVal        \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :hospitalCdVal = 'ALL' then 1 = 1 \n" +
                    "              else hospital_cd = :hospitalCdVal      \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :serviceVal = 'ALL' then 1 = 1    \n" +
                    "              else service = :serviceVal             \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :ipVal = 'ALL' then 1 = 1         \n" +
                    "              else ip = :ipVal                       \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :actionVal = 'ALL' then 1 = 1     \n" +
                    "              else \"action\" = :actionVal           \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                  //  "order by created_on desc                             \n" +
                    ") a",
            value = "select id           as id                            \n" +
                    "     , user_accnt   as userAccnt                     \n" +
                    "     , hospital_cd  as hospitalCd                    \n" +
                    "     , service      as service                       \n" +
                    "     , action       as action                        \n" +
                    "     , description  as description                   \n" +
                    "     , request      as request                       \n" +
                    "     , result       as result                        \n" +
                    "     , ip           as ip                            \n" +
                    "     , ymd          as ymd                           \n" +
                    "     , created_on   as createdOn                     \n" +
                    "  from admin_privacy_access                          \n" +
                    " where ( case when :userAccntVal = 'ALL' then 1 = 1  \n" +
                    "              else user_accnt = :userAccntVal        \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :hospitalCdVal = 'ALL' then 1 = 1 \n" +
                    "              else hospital_cd = :hospitalCdVal      \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :serviceVal = 'ALL' then 1 = 1    \n" +
                    "              else service = :serviceVal             \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :ipVal = 'ALL' then 1 = 1         \n" +
                    "              else ip = :ipVal                       \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when :actionVal = 'ALL' then 1 = 1     \n" +
                    "              else \"action\" = :actionVal           \n" +
                    "          end                                        \n" +
                    "       )                                             \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                        \n" +
                    "       )                                             \n"
                  //  "order by created_on desc                             \n"
    )
    Page<AdminPrivacyAccessProjection> findAdminPrivacyAccess(Pageable page
            , @Param("userAccntVal") String userAccntVal
            , @Param("hospitalCdVal") String hospitalCdVal
            , @Param("serviceVal") String serviceVal
            , @Param("ipVal") String ipVal
            , @Param("actionVal") String actionVal
            , @Param("startDtVal") String startDtVal
            , @Param("endDtVal") String endDtVal );
}
