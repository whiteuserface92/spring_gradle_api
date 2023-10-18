package com.dlsdlworld.spring.api.adminApiService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;

@Slf4j
@Service
public class StatService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public String getMenuStat(LocalDateTime startDt, LocalDateTime endDt ) {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String selectSql = "";
        String whereSql = "";
        String querySql = "";

        // ko_kr로 넘어오는 경우 ko로 변환
        if("ko_kr".equals(lang.toLowerCase()))
        {
            lang = "ko";
        }

        log.info("locale : " +lang);

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " and a.created_on between :startDt and :endDt ";

        selectSql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text " +
        " from " +
                " (select ymd," +
        "                 hospital_id as \"hospitalId\", "+
        "                 hospital_cd as \"hospitalCd\"," +
        "                 hospital_nm as \"hospitalNm\"," +
        "                 (select msg_content from i18n_msg aa where msg_cd = r.menu_name_cd and aa.lang_cd = '" +lang+ "' ) as \"menuNameCd\", " +
        "                 (select msg_content from i18n_msg aa where msg_cd = r.ovrd_name_cd and aa.lang_cd = '" +lang+ "' ) as \"ovrdNameCd\"," +
        "                 cnt" +
        "          from" +
        "               (select ymd, " +
        "                       hospital_id, " +
        "                       hospital_cd, " +
        "                       hospital_nm, " +
        "                       (select aa.name_cd from menu_def aa where aa.id = t.menu_id) as menu_name_cd, " +
        "                       case when ovrd_name_cd = '' then name_cd else ovrd_name_cd end as ovrd_name_cd, " +
        //                      -- name_cd,
        //                      -- ovrd_name_cd,
        "                       cnt " +
        "                from " +
        "                   ( " +
        "                           SELECT (a.created_on\\:\\:DATE)\\:\\:text as ymd, " +
        "                                   a.hospital_id, " +
        "                                   c.hospital_cd, " +
        "                                   c.hospital_nm, " +
        "                                   a.menu_id, " +
        "                                   a.hospital_menu_id, " +
        "                                   d.name_cd, " +
        "                                   coalesce(b.ovrd_name_cd,'') as ovrd_name_cd, " +
        "                                   count(*) as cnt " +
        "                           FROM app_menu_log a " +
        "                           INNER JOIN hospital_menu b on a.hospital_menu_id = b.id " +
        "                           INNER JOIN hospital_mst c on b.hospital_id = c.id " +
        "                           INNER JOIN menu_def d on b.menu_id = d.id " +
//        " LEFT OUTER JOIN i18n_msg e ON d.name_cd = e.msg_cd and e.lang_cd = 'ko' " +
//        " LEFT OUTER JOIN i18n_msg f ON b.ovrd_name_cd = e.msg_cd and e.lang_cd = 'ko' " +
        "                           WHERE a.log_type <> 99 " +
        "                           AND   a.menu_id is not null " +
        whereSql +
        "                           group by  (a.created_on\\:\\:DATE)\\:\\:text, " +
        "                           a.hospital_id, " +
        "                           c.hospital_cd, " +
        "                           c.hospital_nm, " +
        "                           a.menu_id, " +
        "                           a.hospital_menu_id, " +
        "                           d.name_cd, " +
        "                           coalesce(b.ovrd_name_cd,'') " +
        "                     ) t order by ymd asc, cnt desc " +
        "               ) r  order by ymd asc, cnt desc " +
        "       ) q ";

