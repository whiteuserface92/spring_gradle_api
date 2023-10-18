package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.CompApiInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Service
public class CompApiService {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * 2020.10.23 : 김진호
     * 외부 API 와 연관된 API 전체 정보를 트리구조로 조회한다.
     * @param compType
     * @param insuCompCd
     * @return
     */
    @Transactional
    public String getCompApiInfo(String compType, String insuCompCd) {

        final String selectSql =  "select regexp_replace(concat('{ \"content\" : ',coalesce( json_agg(q), '[]'\\:\\:json)\\:\\:text,'}' ), E'[\\n\\r\\u2028]+', ' ', 'g' ) as result " +
                                  "  from ( " +
                                  "         select ac.id     as \"clsId\"  \n" +
                                  "              , ac.cls_nm as \"clsNm\"  \n" +
                                  "              , ( SELECT COALESCE( Array_to_json(Array_agg(Row_to_json(p))), '[]' ) \n" +
                                  "                    from ( select concat( ad2.api_nm, '|', av2.api_ver, '|', av2.service_url ) as \"apiInfo\"  \n" +
                                  "                                , ad2.api_nm       as \"apiNm\"      \n" +
                                  "                                , av2.api_ver      as \"apiVer\"     \n" +
                                  "                                , av2.service_url  as \"serviceUrl\" \n" +
                                  "                                , ca2.id           as \"compApiId\"  \n" +
                                  "                                , ca2.comp_id      as \"compId\"     \n" +
                                  "                                , ca2.comp_type    as \"compType\"   \n" +
                                  "                                , concat(ca2.id, '_', ca2.comp_id, '_', ca2.comp_type) as \"compApiUId\"  \n" +
                                  "                                , ac.id            as \"parentId\"   \n" +
                                  "                             from api_def ad2 \n" +
                                  "                            inner join api_version av2 on av2.api_id = ad2.id      \n" +
                                  "                            inner join comp_api ca2 on ca2.api_version_id = av2.id \n" +
                                  "                            where ca2.comp_id = im.id                              \n" +
                                  "                         ) p \n" +
                                  "                ) as child   \n" +
                                  "           from insu_mst im  \n" +
                                  "          inner join comp_api ca on ca.comp_id = im.id and ca.comp_type = :compType \n" +
                                  "          inner join api_version av on av.id = ca.api_version_id \n" +
                                  "          inner join api_def ad on ad.id = av.api_id \n" +
                                  "          inner join api_cls ac on ac.id = ad.api_cls_id \n" +
                                  "          where im.insu_comp_cd = :insuCompCd \n" +
                                  "          group by ac.id, im.id \n" +
                                  "       ) q";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(selectSql);
        query.setParameter("compType", compType);
        query.setParameter("insuCompCd", insuCompCd);

        String text = (query.getSingleResult()).toString();

        return text;
    }

    /**
     * 2020.10.23 : 김진호
     * 외부 API 정보
     * @param compId
     * @param compType
     * @param apiVersionId
     * @return
     */
    public Page<CompApiInfoDTO> getCompApi(Long compId, String compType, Long apiVersionId) {

        final String selectSql = "select ca.id                as \"id\"               \n" +
                                 "     , ca.comp_type         as \"compType\"         \n" +
                                 "     , ca.comp_id           as \"compId\"           \n" +
                                 "     , ca.api_version_id    as \"apiVersionId\"     \n" +
                                 "     , ca.io_type           as \"ioType\"           \n" +
                                 "     , ca.header_enabled    as \"headerEnabled\"    \n" +
                                 "     , ca.interface_desc    as \"interfaceDesc\"    \n" +
                                 "     , ca.created_by        as \"createdBy\"        \n" +
                                 "     , ca.created_on        as \"createdOn\"        \n" +
                                 "     , ca.tcp_reuse_enabled as \"tcpReuseEnabled\"  \n" +
                                 "     , ca.character_set     as \"characterSet\"     \n" +
                                 "     , ca.header_cnt        as \"headerCnt\"        \n" +
                                 "     , ca.field_ids         as \"fieldIds\"         \n" +
                                 "     , ( SELECT Coalesce(Array_to_json(Array_agg(Row_to_json(p))), '[]') \n" +
                                 "           FROM ( select *\n" +
                                 "                    from comp_param_cnstr cpc    \n" +
                                 "                   where cpc.comp_api_id = ca.id \n" +
                                 "                ) p \n" +
                                 "       )\\:\\:text as \"compParamCnstrs\"            \n" +
                                 "  from comp_api ca \n" +
                                 " where ca.comp_id = :compId\n" +
                                 "   and ca.comp_type = :compType\n" +
                                 "   and ca.api_version_id = :apiVersionId  ";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(selectSql);
        // 전체 카운트를 구한다.

        query.setParameter("compId", compId);
        query.setParameter("compType", compType);
        query.setParameter("apiVersionId", apiVersionId);


        // 현재 리스트를 가져온다.
        List<CompApiInfoDTO> compApiInfo = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(CompApiInfoDTO.class)).list();

//        List<CompApiInfoDTO> compApiInfo = query.getResultList();

        Pageable page = PageRequest.of(0,10);
        Page<CompApiInfoDTO> pages = null;

        if(compApiInfo != null){
            pages = new PageImpl<CompApiInfoDTO>(compApiInfo, page, 1);
        }

        return pages;
    }
}

