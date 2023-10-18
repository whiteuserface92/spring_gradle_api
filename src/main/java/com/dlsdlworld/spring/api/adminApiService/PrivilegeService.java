package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.PrivilegeDTO;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PrivilegeService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @SuppressWarnings("unchecked")
    public Page<PrivilegeDTO> getPrivilegeList(LocalDateTime startDt, LocalDateTime endDt, String keyword, Pageable page) {

        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String countSql = "";
        String querySql = "";

        String selCountSql = " select count(1) ";

        selectSql = " select a.id as id " +
                " , a.privilege_nm as \"privilegeNm\" " +
                " , a.privilege_desc as \"privilegeDesc\" " +
                " , a.created_by as \"createdBy\" " +
                " , a.created_on as \"createdOn\" ";

        fromSql = " from privilege_def a ";

        if(StringUtils.isEmpty(startDt) && StringUtils.isEmpty(endDt)) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime before = LocalDateTime.now().minusMonths(3);
        }

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " where a.created_on between :startDt and :endDt ";

        // 키워드 검색이므로 반드시 둘다 넘어오도록 되어 있음
        if(!StringUtils.isEmpty(keyword)) {
            whereSql += " and (a.privilege_nm like CONCAT('%',:keyword,'%') " +
                    " or a.privilege_desc like CONCAT('%',:keyword,'%')) ";
        }

        orderSql = " order by a.id desc ";

        countSql = selCountSql + fromSql + whereSql;
        querySql = selectSql + fromSql + whereSql + orderSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.
        Query countQuery = entityManager.createNativeQuery(countSql);

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        countQuery.setParameter("startDt", startDt);
        countQuery.setParameter("endDt", endDt);

        if(!StringUtils.isEmpty(keyword)) {

            query.setParameter("keyword", keyword).setParameter("keyword", keyword);
            // query.setParameter("domainDesc", keyword);
            countQuery.setParameter("keyword", keyword).setParameter("keyword", keyword);
            // countQuery.setParameter("domainDesc", keyword);
        }

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        // 전체 카운트를 가져온다.
        int totalCount = ((Number)countQuery.getSingleResult()).intValue();
        // 현재 리스트를 가져온다.
        List<PrivilegeDTO> privilegeList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PrivilegeDTO.class)).list();

        Page<PrivilegeDTO> pages = null;
        //List<DomainDic> products = nativeQuery.getResultList();
        if(privilegeList != null){
            pages = new PageImpl<PrivilegeDTO>(privilegeList, page, totalCount);
        }

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return pages;

    }

}
