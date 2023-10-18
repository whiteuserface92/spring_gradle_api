package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.GroupHospitalDto;
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
public class GroupHospitalService {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public Page<GroupHospitalDto> findByAllGroupHospitals(Long groupId, Pageable page) {
        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String countSql = "";
        String querySql = "";

        String selCountSql = " select count(1) ";

        selectSql = "select a.id as id "+
                " ,a.group_id as \"groupId\" "+
                " ,a.hospital_id as \"hospitalId\" "+
                " ,b.hospital_nm as \"hospitalNm\" "+
                " ,b.hospital_nm_cd as \"hospitalNmCd\" "+
                " ,a.ovrd_img_url as \"ovrdImgUrl\" "+
                " ,a.disp_ord as \"dispOrd\" "+
                " ,a.created_by as \"createdBy\" "+
                " ,a.created_on as \"createdOn\" "+
                " ,a.updated_by as \"updatedBy\" "+
                " ,a.updated_on as \"updatedOn\" ";

        fromSql = " from group_mbr a " +
                  " left outer join hospital_mst b on a.hospital_id = b.id ";

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        if(!StringUtils.isEmpty(groupId)) {
            whereSql = " where a.group_id =:groupId ";
        }

        orderSql = " order by a.disp_ord ";

        countSql = selCountSql + fromSql + whereSql;
        querySql = selectSql + fromSql + whereSql + orderSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.
        Query countQuery = entityManager.createNativeQuery(countSql);

        if(!StringUtils.isEmpty(groupId)) {
            query.setParameter("groupId", groupId);
            countQuery.setParameter("groupId", groupId);
        }

        log.trace("### countSql : {}", countSql);
        log.trace("### querySql : {}", querySql);

        // 전체 카운트를 가져온다.
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        int totalCount = ((Number)countQuery.getSingleResult()).intValue();

        List<GroupHospitalDto> groupHospitalDtoList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(GroupHospitalDto.class)).list();

        Page<GroupHospitalDto> pages = null;
        if(groupHospitalDtoList != null) {
            pages = new PageImpl<GroupHospitalDto>(groupHospitalDtoList, page, totalCount);
        }
        return pages;
    }

}
