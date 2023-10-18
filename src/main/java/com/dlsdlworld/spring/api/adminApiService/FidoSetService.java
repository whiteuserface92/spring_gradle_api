package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.FidoHospitalDto;
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

@Slf4j
@Service
public class FidoSetService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Page<FidoHospitalDto> findAllByKeyword(Long hospitalId, Pageable page) {
        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String countSql = "";
        String querySql = "";

        String selCountSql = " select count(1) ";

        selectSql = "select a.id as id "+
                " ,a.tenant_cd as \"tenantCd\" "+
                " ,a.api_key as \"apiKey\" "+
                " ,a.hospital_id as \"hospitalId\" "+
                " ,b.hospital_nm as \"hospitalNm\" "+
                " ,b.hospital_cd as \"hospitalCd\" "+
                " ,b.hospital_nm_cd as \"hospitalNmCd\" "+
                " ,a.created_by as \"createdBy\" "+
                " ,a.created_on as \"createdOn\" "+
                " ,a.updated_by as \"updatedBy\" "+
                " ,a.updated_on as \"updatedOn\" ";

        fromSql = " from fido_set a " +
                  " left outer join hospital_mst b on a.hospital_id = b.id ";

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        if(!StringUtils.isEmpty(hospitalId)) {
            whereSql = " where a.hospital_id =:hospitalId ";
        }

        orderSql = " order by a.id ";

        countSql = selCountSql + fromSql + whereSql;
        querySql = selectSql + fromSql + whereSql + orderSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.
        Query countQuery = entityManager.createNativeQuery(countSql);

        if(!StringUtils.isEmpty(hospitalId)) {
            query.setParameter("hospitalId", hospitalId);
            countQuery.setParameter("hospitalId", hospitalId);
        }

        log.trace("### countSql : {}", countSql);
        log.trace("### querySql : {}", querySql);

        // 전체 카운트를 가져온다.
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        int totalCount = ((Number)countQuery.getSingleResult()).intValue();

        List<FidoHospitalDto> fidoHospitalDtoLists = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FidoHospitalDto.class)).list();

        Page<FidoHospitalDto> pages = null;
        if(fidoHospitalDtoLists != null) {
            pages = new PageImpl<FidoHospitalDto>(fidoHospitalDtoLists, page, totalCount);
        }
        return pages;
    }

}
