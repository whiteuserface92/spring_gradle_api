package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.CsCenterDto;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hskim
 * Date : 2020/07/09
 * Time : 9:44 오전
 */
@Slf4j
@Service
public class UserInfoForCsCenterService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Page<CsCenterDto> getUserInfo(String keyword, String birthDay, Pageable page) {

        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String countSql = "";
        String querySql = "";


        String selCountSql = " select count(1) ";

        selectSql = " select a.hospital_id as \"hospitalId\", " +
                " b.hospital_cd as \"hospitalCd\", " +
                " c.hospital_nm as \"hospitalNm\", " +
                " a.patient_nm as \"patientNm\", " +
                " a.patient_no as \"patientId\", " +
                " b.phone_no as \"phoneNo\", " +
                " a.user_id as \"userId\", " +
                " b.enabled as \"enabled\", " +
                " b.user_nm as \"userNm\", " +
                " b.birthday as \"birthDay\", " +
                " b.sex as \"sex\", " +
                " b.email as \"email\", " +
                " b.country_cd as \"countryCd\", " +
                " b.created_on as \"createdOn\", " +
                " b.join_type as \"joinType\" ";

        fromSql = " from user_patient a " +
                " left outer join user_mst b on a.user_id = b.id " +
                " inner join hospital_mst c on a.hospital_id = c.id ";

        whereSql = " where birthday = :birthDay " +
                " and (patient_nm like :patientNm or patient_no like :patientNo) ";


        countSql = selCountSql + fromSql + whereSql;
        querySql = selectSql + fromSql + whereSql;

        Query query = entityManager.createNativeQuery(querySql);
        Query countQuery = entityManager.createNativeQuery(countSql);

        log.trace("### countSql : {}", countSql);
        log.trace("### querySql : {}", querySql);

        query.setParameter("birthDay", birthDay);
        countQuery.setParameter("birthDay", birthDay);


        if(!StringUtils.isEmpty(keyword)) {
            query.setParameter("patientNm", keyword).setParameter("patientNo", keyword);
            countQuery.setParameter("patientNm", keyword).setParameter("patientNo", keyword);
        }

        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        int totalCount = ((Number)countQuery.getSingleResult()).intValue();

        List<CsCenterDto> userList = query.unwrap(SQLQuery.class)
            .setResultTransformer(Transformers.aliasToBean(CsCenterDto.class)).list();

        Page<CsCenterDto> pages = null;
        if(userList != null){
            pages = new PageImpl<CsCenterDto>(userList, page, totalCount);
        }
        return pages;
    }

}
