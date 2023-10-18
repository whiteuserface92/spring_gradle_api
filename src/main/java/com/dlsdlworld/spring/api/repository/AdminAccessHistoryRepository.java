package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.baserepository.BaseAdminAccessHistoryRepository;
import com.dlsdlworld.spring.api.model.AdminAccessHistory;
import com.dlsdlworld.spring.api.projection.AdminAccessHistoryProjection;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface AdminAccessHistoryRepository extends BaseAdminAccessHistoryRepository<AdminAccessHistory> {

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
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    @LogAdminExecution(descriptions = "Admin Access History")
    @Query(
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM (                                              \n " +
                    "select id             as id                                              \n" +
                    "     , action         as action                                          \n" +
                    "     , description    as description                                     \n" +
                    "     , hospital_cd    as hospitalCd                                      \n" +
                    "     , ip             as ip                                              \n" +
                    "     , target_user_id as targetUserId                                    \n" +
                    "     , user_accnt     as userAccnt                                       \n" +
                    "     , ymd            as ymd                                             \n" +
                    "     , created_on     as createdOn                                       \n" +
                    "  from admin_access_history                                              \n" +
                    " where ( case when :actionVal = 'ALL' then 1 = 1                         \n" +
                    "              else action = :actionVal                                   \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :hospitalCdVal = 'ALL' then 1 = 1                     \n" +
                    "              else hospital_cd = :hospitalCdVal                          \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :ipVal = 'ALL' then 1 = 1                             \n" +
                    "              else ip = :ipVal                                           \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :targetUserIdVal = 0 then 1 = 1                       \n" +
                    "              else target_user_id = :targetUserIdVal                     \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :userAccntVal = 'ALL' then 1 = 1                      \n" +
                    "              else user_accnt = :userAccntVal                            \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                //    " order by created_on desc                                                \n" +
                    ") a                                                                        ",
            value = "select id             as id                                              \n" +
                    "     , action         as action                                          \n" +
                    "     , description    as description                                     \n" +
                    "     , hospital_cd    as hospitalCd                                      \n" +
                    "     , ip             as ip                                              \n" +
                    "     , target_user_id as targetUserId                                    \n" +
                    "     , user_accnt     as userAccnt                                       \n" +
                    "     , ymd            as ymd                                             \n" +
                    "     , created_on     as createdOn                                       \n" +
                    "  from admin_access_history                                              \n" +
                    " where ( case when :actionVal = 'ALL' then 1 = 1                         \n" +
                    "              else action = :actionVal                                   \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :hospitalCdVal = 'ALL' then 1 = 1                     \n" +
                    "              else hospital_cd = :hospitalCdVal                          \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :ipVal = 'ALL' then 1 = 1                             \n" +
                    "              else ip = :ipVal                                           \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :targetUserIdVal = 0 then 1 = 1                       \n" +
                    "              else target_user_id = :targetUserIdVal                     \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when :userAccntVal = 'ALL' then 1 = 1                      \n" +
                    "              else user_accnt = :userAccntVal                            \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                                            \n" +
                    "       )                                                                 \n"
                   // " order by created_on desc                                                \n"
    )
    Page<AdminAccessHistoryProjection> findAdminAccessHistory(Pageable page
            , @Param("actionVal") String actionVal
            , @Param("hospitalCdVal") String hospitalCdVal
            , @Param("ipVal") String ipVal
            , @Param("targetUserIdVal") Long targetUserIdVal
            , @Param("userAccntVal") String userAccntVal
            , @Param("startDtVal") String startDtVal
            , @Param("endDtVal") String endDtVal
    );
}
