package com.dlsdlworld.spring.api.repository;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.baserepository.BaseMessageRepository;
import com.dlsdlworld.spring.api.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 */
public interface MessageRepository extends BaseMessageRepository<Message> {

    @Transactional(readOnly=true)
    @LogAdminExecution(descriptions = "다국어 조회")
    @PreAuthorize("@security.hasPermission({'API_READ'})")
    @Query( " select     a  " +
            " from       Message a " +
         //   " WHERE  a.createdOn >= :startDt and a.createdOn < :endDt "  +     // 날짜는 항상 가져오도록 설정
            " WHERE  (1=1) "  +
            " AND (" +
            "        ( a.msgCd like CONCAT('%',:keyword,'%') OR :keyword = '' ) "  +
            "          OR  ( a.msgContent like CONCAT('%',:keyword,'%') OR :keyword = '' )" +
            "     ) " )
    Page<Message> findAllByKeyword(Pageable page,
                                       @RequestParam (required=false, defaultValue = "") String keyword);

  /*  Page<Message> findAllByKeyword(Pageable page,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDt,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime endDt,
                                   @RequestParam (required=false, defaultValue = "") String keyword);
*/

    <T>Iterable<T> findAllByMsgCdContainingAndLangCd(String msgCd, String langCd, Class<T> type);

    <T>Iterable<T> findAllByLangCd(String langCd, Class<T> type);

}

