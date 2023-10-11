package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.model.AdminLoginHistory;
import com.dlsdlworld.spring.api.projection.AdminLoginHistoryProjection;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 */
public interface AdminLoginHistoryRepository extends BaseAdminLoginHistoryRepository<AdminLoginHistory> {

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
    @PreAuthorize("@security.hasPermission({'USER_READ'})")
    @LogAdminExecution(descriptions = "Admin Login History")
    @Query(
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM (" +
                    "select id         as id                                                   \n" +
                    "     , user_accnt as userAccnt                                            \n" +
                    "     , success    as success                                              \n" +
                    "     , details    as details                                              \n" +
                    "     , ip         as ip                                                   \n" +
                    "     , ymd        as ymd                                                  \n" +
                    "     , created_on as createdOn                                            \n" +
                    "  from admin_login_history                                                \n" +
                    " where ( case when :ipVal = 'ALL' then 1 = 1                              \n" +
                    "              else ip = :ipVal                                            \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when :successVal = 'ALL' then 1 = 1                         \n" +
                    "              else success = :successVal                                  \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when :userAccntVal = 'ALL' then 1 = 1                       \n" +
                    "              else user_accnt = :userAccntVal                             \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                 //   "order by created_on desc                                                  \n" +
                    ") a",
            value = "select id         as id                                                   \n" +
                    "     , user_accnt as userAccnt                                            \n" +
                    "     , success    as success                                              \n" +
                    "     , details    as details                                              \n" +
                    "     , ip         as ip                                                   \n" +
                    "     , ymd        as ymd                                                  \n" +
                    "     , created_on as createdOn                                            \n" +
                    "  from admin_login_history                                                \n" +
                    " where ( case when :ipVal = 'ALL' then 1 = 1                              \n" +
                    "              else ip = :ipVal                                            \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when :successVal = 'ALL' then 1 = 1                         \n" +
                    "              else success = :successVal                                  \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when :userAccntVal = 'ALL' then 1 = 1                       \n" +
                    "              else user_accnt = :userAccntVal                             \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n" +
                    "   and ( case when ( :startDtVal = '1900-01-01' or :endDtVal = '1900-01-01' ) then 1 = 1               \n" +
                    "              else ymd between to_date(:startDtVal, 'YYYY-MM-DD') and to_date(:endDtVal, 'YYYY-MM-DD') \n" +
                    "          end                                                             \n" +
                    "       )                                                                  \n"
                 //   "order by created_on desc                                                  \n"
    )
    Page<AdminLoginHistoryProjection> findAdminLoginHistory(Pageable page
            , @Param("ipVal") String ipVal
            , @Param("successVal") String successVal
            , @Param("userAccntVal") String userAccntVal
            , @Param("startDtVal") String startDtVal
            , @Param("endDtVal") String endDtVal
    );
}