        querySql = selectSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        // 전체 카운트를 가져온다.
        // 현재 리스트를 가져온다.
        /*List<PushMessageDTO> pushList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PushMessageDTO.class)).list();*/

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return result;

    }

    @Deprecated
    @Transactional
    public String getPeriodMenuStat(LocalDateTime startDt, LocalDateTime endDt ) {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String selectSql = "";
        String whereSql = "";
        String querySql = "";

        // ko_kr로 넘어오는 경우 ko로 변환
        if("ko_kr".equals(lang.toLowerCase()))
        {
            lang = "ko";
        }

        log.info("locale : " +lang);

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " and a.created_on between :startDt and :endDt ";

        selectSql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text " +
                " from " +
                " (select " +
                "                 hospital_id as \"hospitalId\", "+
                "                 hospital_cd as \"hospitalCd\"," +
                "                 hospital_nm as \"hospitalNm\"," +
                "                 (select msg_content from i18n_msg aa where msg_cd = r.menu_name_cd and aa.lang_cd = '" +lang+ "' ) as \"menuNameCd\", " +
                "                 (select msg_content from i18n_msg aa where msg_cd = r.ovrd_name_cd and aa.lang_cd = '" +lang+ "' ) as \"ovrdNameCd\"," +
                "                 cnt" +
                "          from" +
                "               (select  " +
                "                       hospital_id, " +
                "                       hospital_cd, " +
                "                       hospital_nm, " +
                "                       (select aa.name_cd from menu_def aa where aa.id = t.menu_id) as menu_name_cd, " +
                "                       case when ovrd_name_cd = '' then name_cd else ovrd_name_cd end as ovrd_name_cd, " +
                //                      -- name_cd,
                //                      -- ovrd_name_cd,
                "                       cnt " +
                "                from " +
                "                   ( " +
                "                           SELECT  " +
                "                                   a.hospital_id, " +
                "                                   c.hospital_cd, " +
                "                                   c.hospital_nm, " +
                "                                   a.menu_id, " +
                "                                   a.hospital_menu_id, " +
                "                                   d.name_cd, " +
                "                                   coalesce(b.ovrd_name_cd,'') as ovrd_name_cd, " +
                "                                   count(*) as cnt " +
                "                           FROM app_menu_log a " +
                "                           INNER JOIN hospital_menu b on a.hospital_menu_id = b.id " +
                "                           INNER JOIN hospital_mst c on b.hospital_id = c.id " +
                "                           INNER JOIN menu_def d on b.menu_id = d.id " +
//        " LEFT OUTER JOIN i18n_msg e ON d.name_cd = e.msg_cd and e.lang_cd = 'ko' " +
//        " LEFT OUTER JOIN i18n_msg f ON b.ovrd_name_cd = e.msg_cd and e.lang_cd = 'ko' " +
                "                           WHERE a.log_type <> 99 " +
                "                           AND   a.menu_id is not null " +
                whereSql +
                //"                           group by  (a.created_on\\:\\:DATE)\\:\\:text, " +
                "                           group by a.hospital_id, " +
                "                           c.hospital_cd, " +
                "                           c.hospital_nm, " +
                "                           a.menu_id, " +
                "                           a.hospital_menu_id, " +
                "                           d.name_cd, " +
                "                           coalesce(b.ovrd_name_cd,'') " +
                "                     ) t order by cnt desc " +
                "               ) r  order by cnt desc " +
                "       ) q ";

        querySql = selectSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        // 전체 카운트를 가져온다.
        // 현재 리스트를 가져온다.
        /*List<PushMessageDTO> pushList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PushMessageDTO.class)).list();*/

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return result;

    }

    /**
     * 2020.09.10 : 김진호
     * 통계 출력 쿼리 수정
     * getPeriodMenuStat 의 살리기 위해서 추가로 생성하였다!
     *
     * @param startDt
     * @param endDt
     * @return
     */
    @Transactional
    public String getPeriodMenuStat2(String hospitalCd, LocalDateTime startDt, LocalDateTime endDt ) {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String querySql = "";

        // ko_kr로 넘어오는 경우 ko로 변환
        if("ko_kr".equals(lang.toLowerCase()))
        {
            lang = "ko";
        }

        log.info("locale : " +lang);

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        querySql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text " +
                " from ( select ( select hm.id from hospital_mst hm where hm.hospital_cd = sm.hospital_cd) as \"hospitalId\" \n" +
                "     , hospital_cd as \"hospitalCd\"\n" +
                "     , hospital_nm as \"hospitalNm\"\n" +
                "     , ( select msg_content from i18n_msg where msg_cd = menu_cd and lang_cd = '" + lang + "' ) as \"menuNameCd\" \n" +
                "     , ( select msg_content from i18n_msg where msg_cd = hospital_menu_cd and lang_cd = '" + lang + "' ) as \"ovrdNameCd\" \n" +
                "     , sum( cnt ) as \"cnt\" \n" +
                "  from lemon_eis.stat_menu sm \n" +
                " where 1 = 1\n" +
                "   and stat_dt between :startDt and :endDt\n" ;
                if(StringUtils.isNotEmpty(hospitalCd)) {
                   querySql +=   "   and hospital_cd  = '" + hospitalCd + "' \n" ;
                }
                querySql += " group by hospital_cd , hospital_nm , menu_cd, hospital_menu_cd \n" +
                            "order by sum( cnt )  desc ) q ";


        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();

        return result;
    }

    @Deprecated
    @Transactional
    @SuppressWarnings("unchecked")
    public String getUserStat(LocalDateTime startDt, LocalDateTime endDt) {
        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String querySql = "";

/*        whereSql = " WHERE  a.created_on >= '2020-07-01' " +
                " AND    a.created_on < '2020-07-17' ";*/

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " and a.created_on between :startDt and :endDt ";

        selectSql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text "+
        " from "+
        " ( "+
        " SELECT * "+
        " FROM "+
        "      (SELECT concat(a.created_on\\:\\:DATE,' 24:00:00')\\:\\:text as ymd, "+
        "              a.hospital_id as \"menuHospitalId\", "+   //-- plus쪽에서 넘겨주는 병원구분 값 (넘어와야 병원구분이 가능하다)
        "              b.hospital_id as \"hospitalId\", "+  //-- 병원메뉴에 맵핑되어 있는 병원아이디
        "              count(*) as cnt "+
        "       FROM   app_menu_log a "+
        "       INNER JOIN hospital_menu b on a.hospital_menu_id = b.id "+
        "       INNER JOIN hospital_mst d on b.hospital_id = d.id "+
        "       WHERE    a.log_type <> 99 "+
        "       AND    a.menu_id is not null "+
        whereSql +
        "       group by  a.created_on\\:\\:DATE, "+
        "       a.hospital_id, "+
        "       b.hospital_id "+
        "      ) T  order by ymd asc, cnt desc "+
        " ) q ";

        querySql = selectSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        // 전체 카운트를 가져온다.
        // 현재 리스트를 가져온다.
        /*List<PushMessageDTO> pushList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PushMessageDTO.class)).list();*/

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return result;
    }

    /**
     * 2020.09.11 : 김진호
     * 사용자통계 출력 쿼리 수정
     * getUserStat 살리기 위해서 추가로 생성하였다!
     *
     * @param startDt
     * @param endDt
     * @return
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public String getUserStat2(LocalDateTime startDt, LocalDateTime endDt) {

        String querySql = "";

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        querySql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text "+
                   " from                                              \n"+
                   " (select stat_dt          as \"ymd\"               \n" +
                   "       , ''               as \"menuHospitalId\"    \n" +
                   "       , hospital_cd      as \"hospitalId\"        \n" +
                   "       , sum( cnt )       as \"cnt\"               \n" +
                   "    from lemon_eis.stat_menu sm                    \n" +
                   "   where stat_dt between :startDt and :endDt       \n" +
                   "   group by stat_dt, hospital_cd                   \n" +
                   "   order by stat_dt asc, sum( cnt ) asc ) q          ";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        return result;
    }

    @Transactional
    public String getMenuStatBak(LocalDateTime startDt, LocalDateTime endDt ) {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String selectSql = "";
        String whereSql = "";
        String querySql = "";

        // ko_kr로 넘어오는 경우 ko로 변환
        if("ko_kr".equals(lang.toLowerCase()))
        {
            lang = "ko";
        }

        log.info("locale : " +lang);

/*        whereSql = " WHERE  a.created_on >= '2020-07-01' " +
                " AND    a.created_on < '2020-07-17' ";*/

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " and a.created_on between :startDt and :endDt ";

        selectSql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text " +
                " from " +
                " ( " +
                " SELECT *, " +
                " (select coalesce(ARRAY_TO_JSON(ARRAY_AGG(ROW_TO_JSON(p))),'[]') " +
                " from (select *, " +
                " (select coalesce(ARRAY_TO_JSON(ARRAY_AGG(ROW_TO_JSON(p))),'[]') " +
                " from (select * " +
                " from hospital_mst dd " +
                " where cc.hospital_id = dd.id "+
                " ) p  ) as hospitalInfo " +
                " from hospital_menu cc " +
                " where cc.parent_id = t.\"hospitalMenuId\" " +
                " ) p  ) as menuTreeId " +
                " FROM " +
                " (SELECT concat(a.created_on\\:\\:DATE,' 24:00:00')\\:\\:text as ymd, "+
                " c.id as \"menuId\", "+
                " a.hospital_id as \"menuHospitalId\", " +   //plus쪽에서 넘겨주는 병원구분 값 (넘어와야 병원구분이 가능하다)
                " b.hospital_id as \"hospitalId\", " +             //병원메뉴에 맵핑되어 있는 병원아이디
                " a.hospital_menu_id as \"hospitalMenuId\", " +
                " d.hospital_nm as \"hospitalNm\", " +
                " d.hospital_cd as \"hospitalCd\", " +
                " c.service_url as \"serviceUrl\", " +
                " c.menu_desc as \"menuDesc\", " +
                " COALESCE(f.msg_cd, e.msg_cd) as \"menuCd\", " +
                " COALESCE(f.msg_content, e.msg_content) as \"menuNm\", " +
                // --           b.ovrd_img_url,
                // --           b.ovrd_service_url,
                " count(*) as cnt " +
                " FROM   app_menu_log a " +
                " INNER JOIN hospital_menu b on a.hospital_menu_id = b.id " +
                " INNER JOIN hospital_mst d on b.hospital_id = d.id " +
                " INNER JOIN menu_def c on b.menu_id = c.id " +
                " LEFT OUTER JOIN i18n_msg e on e.msg_cd = c.name_cd and e.lang_cd = 'ko' " +
                " LEFT OUTER JOIN i18n_msg f on f.msg_cd = b.ovrd_name_cd and e.lang_cd = 'ko' " +
                " WHERE    a.log_type <> 99 " +
                " AND    a.menu_id is not null " +
                whereSql +
                " group by  a.created_on\\:\\:DATE, " +
                "         c.id, " +
                //        --           a.device_type,
                //        --           b.menu_type,
                "        a.hospital_id, " +
                "        b.hospital_id, " +
                "        a.hospital_menu_id, " +
                "        d.hospital_nm, " +
                "        d.hospital_cd, " +
                "        c.service_url, " +
                "        c.menu_desc, " +
                //        --            b.ovrd_img_url,
                //        --            b.ovrd_service_url,
                "        COALESCE(f.msg_cd, e.msg_cd), " +
                "        COALESCE(f.msg_content, e.msg_content) " +
                ") T order by T.ymd asc " +
                "            ) q ";

        querySql = selectSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        // 전체 카운트를 가져온다.
        // 현재 리스트를 가져온다.
        /*List<PushMessageDTO> pushList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PushMessageDTO.class)).list();*/

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return result;

    }

    @Transactional
    @SuppressWarnings("unchecked")
    public String getUserHourlyStat(LocalDateTime startDt, LocalDateTime endDt) {
        String selectSql = "";
        String fromSql = "";
        String whereSql = "";
        String orderSql = "";
        String querySql = "";

/*        whereSql = " WHERE  a.created_on >= '2020-07-01' " +
                " AND    a.created_on < '2020-07-17' ";*/

        // 날짜는 무조건 조건절에서 조회되도록 한다.
        whereSql = " and a.created_on between :startDt and :endDt ";

        selectSql = " SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text "+
                " from "+
                " ( "+
                " SELECT * "+
                " FROM "+
                "      (SELECT concat(a.created_on\\:\\:DATE,' 24:00:00')\\:\\:text as ymd, "+
                "      to_char(a.created_on,'HH') as hours , " +
                "              a.hospital_id as \"menuHospitalId\", "+   //-- plus쪽에서 넘겨주는 병원구분 값 (넘어와야 병원구분이 가능하다)
                "              b.hospital_id as \"hospitalId\", "+  //-- 병원메뉴에 맵핑되어 있는 병원아이디
                "              count(*) as cnt "+
                "       FROM   app_menu_log a "+
                "       INNER JOIN hospital_menu b on a.hospital_menu_id = b.id "+
                "       INNER JOIN hospital_mst d on b.hospital_id = d.id "+
                "       WHERE    a.log_type <> 99 "+
                "       AND    a.menu_id is not null "+
                whereSql +
                "       group by  a.created_on\\:\\:DATE, "+
                "                 to_char(a.created_on,'HH'), " +
                "       a.hospital_id, "+
                "       b.hospital_id "+
                "      ) T  order by ymd asc, hours asc, cnt desc "+
                " ) q ";

        querySql = selectSql;

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);

        String result = query.getSingleResult().toString();
        // 전체 카운트를 가져온다.
        // 현재 리스트를 가져온다.
        /*List<PushMessageDTO> pushList = query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(PushMessageDTO.class)).list();*/

        /*
        if(query != null) {
            query = null;
        }
        if(countQuery != null) {
            countQuery = null;
        }
        */

        return result;
    }

    @Transactional
    public String getLoginLogStat(LocalDateTime startDt, LocalDateTime endDt, String platformType, int hospitalId ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text \n" +
                          "  from \n" +
                          "       ( \n" +
                          "         SELECT leged_on \n" +
                          "              , service_nm \n" +
                          "              , ( CASE WHEN platform_type = 'ANDROID' THEN platform_type \n" +
                          "                       WHEN platform_type = 'IOS' THEN platform_type \n" +
                          "                       ELSE 'ETC' \n" +
                          "                   END \n" +
                          "                ) AS platform_type \n" +
                          "              , hospital_id \n" +
                          "              , hospital_cd \n" +
                          "              , hospital_nm \n" +
                          "              , cnt \n" +
                          "           FROM ( SELECT loged_on\\:\\:date as leged_on , service_nm , hospital_id , platform_type , hospital_cd , hospital_nm , count( 1 ) AS cnt \n" +
                          "                    FROM lemon_eis.stat_login \n" +
                          "                   WHERE loged_on BETWEEN :startDt AND :endDt \n" +
                          "                     AND ( CASE WHEN 'ALL' = :platformType THEN 'ALL' = :platformType \n" +
                          "                                ELSE platform_type = :platformType \n" +
                          "                            END \n" +
                          "                         ) \n" +
                          "                     AND ( CASE WHEN 0 = :hospitalId THEN 0 = :hospitalId \n" +
                          "                                ELSE hospital_id = :hospitalId \n" +
                          "                            END \n" +
                          "                         ) \n" +
                          "                   GROUP BY loged_on\\:\\:date , service_nm , platform_type , hospital_id , hospital_cd , hospital_nm \n" +
                         "                ) AA \n" +
                          "         ORDER BY leged_on ASC \n" +
                          "       ) q ";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        query.setParameter("platformType", platformType);
        query.setParameter("hospitalId", hospitalId);

        String result = query.getSingleResult().toString();

        return result;
    }

    /**
     * 2020.10.19 : 김진호
     * 일별 사용자 방문 플랫폼 현황
     * @param startDt
     * @param endDt
     * @param hospitalId
     * @return
     */
    @Transactional
    public String getLoginpPlatformType(LocalDateTime startDt, LocalDateTime endDt, int hospitalId ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text          \n" +
                          "  from                                                          \n" +
                          "       (                                                        \n" +
                          "         select loged_on      as \"logedOn\"                    \n" +
                          "              , platform_type as \"platformType\"               \n" +
                          "              , hospital_cd   as hospitalCd                     \n" +
                          "              , hospital_id   as hospitalId                     \n" +
                          "              , cnt           as \"cnt\"                        \n" +
                          "           from ( select loged_on\\:\\:date                     \n" +
                          "                       , platform_type                          \n" +
                          "                       , hospital_cd                            \n" +
                          "                       , hospital_id                            \n" +
                          "                       , count(1) as cnt                        \n" +
                          "                    from lemon_eis.stat_login                   \n" +
                          "                   where loged_on between :startDt and :endDt   \n" +
                          "                     and ( case when 0 = :hospitalId then 0 = :hospitalId \n" +
                          "                                else hospital_id = :hospitalId  \n" +
                          "                            end                                 \n" +
                          "                         )                                      \n" +
                          "                    group by loged_on\\:\\:date, platform_type, hospital_cd , hospital_id \n" +
                          "                ) qq                                            \n" +
                          "           order by qq.loged_on asc                             \n" +
                          "       ) q                                                      \n";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        query.setParameter("hospitalId", hospitalId);

        String result = query.getSingleResult().toString();

        return result;
    }

    /**
     * 2020.10.19 : 김진호
     * 월별 누적 사용자 현황
     * @param startMm
     * @param endMm
     * @param platformType
     * @param hospitalId
     * @param serviceNmAllSum
     * @param platformTypeAllSum
     * @return
     */
    @Transactional
    public String getMonthlyLoginLogtat( String startMm, String endMm, String platformType, int hospitalId, String serviceNmAllSum, String platformTypeAllSum ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text          \n" +
                          "  from                                                          \n" +
                          "       (                                                        \n" +
                          "         select a.loged_on       as \"logedOn\" \n" +
                          "              , a.service_nm     as \"serviceNm\" \n" +
                          "              , a.platform_type  as \"platformType\" \n" +
                          "              , a.hospital_id    as \"hospitalId\" \n" +
                          "              , a.hospital_cd    as \"hospitalCd\" \n" +
                          "              , a.hospital_nm    as \"hospitalNm\" \n" +
                          "              , sum(a.cnt)       as \"cnt\" \n" +
                          "           from ( \n" +
                          "                  select to_char( loged_on, 'yyyy-mm') as loged_on \n" +
                          "                       , case when 'Y' = :serviceNmAllSum then 'ALLSUM' \n" +
                          "                              else service_nm\n" +
                          "                          end as service_nm\n" +
                          "                       , case when 'Y' = :platformTypeAllSum then 'ALLSUM' \n" +
                          "                              else platform_type\n" +
                          "                          end as platform_type \n" +
                          "                       , case when 0 = :hospitalId then 0\n" +
                          "                              else hospital_id\n" +
                          "                          end\n" +
                          "                       , case when 0 = :hospitalId then 'ALLSUM'\n" +
                          "                              else hospital_cd\n" +
                          "                          end as hospital_cd\n" +
                          "                       , case when 0 = :hospitalId then 'ALLSUM'\n" +
                          "                              else hospital_nm\n" +
                          "                          end as hospital_nm\n" +
                          "                       , count(1) as cnt\n" +
                          "                    from lemon_eis.stat_login\n" +
                          "                   where to_char( loged_on, 'yyyy-mm') between :startMm and :endMm\n" +
                          "                     and ( case when 'ALL' = :platformType then 'ALL' = :platformType\n" +
                          "                                else platform_type = :platformType\n" +
                          "                            end\n" +
                          "                         )\n" +
                          "                     and ( case when 0 = :hospitalId then 0 = :hospitalId\n" +
                          "                                else hospital_id = :hospitalId\n" +
                          "                            end\n" +
                          "                         )\n" +
                          "                   group by to_char( loged_on, 'yyyy-mm'), service_nm , platform_type , hospital_id , hospital_cd , hospital_nm\n" +
                          "                ) a\n" +
                          "          group by a.loged_on, a.service_nm, a.platform_type, a.hospital_id, a.hospital_cd, a.hospital_nm\n" +
                          "          order by a.loged_on asc \n"+
                           "       ) q                                                      \n";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startMm", startMm);
        query.setParameter("endMm", endMm);
        query.setParameter("platformType", platformType);
        query.setParameter("hospitalId", hospitalId);
        query.setParameter("serviceNmAllSum", serviceNmAllSum);
        query.setParameter("platformTypeAllSum", platformTypeAllSum);

        String result = query.getSingleResult().toString();

        return result;
    }

    /**
     * 2020.10.19 : 김진호
     * 회원 가입 현황(가입유형별)
     * @param startDt
     * @param endDt
     * @param hospitalCd
     * @param platformType
     * @param hospitalCdALL
     * @param ageCdAll
     * @param sexCdAll
     * @param areaCdAll
     * @return
     */
    @Transactional
    public String getJoinStat( LocalDateTime startDt, LocalDateTime endDt, String hospitalCd, String platformType, String hospitalCdALL, String ageCdAll, String sexCdAll, String areaCdAll ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text          \n" +
                "  from                                                          \n" +
                "       (                                                        \n" +
                "         select a.stat_dt       as statDt \n" +
                "              , a.stat_ym       as statYm \n" +
                "              , a.hospital_cd   as hospitalCd \n" +
                "              , a.age_cd        as ageCd \n" +
                "              , a.sex_cd        as sexCd \n" +
                "              , a.area_cd       as areaCd \n" +
                "              , a.platform_type as platformType \n" +
                "              , sum(a.cnt)      as cnt\n" +
                "           from ( select stat_dt \n" +
                "                       , stat_ym\n" +
                "                       , case when 'Y' = :hospitalCdALL then 'ALLSUM'\n" +
                "                              else hospital_cd\n" +
                "                          end\n" +
                "                       , case when 'Y' = :ageCdAll then 'ALLSUM'\n" +
                "                              else age_cd\n" +
                "                          end \n" +
                "                       , case when 'Y' = :sexCdAll then 'ALLSUM'\n" +
                "                              else sex_cd\n" +
                "                          end \n" +
                "                       , case when 'Y' = :areaCdAll then 'ALLSUM'\n" +
                "                              else area_cd\n" +
                "                          end\n" +
                "                       , case when 'ALL' = :platformType then 'ALLSUM'\n" +
                "                              else platform_type\n" +
                "                          end        \n" +
                "                       , cnt\n" +
                "                    from lemon_eis.stat_join sj \n" +
                "                   where stat_dt between :startDt and :endDt \n" +
                "                     and ( case when 'ALL' = :hospitalCd then 'ALL' = :hospitalCd\n" +
                "                                else hospital_cd = :hospitalCd \n" +
                "                            end\n" +
                "                         )\n" +
                "                     and ( case when 'ALL' = :platformType then 'ALL' = :platformType\n" +
                "                                else platform_type = :platformType \n" +
                "                            end\n" +
                "                         )\n" +
                "                   group by stat_dt, stat_ym, hospital_cd, age_cd, sex_cd, area_cd, platform_type\n" +
                "                ) a\n" +
                "          group by a.stat_dt, a.stat_ym, a.hospital_cd, a.age_cd, a.sex_cd, a.area_cd, a.platform_type\n" +
                "          order by stat_dt asc \n" +
                "       ) q                                                      \n";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        query.setParameter("platformType", platformType);
        query.setParameter("hospitalCd", hospitalCd);
        query.setParameter("hospitalCdALL", hospitalCdALL);
        query.setParameter("ageCdAll", ageCdAll);
        query.setParameter("sexCdAll", sexCdAll);
        query.setParameter("areaCdAll", areaCdAll);

        String result = query.getSingleResult().toString();

        return result;
    }

    /**
     * 2020.10.19 : 김진호
     * 회원 탈퇴 현황(탈퇴사유별)
     * @param startDt
     * @param endDt
     * @param userId
     * @param inactivedOnAll
     * @param inactiveTypeAll
     * @param withdrawalTypeAll
     * @param withdrawalType
     * @return
     */
    @Transactional
    public String getStatInactived( LocalDateTime startDt, LocalDateTime endDt, int userId, String inactivedOnAll, String inactiveTypeAll, String withdrawalTypeAll, String withdrawalType, String inactivedOnPeriod ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text        \n" +
                "  from                                                                  \n" +
                "       (                                                                \n" +
                "         select inactived_on                    as \"inactivedOn\"      \n" +
                "              , inactive_type                   as \"inactiveType\"     \n" +
                "              , user_id                         as \"userId\"           \n" +
                "              , user_nm                         as \"userNm\"           \n" +
                "              , coalesce(withdrawal_type,'N/A') as \"withdrawalType\"   \n" +
                "              , coalesce(withdrawal_desc,'N/A') as \"withdrawalDesc\"   \n" +
                "              , sum(cnt)                        as \"cnt\"              \n" +
                "           from ( select case when 'Y' = :inactivedOnAll then 'ALLSUM'  \n" +
                "                              else to_char( inactived_on, 'yyyy-mm-dd') \n" +
                "                          end as inactived_on                           \n" +
                "                       , case when 'Y' = :inactiveTypeAll then 'ALLSUM' \n" +
                "                              else inactive_type\n" +
                "                          end\n" +
                "                       , case when 0 = :userId then 0 \n" +
                "                              else user_id\n" +
                "                          end\n" +
                "                       , case when 0 = :userId then 'ALLSUM' \n" +
                "                              else user_nm\n" +
                "                          end\n" +
                "                       , case when 'Y' = :withdrawalTypeAll then 'ALLSUM' \n" +
                "                              else withdrawal_type\n" +
                "                          end                                                         \n" +
                "                       , case when 'Y' = :withdrawalTypeAll then 'ALLSUM'             \n" +
                "                              else withdrawal_desc                                    \n" +
                "                          end                                                         \n" +
                "                       , count(1) as cnt                                              \n" +
                "                    from lemon_eis.stat_inactive                                      \n" +
                "                   where ( case when 'N' = :inactivedOnPeriod then 'N' = :inactivedOnPeriod \n" +
                "                                else inactived_on between :startDt and :endDt         \n" +
                "                            end\n" +
                "                         )\n" +
                "                     and ( case when 0 = :userId then 0 = :userId\n" +
                "                                else user_id = :userId\n" +
                "                            end \n" +
                "                         )\n" +
                "                     and ( case when 'ALL' = :withdrawalType then 'ALL' = :withdrawalType\n" +
                "                                else withdrawal_type = :withdrawalType\n" +
                "                            end \n" +
                "                         )\n" +
                "                   group by user_id, user_nm, to_char( inactived_on, 'yyyy-mm-dd'), inactive_type, withdrawal_type , withdrawal_desc \n" +
                "                ) a\n" +
                "          group by user_id, user_nm, inactived_on, inactive_type, withdrawal_type , withdrawal_desc \n" +
                "          order by inactived_on asc \n" +
                "       ) q                                                      \n";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        query.setParameter("userId", userId);
        query.setParameter("inactivedOnAll", inactivedOnAll);
        query.setParameter("inactiveTypeAll", inactiveTypeAll);
        query.setParameter("withdrawalTypeAll", withdrawalTypeAll);
        query.setParameter("withdrawalType", withdrawalType);
        query.setParameter("inactivedOnPeriod", inactivedOnPeriod);

        String result = query.getSingleResult().toString();

        return result;
    }

    /**
     * 2020.10.19 : 김진호
     * 회원 탈퇴 현황(탈퇴사유별)
     * @param startDt
     * @param endDt
     * @param hospitalid
     * @param platformTypeAllSum
     * @param logedOnPeriod
     * @param appId
     * @param appIdAllSum
     * @return
     */
    @Transactional
    public String getStatHospital( LocalDateTime startDt, LocalDateTime endDt, int hospitalid, String platformTypeAllSum, String logedOnPeriod, int appId, String appIdAllSum ) {

        String querySql = "SELECT coalesce(json_agg(q), '[]'\\:\\:json)\\:\\:text        \n" +
                "  from                                                                  \n" +
                "       (                                                                \n" +
                "         select hospital_cd    as \"hospitalCd\"    \n" +
                "              , hospital_id    as \"hospitalId\"    \n" +
                "              , hospital_nm    as \"hospitalNm\"    \n" +
                "              , service_nm     as \"serviceNm\"     \n" +
                "              , app_id         as \"appId\"         \n" +
                "              , app_state      as \"appState\"      \n" +
                "              , app_ver        as \"appVer\"        \n" +
                "              , platform_type  as \"platformType\"  \n" +
                "              , sum(cnt)       as \"cnt\"           \n" +
                "           from (\n" +
                "                  select hospital_cd\n" +
                "                       , hospital_id\n" +
                "                       , hospital_nm\n" +
                "                       , service_nm\n" +
                "                       , case when 'Y' = :appIdAllSum then 0 \n" +
                "                              else app_id \n" +
                "                          end \n" +
                "                       , case when 'Y' = :appIdAllSum then 'ALLSUM' \n" +
                "                              else app_state \n" +
                "                          end \n" +
                "                       , case when 'Y' = :appIdAllSum then 'ALLSUM' \n" +
                "                              else app_ver \n" +
                "                          end \n" +
                "                       , case when 'Y' = :platformTypeAllSum then 'ALLSUM' \n" +
                "                              else platform_type\n" +
                "                          end \n" +
                "                       , count(1) as cnt\n" +
                "                    from lemon_eis.stat_login sl \n" +
                "                   where ( case when 'Y' = :logedOnPeriod then 'Y' = :logedOnPeriod \n" +
                "                                         else loged_on between :startDt and :endDt\n" +
                "                                     end\n" +
                "                         )\n" +
                "                     and ( case when 0 = :hospitalid then 0 = :hospitalid\n" +
                "                                else hospital_id = :hospitalid \n" +
                "                            end\n" +
                "                         )\n" +
                "                     and ( case when 0 = :appId then 0 = :appId\n" +
                "                                else app_id = :appId \n" +
                "                            end\n" +
                "                         )\n" +
                "                   group by hospital_cd, hospital_id, hospital_nm, service_nm , app_id , app_state , app_ver , platform_type\n" +
                "                ) a\n" +
                "          group by hospital_cd, hospital_id, hospital_nm, service_nm , app_id , app_state , app_ver , platform_type\n" +
                "          order by hospital_id asc \n" +
                "       ) q                                                      \n";

        // 결과 리스트를 가져온다.
        Query query = entityManager.createNativeQuery(querySql);
        // 전체 카운트를 구한다.

        query.setParameter("startDt", startDt);
        query.setParameter("endDt", endDt);
        query.setParameter("hospitalid", hospitalid);
        query.setParameter("platformTypeAllSum", platformTypeAllSum);
        query.setParameter("logedOnPeriod", logedOnPeriod);
        query.setParameter("appId", appId);
        query.setParameter("appIdAllSum", appIdAllSum);

        String result = query.getSingleResult().toString();

        return result;
    }

}
